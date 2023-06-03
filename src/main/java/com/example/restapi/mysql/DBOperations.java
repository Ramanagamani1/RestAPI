package com.example.restapi.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOperations {

    private static Logger logger = LoggerFactory.getLogger(DBOperations.class);
    private Connection connection;

    DBOperations() {
        try {
            createConnection();
            createEmployeeTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createConnection() {
         try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db", "root", "daddy");
             logger.info("Connection created");
         }catch (Exception e) {
            logger.info("connection creation failed , {}",e);
         }
    }

    public void createEmployeeTable() throws SQLException {
        String sql = "create table if not exists employee (id int primary key auto_increment, name varchar(30), age int)";

        Statement statement = connection.createStatement();
        statement.execute(sql);
        logger.info("Table Created");
    }

    public void createEmployee(Employee employee) throws SQLException {
        String sql = "insert into employee(name,age) values('" +employee.getName()+"',"+employee.getAge()+")";
        Statement statement = connection.createStatement();
        int res = statement.executeUpdate(sql);
        logger.info("Number of employees created {}",res);
    }

    public Employee getEmployee(int id) throws SQLException {
        String sql = "select * from employee where id="+id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            int empId = rs.getInt(1); // rs.getInt("id")
            String name = rs.getString(2);
            Integer age = rs.getInt(3);

            return new Employee(empId, name, age);
        }
        return null;
    }

    public List<Employee> getEmployees() throws SQLException {
        String sql = "select * from employee";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        List<Employee> employees = new ArrayList<>();

        while(rs.next()) {
            employees.add(new Employee(rs.getInt(1),rs.getString(2),rs.getInt(3)));
        }

        return employees;
    }

    public void updateEmployee(int id, Employee employee) throws SQLException{
        String sql =  "update employee set name=?, age=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setInt(2,employee.getAge());
        preparedStatement.setInt(3,id);
        preparedStatement.execute();
        logger.info("Employee details updated");
    }

    public void deleteEmployee(int id) throws SQLException {
        String sql = "delete from employee where id="+id+"";
        Statement statement = connection.createStatement();
        statement.execute(sql);
        logger.info("Employee deleted with id {}",id);
    }


}
