package service;

import pojo.Stock;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockService {
    public int stock_insert(Stock stock)throws Exception{
        Connection connection= Utils.getConnection();
        String sql="insert into goods(id,name,num) values(?,?,?)";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,stock.getId());
        preparedStatement.setString(2,stock.getName());
        preparedStatement.setInt(3,stock.getNum());
        int i=preparedStatement.executeUpdate();
        Utils.Close(preparedStatement,connection);
        return i;
    }
    public int stock_updateaut(Stock stock)throws Exception{
        Connection connection=Utils.getConnection();
        String sql="update stock set num=? where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,stock.getNum());
        preparedStatement.setString(2,stock.getId());
        int i=preparedStatement.executeUpdate();
        Utils.Close(preparedStatement,connection);
        return i;
    }
    public int stock_delete(String id)throws Exception{
        Connection connection=Utils.getConnection();
        String sql="delete from stock where id = ?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        int i=preparedStatement.executeUpdate();
        Utils.Close(preparedStatement,connection);
        return i;
    }
    public Stock stock_queryid(String id)throws Exception{
        Connection connection=Utils.getConnection();
        String sql="select * from stock where id=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet rs=preparedStatement.executeQuery();
        Stock stock=new Stock();
        if(rs.next()){
            stock.setId(rs.getString("id"));
            stock.setName(rs.getString("name"));
            stock.setNum(rs.getInt("num"));
        }
        Utils.Close(preparedStatement,connection);
        return stock;
    }
    public List<Stock> queryall()throws Exception{
        Connection connection=Utils.getConnection();
        String sql="select * from stock";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        List<Stock>array=new ArrayList<>();
        ResultSet res=preparedStatement.executeQuery(sql);
        while(res.next()){
            Stock stock=new Stock();
            stock.setId(res.getString("id"));
            stock.setName(res.getString("name"));
            stock.setNum(res.getInt("num"));
            array.add(stock);
        }
        Utils.Close(preparedStatement,connection);
        return  array;
    }
}
