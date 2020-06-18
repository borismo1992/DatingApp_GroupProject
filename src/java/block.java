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
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author borismo
 */
@Named(value = "blockuser")
@SessionScoped
@ManagedBean
@Dependent

public class block extends User implements Serializable {

    /**
     * Creates a new instance of block
     */
    private String loggedinUser;
    public User loginUser = Login.loginUser;
       
    ArrayList<User> usersList = new ArrayList<>();
    
    
    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<User> usersList) {
        this.usersList = usersList;
    }
    private String targetedToBlockFname;
    private String targetedToBlockLname;
    private String targetedToBlock;
    private String status;
    //private final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/mos7216?useSSL=false";
    private final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/virulkarv3140?useSSL=false";
    
    private ArrayList<block> blockeduserList = new ArrayList<block> ();
    
    public ArrayList<block> getBlockUserList()
    {
        checkBlockedUser();
        return blockeduserList;
    }
    
    public String getLoggedinUser()
    {
        return loggedinUser;
    }
    public void setLoggedinUser(String loggedinUser)
    {
        this.loggedinUser = loggedinUser;
    }
    public String getTargetedtoBlock()
    {
        return targetedToBlock;
    }
    public void setTargetedtoBlock(String targetedToBlock)
    {
        this.targetedToBlock = targetedToBlock;
    }
    public String getStatus()
    {
        return status;
    }
    private static String blockingresult;
    
    public void setBlockingResult(String blockingresult)
    {
        this.blockingresult = blockingresult;
    }
    public String getBlockingResult()
    {
        return blockingresult;
    }
    public String getTargetedtoBlockFname()
    {
        return targetedToBlockFname;
    }
    public void setTargetedtoBlockFname(String targetedToBlockFname)
    {
        this.targetedToBlockFname = targetedToBlockFname;
    }
    public String getTargetedtoBlockLname()
    {
        return targetedToBlockLname;
    }
    public void setTargetedtoBlockLname(String targetedToBlockLname)
    {
        this.targetedToBlockLname = targetedToBlockLname;
    }
    public block() {
    }
    
    public block(String targetedToBlock, String targetedToBlockFname, String targetedToBlockLname, String status) {
        this.targetedToBlockFname = targetedToBlockFname;
        this.targetedToBlockLname = targetedToBlockLname;
        this.targetedToBlock = targetedToBlock;
        this.status = status;
        
    }
    
    public void blockUser(String blockinguserid, String blockuserfname, String blockuserlname) throws NullPointerException
    {
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        DateAndTime d = new DateAndTime();
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            //return ("internalError");
            e.printStackTrace();
        }
        
        try
        {
            //c = DriverManager.getConnection(DATABASE_URL,"mos7216","1090277");
            c = DriverManager.getConnection(DATABASE_URL, "virulkarv3140", "1577403"); 
            st = c.createStatement();
            rs = st.executeQuery("Select * from block "
                    + "where loggedinuser = '" + 
                    loginUser.getUser_ID() + "' and targettoblock = '" + blockinguserid + "'");
            
            if(rs.next())
            {
                if(rs.getString(5).equals("True"))
                {
                    setBlockingResult("You blocked this user already!");
                }
                if(rs.getString(5).equals("False"))
                {
                    int j = st.executeUpdate("Update block set status = 'True', timestamp = '" + d.DateTime() + "' where loggedinuser = '" + 
                    loginUser.getUser_ID() + "' and targettoblock = '" + blockinguserid + "'");
                    setBlockingResult("Successfully blocked this user!");
                }
            }
            else if(!rs.next())
            {
                int r = st.executeUpdate("Insert into block values ('" + loginUser.getUser_ID() + "', '" + blockinguserid + "', '" + blockuserfname + "', '" + blockuserlname + "', 'True', '" + d.DateTime() +"')");
                setBlockingResult("Successfully blocked this user!");
            }
                
        }       
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        finally
        {
            try
            {
                c.close();
                st.close();
                rs.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public ArrayList<block> checkBlockedUser() throws NullPointerException
    {
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            System.out.print("internalError");
        }
        
        try
        {
            c = DriverManager.getConnection(DATABASE_URL, "virulkarv3140", "1577403"); 
            //c = DriverManager.getConnection(DATABASE_URL,"mos7216","1090277");
            st = c.createStatement();
            rs = st.executeQuery("Select * from block "
                    + "where loggedinuser = '" + 
                    loginUser.getUser_ID() + "' and status = 'True'");
            
            while(rs.next())
            {
                blockeduserList.add(new block(rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        finally
        {
            try
            {
                c.close();
                st.close();
                rs.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        } 
       
        return blockeduserList;
    }   
    
    public void unblockUser(String blockinguserid, String blockuserfname, String blockuserlname)
    {
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        DateAndTime d = new DateAndTime();
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            //return ("internalError");
            e.printStackTrace();
        }
        
        try
        {
            //c = DriverManager.getConnection(DATABASE_URL,"mos7216","1090277");
            c = DriverManager.getConnection(DATABASE_URL, "virulkarv3140", "1577403"); 
            st = c.createStatement();
            rs = st.executeQuery("Select * from block "
                    + "where loggedinuser = '" + 
                    loginUser.getUser_ID() + "' and targettoblock = '" + blockinguserid + "'");
            
            if(rs.next())
            {
                if(rs.getString(5).equals("True"))
                {
                    int j = st.executeUpdate("Update block set status = 'False', timestamp = '" + d.DateTime() + "' where loggedinuser = '" + 
                    loginUser.getUser_ID() + "' and targettoblock = '" + blockinguserid + "'");
                }
            }
        }       
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        finally
        {
            try
            {
                c.close();
                st.close();
                rs.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
