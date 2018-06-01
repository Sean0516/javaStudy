package com.jdbc;

import java.sql.*;

/**
 * Created by Sean on 2018/5/29.
 */
public class TestJdbc {
    public static void main(String[] args)  {
        ResultSet resultSet=null;
        PreparedStatement preparedStatement=null;
        Connection connection=null;
        try {
            Class.forName("");
            connection = DriverManager.getConnection("","","");
            preparedStatement= connection.prepareStatement("INSERT  INTO T_TEST (id,name,age,sex) VALUES (?,?,?)");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                resultSet.getString("colName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
