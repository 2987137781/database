package utils;

import java.sql.*;

public class Utils  {
    public static final String DRIVER="com.mysql.cj.jdbc.Driver";

    public static final String url="jdbc:mysql://localhost:3306/goods";
    public static final String username="root";
    public static final String password="yyj123";


        static{
            try{
                Class.forName(DRIVER);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        public static Connection getConnection(){
            Connection connection=null;
            try{
                connection= DriverManager.getConnection(url,username,password);
            }catch(SQLException e){
                e.printStackTrace();
            }
            return connection;
        }
        public static void Close(Statement statement,Connection connection){
            Close(null,statement,connection);
        }
        public static void Close(ResultSet rs,Statement statement,Connection connection){
            try{
                if(rs!=null&&!rs.isClosed()){
                    rs.close();
                }
                if(statement!=null&&!statement.isClosed()){
                    statement.close();
                }
                if(connection!=null&&!connection.isClosed()){
                    connection.close();
                }
            }catch(Exception e){
                System.out.println("关闭数据连接失败");
            }
        }
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
