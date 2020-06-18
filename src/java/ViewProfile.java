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
@SessionScoped

public class ViewProfile extends User implements Serializable {
    // public User loginUser = Login.loginUser;
     private User friend;
     //String friend="";
     private ArrayList<User> list = new ArrayList<>();
     
     public String viewProfile(String viewFriend){
          
        //load the Driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        { 
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }
        
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/virulkarv3140?useSSL=false";
        Connection connection = null;  //a connection to the database
        Statement statement = null;    //execution of a statement
        ResultSet resultSet = null;   //set of results
        
        
        try
        {      
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "virulkarv3140", "1577403");   
            statement = connection.createStatement();
            String sql = "SELECT * FROM `dating_user` WHERE `user_Id` = '" + viewFriend + "'";

            resultSet = statement.executeQuery(sql);
            
            if(resultSet.next())
            {
                //id is found
                
                       friend = new User(resultSet.getString("first_Name"),
                       resultSet.getString("last_Name"),
                       resultSet.getString("gender"),
                       resultSet.getInt("age"),
                       resultSet.getString("city"), 
                       resultSet.getString("interest"));
                
                       int view = resultSet.getInt("views");
                view++;
                sql = "UPDATE `dating_user` SET `views` = '" + view + "'  where `user_Id` = '" + getUser_ID() + "' ";
                statement.executeUpdate(sql);
                                
            }
            else
            {
                    //display loginNotOK.xhtml
                    return "";  
            }
        }
        catch (SQLException e) {
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
        return "viewProfile";
        
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

     
     
}