package service;

import pojo.OutstoreRequest;
import pojo.Outbound;
import pojo.Stock;
import utils.Utils;

import javax.swing.*;
import java.sql.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OutstoreRequestService {
    // 提交出库请求
    public static void submitOutstoreRequest(String employeeId, String productId, int quantity) {
        String requestId = generateRequestId();
        String status = "pending";

        try (Connection connection = Utils.getConnection()) {
            String sql = "INSERT INTO outstore_request (request_id, employee_id, product_id, quantity, status) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, requestId);
                preparedStatement.setString(2, employeeId);
                preparedStatement.setString(3, productId);
                preparedStatement.setInt(4, quantity);
                preparedStatement.setString(5, status);
                preparedStatement.executeUpdate();
                System.out.println("出库请求提交成功，等待经理审批。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("出库请求提交失败。");
        }
    }

    // 审批出库请求
    public static void approveOutstoreRequest(String requestId, boolean approve) {
        String status = approve ? "approved" : "rejected";
        System.out.println(requestId);
        try (Connection connection = Utils.getConnection()) {
            String sql = "UPDATE outstore_request SET status = ? WHERE request_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, status);
                preparedStatement.setString(2, requestId);
                preparedStatement.executeUpdate();

                if (approve) {
                    recordOutstore(requestId);
                    System.out.println("出库请求已批准并记录到出库表中。");
                } else {
                    System.out.println("出库请求被拒绝。");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("审批出库请求失败。");
        }
    }

    private static void recordOutstore(String requestId) {
        try (Connection connection = Utils.getConnection()) {
            String sqlSelect = "SELECT employee_id, product_id, quantity FROM outstore_request WHERE request_id = ?";
            String sqlInsert = "INSERT INTO outbound (id,employee_id,goods_id,num,trade_time) VALUES (?, ?, ?, ?, NOW())";

            try (PreparedStatement selectStatement = connection.prepareStatement(sqlSelect);
                 PreparedStatement insertStatement = connection.prepareStatement(sqlInsert)) {

                selectStatement.setString(1, requestId);
                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String employeeId = resultSet.getString("employee_id");
                        String productId = resultSet.getString("product_id");
                        int quantity = resultSet.getInt("quantity");

                        // 执行出库操作
                        OutboundService outboundService = new OutboundService();
                        StockService stockService = new StockService();

                        Outbound outbound = new Outbound();
                        int currentRequestCount = OutstoreRequestService.getRequestCount();
                        String newId = String.format("REQ%05d", currentRequestCount + 1);
                        outbound.setId(newId);
                        outbound.setEmployee_id(employeeId);
                        outbound.setGoods_id(productId);
                        outbound.setNum(quantity);

                        // 获取当前时间戳
                        Timestamp timestamp = new Timestamp(new Date().getTime());
                        outbound.setTrade_time(timestamp);

                        try {
                            Stock stock = stockService.stock_queryid(productId);
                            if (stock.getId() == null) {
                                JOptionPane.showMessageDialog(null, "商品编号不存在！出库失败！！", "出库", JOptionPane.ERROR_MESSAGE);
                                return;
                            } else {
                                // 插入出库记录
                                outboundService.outbound_insert(outbound);
                                // 更新库存数量
                                stock.setNum(stock.getNum() - quantity);
                                stockService.stock_updateaut(stock);
                                JOptionPane.showMessageDialog(null, "出库成功！", "出库", JOptionPane.DEFAULT_OPTION);
                            }
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }

                        // 插入记录到出库表中
                        insertStatement.setString(1, newId);
                        insertStatement.setString(2, employeeId);
                        insertStatement.setString(3, productId);
                        insertStatement.setInt(4, quantity);
                        insertStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("记录出库操作失败。");
        }
    }

    // 获取所有待审批的出库请求
    public static List<OutstoreRequest> getPendingRequests() {
        List<OutstoreRequest> pendingRequests = new ArrayList<>();

        try (Connection connection = Utils.getConnection()) {
            String sql = "SELECT request_id, employee_id, product_id, quantity FROM outstore_request WHERE status = 'pending'";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String requestId = resultSet.getString("request_id");
                    String employeeId = resultSet.getString("employee_id");
                    String productId = resultSet.getString("product_id");
                    int quantity = resultSet.getInt("quantity");

                    OutstoreRequest request = new OutstoreRequest(requestId, employeeId, productId, quantity);
                    pendingRequests.add(request);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取待审批的出库请求失败。");
        }

        return pendingRequests;
    }

    public static String generateRequestId() {
        int currentRequestCount = getRequestCount();
        return String.format("REQ%05d", currentRequestCount + 1);
    }

    public static int getRequestCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM outstore_request";
        try (Connection connection = Utils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
