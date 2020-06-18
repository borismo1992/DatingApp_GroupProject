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
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ChandanBandi
 */

@ManagedBean
@SessionScoped
public class Login extends User implements Serializable{
  
 
    public static User loginUser;
    
    public String loginUser(){
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
            
             String sql = "SELECT * FROM `dating_user` WHERE `user_name`='"+getUserName()+"' and `password`='"+getPassword()+"' ";
            resultSet = statement.executeQuery(sql);
            
            if(resultSet.next())
            {
                //id is found
                loginUser = new User(resultSet.getInt("user_Id"),resultSet.getString("user_name"),
                       resultSet.getString("first_Name"),resultSet.getString("last_Name"),
                       resultSet.getString("gender"),resultSet.getString("password"),
                       resultSet.getInt("age"), resultSet.getString("city"), 
                       resultSet.getString("interest"));
               
                
                
                return "welcome";
               
//                 WelcomePage wp = new WelcomePage();
//
//               wp.userProfile();
            }
            else
            {
              
                    //display loginNotOK.xhtml
                    return "loginNotOK";  
                
                 
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return ("internalError");
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
    }
    }

    
    
    
    
}
