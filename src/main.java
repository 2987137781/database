
import pojo.Inbound;
import service.InboundService;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class main {

    public static void main(String[] args) throws Exception {
        InboundService inboundservice=new InboundService();
        List<Inbound>list;
        list=inboundservice.queryall();
        System.out.println(list.toString());
        Inbound inbound=new Inbound();
        inbound.setNum(500);
        inbound.setId("2");
        System.out.println(inboundservice.inbound_updatenum(inbound));
        System.out.println(inboundservice.inbound_queryid("1"));
        System.out.println(inboundservice.inbound_delete("1"));
//        Date d=new Date();
//        Timestamp timestamp=new Timestamp(d.getTime());
//        Inbound inbound1=new Inbound("3","A2","3",20,timestamp);
//        System.out.println(inboundservice.insertinbound(inbound1));
    }
}
