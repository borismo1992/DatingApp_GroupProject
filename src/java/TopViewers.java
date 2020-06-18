
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
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ChandanBandi
 */

@ManagedBean
@SessionScoped
@Named(value = "topViewers")
@Dependent
public class TopViewers  extends User implements Serializable {
    
    public TopViewers(){
        topSearch();
        topSearchF();
    }

     public User loginUser = Login.loginUser;
     
      ArrayList<User> Viewers = new ArrayList<>();
      ArrayList<User> View = new ArrayList<>();

    public ArrayList<User> getView() {
        return View;
    }

    public void setView(ArrayList<User> View) {
        this.View = View;
    }
      
    public ArrayList<User> getViewers() {
        return Viewers;
    }

    public void setViewers(ArrayList<User> Viewers) {
        this.Viewers = Viewers;
    }
      
         
    /**
     * Creates a new instance of TopViewers
     * @return 
     */
     public ArrayList topSearch() {
         
         
          
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
            
            String sql = "select * from dating_user where gender = 'Male' order by views DESC LIMIT 3";

            resultSet = statement.executeQuery(sql);
             
           
            Viewers.clear();
                      
            while (resultSet.next()) {
                
                Viewers.add(new User(resultSet.getInt("user_Id"),resultSet.getString("first_Name"), resultSet.getString("last_Name"), resultSet.getString("gender"), resultSet.getInt("age"), resultSet.getInt("views")));
                       
                
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
         return Viewers;   

}
      public ArrayList topSearchF() {
         
         
          
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
            
                       
            String sql1 = "select * from dating_user where gender = 'Female' order by views DESC LIMIT 3";

            resultSet = statement.executeQuery(sql1);
            
            View.clear();
            while (resultSet.next()) {                 
                View.add(new User(resultSet.getInt("user_Id"),resultSet.getString("first_Name"), resultSet.getString("last_Name"), resultSet.getString("gender"), resultSet.getInt("age"), resultSet.getInt("views")));
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
         return View;
         

}

  public ArrayList ViewTop3M(){
      return getViewers();
      
  }
   public ArrayList ViewTop3F(){
      return getView();
      
  }
   
//   public String showText()
//    {
//        return "Select Distinct sender from message where receiver = '" + loginUser.getUser_ID() + "'";
//    }
  
}