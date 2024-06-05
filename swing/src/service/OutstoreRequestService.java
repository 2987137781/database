package service;
import  utils.*;
import  pojo.OutstoreRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class OutstoreRequestService {
    private static List<OutstoreRequest> pendingRequests = new ArrayList<>();

    // 提交出库请求
    public static void submitOutstoreRequest(String employeeId, String productId, int quantity) {
        OutstoreRequest request = new OutstoreRequest(employeeId, productId, quantity);
        pendingRequests.add(request);
        System.out.println("出库请求提交成功，等待经理审批。");
    }

    // 审批出库请求
    public static void approveOutstoreRequest(String employeeId, String productId, boolean approve) throws Exception {
        Iterator<OutstoreRequest> iterator = pendingRequests.iterator();

        while (iterator.hasNext()) {
            OutstoreRequest request = iterator.next();

            if (request.getEmployeeId().equals(employeeId) && request.getProductId().equals(productId)) {
                if (approve) {
                    recordOutstore(request);
                    System.out.println("出库请求已批准并记录到出库表中。");
                } else {
                    System.out.println("出库请求被拒绝。");
                }
                iterator.remove();
                return;
            }
        }

        System.out.println("没有找到匹配的出库请求。");
    }

    // 记录出库操作到数据库
    private static void recordOutstore(OutstoreRequest request) throws Exception {
        Connection connection = Utils.getConnection();
        String sql = "INSERT INTO outstore (outstore_id, employee_id, product_id, quantity, date) VALUES (?, ?, ?, ?, NOW())";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, UUID.randomUUID().toString());
        preparedStatement.setString(2, request.getEmployeeId());
        preparedStatement.setString(3, request.getProductId());
        preparedStatement.setInt(4, request.getQuantity());
        preparedStatement.executeUpdate();
        Utils.close(preparedStatement, connection);
    }
}
