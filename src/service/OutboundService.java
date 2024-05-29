package service;

import pojo.Outbound;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OutboundService {
    public int outbound_insert(Outbound outbound)throws Exception{
        Connection connection= Utils.getConnection();
        String sql="insert into inbound(id,employee_id,goods_id,num,trade_time) values(?,?,?,?,?)";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,outbound.getId());
        preparedStatement.setString(2,outbound.getEmployee_id());
        preparedStatement.setString(3,outbound.getGoods_id());
        preparedStatement.setInt(4,outbound.getNum());
        preparedStatement.setTimestamp(5, (Timestamp) outbound.getTrade_time());
        int i=preparedStatement.executeUpdate();
        Utils.Close(preparedStatement,connection);
        return i;
    }
    public int outbound_updatenum(Outbound outbound)throws Exception{
        Connection connection=Utils.getConnection();
        String sql="update outbound set num=? where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,outbound.getNum());
        preparedStatement.setString(2,outbound.getId());
        int i=preparedStatement.executeUpdate();
        Utils.Close(preparedStatement,connection);
        return i;
    }
    public int outbound_delete(String id)throws Exception{
        Connection connection=Utils.getConnection();
        String sql="delete from outbound where id = ?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        int i=preparedStatement.executeUpdate();
        Utils.Close(preparedStatement,connection);
        return i;
    }
    public Outbound outbound_queryid(String id)throws Exception{
        Connection connection=Utils.getConnection();
        String sql="select * from outbound where id=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet rs=preparedStatement.executeQuery();
        Outbound outbound=new Outbound();
        if(rs.next()){
            outbound.setId(rs.getString("id"));
            outbound.setEmployee_id(rs.getString("employee_id"));
            outbound.setGoods_id(rs.getString("goods_id"));
            outbound.setNum(rs.getInt("num"));
            outbound.setTrade_time(rs.getTimestamp("trade_time"));
        }
        Utils.Close(preparedStatement,connection);
        return outbound;
    }
    public List<Outbound> queryall()throws Exception{
        Connection connection=Utils.getConnection();
        String sql="select * from outbound";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        List<Outbound>array=new ArrayList<>();
        ResultSet res=preparedStatement.executeQuery(sql);
        while(res.next()){
            Outbound outbound=new Outbound();
            outbound.setId(res.getString("id"));
            outbound.setEmployee_id(res.getString("employee_id"));
            outbound.setGoods_id(res.getString("goods_id"));
            outbound.setNum(res.getInt("num"));
            outbound.setTrade_time(res.getTimestamp("trade_time"));
            array.add(outbound);
        }
        Utils.Close(preparedStatement,connection);
        return  array;
    }
}

