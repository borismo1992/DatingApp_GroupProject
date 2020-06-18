/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author USER
 */


@ManagedBean
@javax.faces.bean.SessionScoped
@RequestScoped

public class Friendlist extends User implements Serializable {

     public User loginUser = Login.loginUser;
    
  
    ArrayList<User> usersList = new ArrayList<>();
    
    
    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<User> usersList) {
        this.usersList = usersList;
    }

    public ArrayList friendList() {

//        if (!usersList.isEmpty()) {
//            usersList = new ArrayList<>();
//        }

        //load the Driver
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            //return to internalError.xhtml
            System.out.println("internalError");
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/virulkarv3140?useSSL=false";
        Connection connection = null;  //a connection to the database
        Statement statement = null;    //execution of a statement
        ResultSet resultSet = null;   //set of results

        try {

            connection = DriverManager.getConnection(DATABASE_URL,
                    "virulkarv3140", "1577403");
            statement = connection.createStatement();

            String condition = "";

            String sql = "SELECT * FROM dating_user WHERE user_Id != '" + loginUser.getUser_ID() + "' and user_Id in (select user_2 from friend_list)";

            usersList.clear();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                usersList.add(new User(resultSet.getInt("user_ID"),resultSet.getString("user_name"), resultSet.getString("first_Name"), resultSet.getString("last_Name"), resultSet.getString("gender"),resultSet.getInt("age"),resultSet.getString("city")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //close the database
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        
        
       
        return usersList;

    }
    
}
