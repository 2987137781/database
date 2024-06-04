package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pojo.Employee;
import utils.Utils;

public class EmployeeService {
    public int employee_insert(Employee employee)throws Exception{
        Connection connection= Utils.getConnection();
        String sql="insert into employee(id,name,phone_number,authority) values(?,?,?,?)";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,employee.getId());
        preparedStatement.setString(2,employee.getName());
        preparedStatement.setString(3,employee.getPhone_number());
        preparedStatement.setInt(4,employee.getAuthority());
        int i=preparedStatement.executeUpdate();
        Utils.Close(preparedStatement,connection);
        return i;
    }
    public int employee_updateaut(Employee employee)throws Exception{
        Connection connection=Utils.getConnection();
        String sql="update employee set authority=? where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,employee.getAuthority());
        preparedStatement.setString(2,employee.getId());
        int i=preparedStatement.executeUpdate();
        Utils.Close(preparedStatement,connection);
        return i;
    }
    public int employee_delete(String id)throws Exception{
        Connection connection=Utils.getConnection();
        String sql="delete from employee where id = ?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        int i=preparedStatement.executeUpdate();
        Utils.Close(preparedStatement,connection);
        return i;
    }
    public Employee employee_queryid(String id)throws Exception{
        Connection connection=Utils.getConnection();
        String sql="select * from employee where id=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet rs=preparedStatement.executeQuery();
        Employee employee=new Employee();
        if(rs.next()){
            employee.setId(rs.getString("id"));
            employee.setName(rs.getString("name"));
            employee.setPhone_number(rs.getString("phone_number"));
            employee.setAuthority(rs.getInt("authority"));
        }
        Utils.Close(preparedStatement,connection);
        return employee;
    }
    public int query_login(String phone_number,String password)throws Exception{
        Connection connection=Utils.getConnection();
        String sql="select * from employee where phone_number=? and password=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,phone_number);
        preparedStatement.setString(2,password);
        ResultSet rs=preparedStatement.executeQuery();
        Employee employee=new Employee();
        if(rs.next()){
            employee.setId(rs.getString("id"));
            employee.setName(rs.getString("name"));
            employee.setPhone_number(rs.getString("phone_number"));
            employee.setAuthority(rs.getInt("authority"));
            employee.setPassword(rs.getString("password"));
        }
        int i=1;
        if (employee.getId()!=null)i=2;
        Utils.Close(preparedStatement,connection);
        return i;
    }
    public List<Employee> queryall()throws Exception{
        Connection connection=Utils.getConnection();
        String sql="select * from employee";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        List<Employee>array=new ArrayList<>();
        ResultSet res=preparedStatement.executeQuery(sql);
        while(res.next()){
            Employee employee=new Employee();
            employee.setId(res.getString("id"));
            employee.setName(res.getString("name"));
            employee.setPhone_number(res.getString("phone_number"));
            employee.setAuthority(res.getInt("authority"));
            array.add(employee);
        }
        Utils.Close(preparedStatement,connection);
        return  array;
    }
}
