import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库连接类
 * 没用了和utils重复了
 */
public class DatabaseConnection {

    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/goods";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static Connection connection;

    static {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    public static Connection getConnection() {
        return connection;
    }


    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //测试连接结果
    /*
    public static void main(String[] args) {
        // SQL query to select all from employee table
        String query = "SELECT * FROM employee";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Assuming employee table has columns: id, name, position, salary
                System.out.println("ID: " + resultSet.getString("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("--------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }*/
}