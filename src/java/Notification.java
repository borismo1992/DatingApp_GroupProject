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
public class Notification extends User implements Serializable {

    /**
     * Creates a new instance of Notification
     */
    private String sender;
    private  String type;
    private String content;
    private String time;
    private String senderId;
    
    public User loginUser = Login.loginUser;
    
    public  Notification() {
    }

    public Notification(String sender,String senderId, String content, String time) {
        this.sender = sender;
        this.type = type;
        this.content = content;
        this.time = time;
        this.senderId = senderId;
    }
    

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

  

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    
    
    public ArrayList<Notification> viewFriendRequest(){
        ArrayList<Notification> list = new ArrayList<>();
        
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
        ResultSet resultSet = null;   //set of results //set of results

        try {

            connection = DriverManager.getConnection(DATABASE_URL,
                    "virulkarv3140", "1577403");
            statement = connection.createStatement();
            
            String sql = "SELECT * FROM `dating_user` u JOIN `notification` n on u.user_Id = n.User_2 WHERE n.user_1 = '"+loginUser.getUser_ID()+"' AND n.IsSeen = \"False\" and Content = \"Request\"";

            resultSet = statement.executeQuery(sql);
             
           
            list.clear();
                      
            while (resultSet.next()) {
                
                       list.add(new Notification(resultSet.getString("user_name"), resultSet.getString("user_Id"), "Friend Request", resultSet.getString("timeSpan")));
                
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
        return  list;
    }
    
    public void acceptFriendReq(String user){
        
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
        ResultSet resultSet = null;   //set of results //set of results

        try {

            connection = DriverManager.getConnection(DATABASE_URL,
                    "virulkarv3140", "1577403");
            statement = connection.createStatement();
            
            String sql = "UPDATE `notification` SET  `IsSeen` = \"True\" WHERE User_1 = '" +loginUser.getUser_ID() + "' and User_2 = '" + user + "'";
            statement.executeUpdate(sql);
            
            sql ="UPDATE `notification` SET = `User_1 = '"+loginUser.getUser_ID()+"' ,`User_2`= '"+user+"',`IsSeen`=\"True \"";
                       statement.executeUpdate(sql);
            

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
        
        
    }
    
    public void denyRequest(String user){
        
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
        ResultSet resultSet = null;   //set of results //set of results

        try {

            connection = DriverManager.getConnection(DATABASE_URL,
                    "virulkarv3140", "1577403");
            statement = connection.createStatement();
            
           String sql ="UPDATE `notification` SET = `User_1 = '"+loginUser.getUser_ID()+"' ,`User_2`= '"+user+"',`IsSeen`=\"True \"";
                       statement.executeUpdate(sql);
            
            
            
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
    }
    
}
