package operation;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryOperations {

    public static void performOutbound(Connection connection, String goodsId, int quantity, String employeeId) {
        if (connection != null) {
            try {
                // 更新库存
                String updateStoreSQL = "UPDATE store SET num = num - ? WHERE id = ?";
                PreparedStatement updateStoreStmt = connection.prepareStatement(updateStoreSQL);
                updateStoreStmt.setInt(1, quantity);
                updateStoreStmt.setString(2, goodsId);
                updateStoreStmt.executeUpdate();

                // 插入出库记录
                String insertOutboundSQL = "INSERT INTO outbound (id, employee_id, goods_id, num, trade_time) VALUES (?, ?, ?, ?, NOW())";
                PreparedStatement insertOutboundStmt = connection.prepareStatement(insertOutboundSQL);
                String outboundId = generateTransactionId("OUT"); // 生成一个唯一的出库号
                insertOutboundStmt.setString(1, outboundId);
                insertOutboundStmt.setString(2, employeeId);
                insertOutboundStmt.setString(3, goodsId);
                insertOutboundStmt.setInt(4, quantity);
                insertOutboundStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "出库成功");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "出库失败: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "无法连接到数据库");
        }
    }

    public static void performInbound(Connection connection, String goodsId, int quantity, String employeeId) {
        if (connection != null) {
            try {
                // 更新库存
                String updateStoreSQL = "UPDATE store SET num = num + ? WHERE id = ?";
                PreparedStatement updateStoreStmt = connection.prepareStatement(updateStoreSQL);
                updateStoreStmt.setInt(1, quantity);
                updateStoreStmt.setString(2, goodsId);
                updateStoreStmt.executeUpdate();

                // 插入入库记录
                String insertInboundSQL = "INSERT INTO inbound (id, employee_id, goods_id, num, trade_time) VALUES (?, ?, ?, ?, NOW())";
                PreparedStatement insertInboundStmt = connection.prepareStatement(insertInboundSQL);
                String inboundId = generateTransactionId("IN"); // 生成一个唯一的入库号
                insertInboundStmt.setString(1, inboundId);
                insertInboundStmt.setString(2, employeeId);
                insertInboundStmt.setString(3, goodsId);
                insertInboundStmt.setInt(4, quantity);
                insertInboundStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "入库成功");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "入库失败: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "无法连接到数据库");
        }
    }

    private static String generateTransactionId(String prefix) {
        // 生成唯一的交易号，可以根据你的需求实现
        return prefix + System.currentTimeMillis();
    }
}
