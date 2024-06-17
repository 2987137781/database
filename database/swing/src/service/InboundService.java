package service;

import pojo.Inbound;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InboundService {
    public int inbound_insert(Inbound inbound)throws Exception{
        Connection connection= Utils.getConnection();
        String sql="insert into inbound(id,employee_id,goods_id,num,trade_time) values(?,?,?,?,?)";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,inbound.getId());
        preparedStatement.setString(2,inbound.getEmployee_id());
        preparedStatement.setString(3,inbound.getGoods_id());
        preparedStatement.setInt(4,inbound.getNum());
        preparedStatement.setTimestamp(5, (Timestamp) inbound.getTrade_time());
        int i=preparedStatement.executeUpdate();
        Utils.Close(preparedStatement,connection);
        return i;
    }
    public int inbound_updatenum(Inbound inbound)throws Exception{
        Connection connection=Utils.getConnection();
        String sql="update inbound set num=? where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,inbound.getNum());
        preparedStatement.setString(2,inbound.getId());
        int i=preparedStatement.executeUpdate();
        Utils.Close(preparedStatement,connection);
        return i;
    }
    public int inbound_delete(String id)throws Exception{
        Connection connection=Utils.getConnection();
        String sql="delete from inbound where id = ?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        int i=preparedStatement.executeUpdate();
        Utils.Close(preparedStatement,connection);
        return i;
    }
    public Inbound inbound_queryid(String id)throws Exception{
        Connection connection=Utils.getConnection();
        String sql="select * from inbound where id=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet rs=preparedStatement.executeQuery();
        Inbound inbound=new Inbound();
        if(rs.next()){
            inbound.setId(rs.getString("id"));
            inbound.setEmployee_id(rs.getString("employee_id"));
            inbound.setGoods_id(rs.getString("goods_id"));
            inbound.setNum(rs.getInt("num"));
            inbound.setTrade_time(rs.getTimestamp("trade_time"));
        }
        Utils.Close(preparedStatement,connection);
        return inbound;
    }
    public  List<Inbound> queryall()throws Exception{
        Connection connection=Utils.getConnection();
        String sql="select * from inbound";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        List<Inbound>array=new ArrayList<>();
        ResultSet res=preparedStatement.executeQuery(sql);
        while(res.next()){
            Inbound inbound=new Inbound();
            inbound.setId(res.getString("id"));
            inbound.setEmployee_id(res.getString("employee_id"));
            inbound.setGoods_id(res.getString("goods_id"));
            inbound.setNum(res.getInt("num"));
            inbound.setTrade_time(res.getTimestamp("trade_time"));
            array.add(inbound);
        }
        Utils.Close(preparedStatement,connection);
        return  array;
    }
}
