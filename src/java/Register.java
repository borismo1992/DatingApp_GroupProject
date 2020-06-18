/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ChandanBandi
 */

@ManagedBean
@RequestScoped
public class Register extends User implements Serializable {
    
    public static User loginUser;
    
    public String registerUser(){
         
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
        
         if (!isUserExist(getUserName())) {
        
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/virulkarv3140?useSSL=false";
        Connection connection = null;  //a connection to the database
        Statement statement = null;    //execution of a statement
        ResultSet resultSet = null;   //set of results
        
        try
        {      
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL,"virulkarv3140","1577403");   
            
            connection.setAutoCommit(false);
            
            statement = connection.createStatement();
            
            String sql = "INSERT INTO `dating_User`( `user_name`, `first_Name`, `last_Name`, `gender`, `password`, `age`, `city`, `interest`,`views`)"
                     + " VALUES ('" + getUserName() + "', '" + getF_Name() + "', '" + getL_Name() + "','" + getGender()
                    + "', '" + getPassword() + "', '" + getAge() + "', '" + getCity() + "', '" + getInterest() + "', 0)";
             statement.executeUpdate(sql);
             
             
            connection.commit();
            connection.setAutoCommit(true);
                
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

        } else {
           return "login";
        }
         return "login";
 
    }
    
    public boolean isUserExist(String UserName) {
         {
        //load the Driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
//            return ("internalError");
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
            
             String sql = "SELECT * FROM `dating_User` WHERE `user_name` ='" + UserName + "' ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return true;
            }
             
            else
            {
            return false;               
            }
            }
        catch (SQLException e)
        {
            e.printStackTrace();
            
        }
        finally
        {
            try
            {
                resultSet.close();
                statement.close();
                connection.close();                 
            }
            catch (Exception e)
            {
                e.printStackTrace();    
            }
        }
        return false;
    }
    
    }
        
}

            

   