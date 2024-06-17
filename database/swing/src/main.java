
import pojo.Inbound;
import service.InboundService;
import utils.Utils;
import view.EmployeeFrame;

import java.sql.Connection;
import java.util.List;

public class main {
    public static void main(String[] args) throws Exception {
        Connection connection = Utils.getConnection();

        if (connection != null) {
            //
            Login login=new Login();
            InboundService inboundservice=new InboundService();
            List<Inbound>list;
            list=inboundservice.queryall();
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }

}
