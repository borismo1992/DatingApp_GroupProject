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
* @author ChandanBandi
 */
@ManagedBean
@SessionScoped

public class FriendReq extends User implements Serializable {
    
   public User loginUser = Login.loginUser;
   
     public void friendReq(String viewFriend){
          
      
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        { 
            e.printStackTrace();
            //return to internalError.xhtml
            //return ("internalError");
        }
        
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/virulkarv3140?useSSL=false";
        Connection connection = null;  //a connection to the database
        Statement statement = null;    //execution of a statement
        
        
        try
        {      
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "virulkarv3140", "1577403");   
            statement = connection.createStatement();
            String sql = "INSERT INTO `notification`( `User_1`, `User_2`, `Type`, `Content`, `IsSeen`) VALUES ('" + loginUser.getUser_ID() + "', '" + viewFriend + "', 'Friend Request', 'Request', 'False')";

                      
                statement.executeUpdate(sql);
            
            {
                    //display loginNotOK.xhtml
                   // return "";  
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //close the database
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        
    }

  

     
     
}