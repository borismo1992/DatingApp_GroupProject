/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author borismo
 */
@Named(value = "newmessage")
@ManagedBean
@SessionScoped
@Dependent
public class Message extends User implements Serializable {

    /**
     * Creates a new instance of newmessage
     */
    public User loginUser = Login.loginUser;
       
    ArrayList<User> usersList = new ArrayList<>();
    
    
    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<User> usersList) {
        this.usersList = usersList;
    }
    
    //public static User loginUser;
    private String receiver;
    private static String selectedReceiver;
    private static String selectedReceiver_ID;
    private String inputcontent;
    
    public String getInputcontent()
    {
        return inputcontent;
    }
    public void setInputcontent(String inputcontent)
    {
        this.inputcontent = inputcontent;
    }
    public String getSelectedReceiver_ID()
    {
        return selectedReceiver_ID;
    }
    public void setSelectedReceiver_ID(String selectedReceiver_ID)
    {
        this.selectedReceiver_ID = selectedReceiver_ID;
    }
    public String getSelectedReceiver()
    {
        return selectedReceiver;
    }
    public void setSelectedReceiver(String selectedReceiver)
    {
        this.selectedReceiver = selectedReceiver;
    }
    public String getReceiver()
    {
        return receiver;
    }
    public void setReceiver(String receiver)
    {
        this.receiver = receiver;
    }
    
    public Message() {
    }
    
    private final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/virulkarv3140?useSSL=false";
    //final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/mos7216?useSSL=false";
    public String getDisplayList()
    {
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
        
        //final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/mos7216?useSSL=false";
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String displayMsg = "";
//        FacesContext fc = FacesContext.getCurrentInstance();
//        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
//        String selectedReceiver1= params.get("action");
//        try
//        {
//        if(!selectedReceiver.equals(null))
//        { 
//            setSelectedReceiver(selectedReceiver1);
//        }
//        }
//        catch(NullPointerException np)
//        {
//            np.printStackTrace();
//        }
        try
            {
                conn = DriverManager.getConnection(DATABASE_URL, "virulkarv3140", "1577403"); 
                //conn = DriverManager.getConnection(DATABASE_URL,"mos7216","1090277");
                //conn.setAutoCommit(false);
                st = conn.createStatement();
                rs = st.executeQuery("Select * from message where (receiver = '" + loginUser.getUser_ID() + "' or sender = '" + loginUser.getUser_ID() + "') AND (receiver ='" + selectedReceiver_ID + "' or sender = '" + selectedReceiver_ID + "') ORDER BY msgID");
                int i = 0;
                while(rs.next())
                {
                    if(rs.getString(2).equals(Integer.toString(loginUser.getUser_ID())))
                    {
                        displayMsg += "<li class=" + '"' + "mine" + '"' + "><span>" + rs.getString(6) + "</span></li>";
                    }
                    if(rs.getString(4).equals(Integer.toString(loginUser.getUser_ID())))
                    {
                        displayMsg += "<li><span>" + rs.getString(6) + "</span></li>";
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
                    conn.close();
                    st.close();
                    rs.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        
        String display = "<ol class=" + '"' + "messages" + '"' + ">"+ displayMsg + "</ol>";

        return display;
        
        }
    }
    
//    public ArrayList getSendMessageMain() throws NullPointerException
//    {
//        ArrayList sendmessageMain = new ArrayList();
//        {
//        //load the Driver
//        try
//        {
//            Class.forName("com.mysql.jdbc.Driver");
//            
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        
//        Connection conn = null;
//        Statement st = null;
//        ResultSet rs = null;
//        try
//            {
//                conn = DriverManager.getConnection(DATABASE_URL, "virulkarv3140", "1577403"); 
//                //conn = DriverManager.getConnection(DATABASE_URL,"mos7216","1090277");
//                st = conn.createStatement();
//                rs = st.executeQuery("Select Distinct receiver from message where sender = '" + loginUser.getUser_ID() + "'");
//                
//                while(rs.next())
//                {       
//                    Message obj_newmessage=new Message();
//                    obj_newmessage.setReceiver(rs.getString(1));
//                    sendmessageMain.add(obj_newmessage);
//                }
//                
//                if(sendmessageMain.size() == 0)
//                {
//                    sendmessageMain.add("No message sent!");
//                }
//            }
//            catch(SQLException e)
//            {
//                e.printStackTrace();
//            }
//            finally
//            {
//                try
//                {
//                    conn.close();
//                    st.close();
//                    rs.close();
//                }
//                catch(Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//
//        return sendmessageMain;        
//        }
//    }
//    
//    public ArrayList getReceiveMessageMain() throws NullPointerException
//    {
//        ArrayList receivemessageMain = new ArrayList();
//        {
//        //load the Driver
//        try
//        {
//            Class.forName("com.mysql.jdbc.Driver");
//            
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        Connection conn = null;
//        Statement st = null;
//        ResultSet rs = null;
//        try
//            {
//                //conn = DriverManager.getConnection(DATABASE_URL,"mos7216","1090277");
//                conn = DriverManager.getConnection(DATABASE_URL, "virulkarv3140", "1577403");
//                st = conn.createStatement();
//                rs = st.executeQuery("Select Distinct sender from message where receiver = '" + loginUser.getUser_ID() + "'");
//                
//                while(rs.next())
//                {       
//                    Message obj_newmessage=new Message();
//                    obj_newmessage.setReceiver(rs.getString(1));
//                    receivemessageMain.add(obj_newmessage);
//                }
//
//                if(receivemessageMain.size() == 0)
//                {
//                    receivemessageMain.add("No message received!");
//                }
//            }
//            catch(SQLException e)
//            {
//                e.printStackTrace();
//            }
//            finally
//            {
//                try
//                {
//                    conn.close();
//                    st.close();
//                    rs.close();
//                }
//                catch(Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//
//        return receivemessageMain;        
//        }
//    }
    public void sendMessage(String inputC)
    {
        String input = inputC;
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
            //return ("internalError");
        }
        
        String content = "";
        String wholeContent = "";
        int msgID = -1;
        int newmsgID = -1;
//        FacesContext fc = FacesContext.getCurrentInstance();
//        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
//        String inputcontent1= params.get("action");
//        setInputcontent(inputcontent1);
            
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        DateAndTime d = new DateAndTime();
       
        try
        {
            //conn = DriverManager.getConnection(DATABASE_URL, "mos7216", "1090277");
            conn = DriverManager.getConnection(DATABASE_URL, "virulkarv3140", "1577403");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT max(msgID) from message;");

            if(rs.next())
            {
            msgID = rs.getInt(1);
            }
            newmsgID = msgID + 1;
            
            int r = st.executeUpdate("insert into message values('"+newmsgID +"','" + loginUser.getUser_ID() + "','" + loginUser.getF_Name() + "','"+ selectedReceiver_ID + "','" + selectedReceiver + "','"+ input +"','1','" + d.DateTime() +"')");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try{
                conn.close();
                rs.close();
                st.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
         }
        setInputcontent("");
        }
    }
    
    public ArrayList getMessageMain() throws NullPointerException
    {
        ArrayList receivemessageMain = new ArrayList();
        ArrayList messageMain = new ArrayList();
        ArrayList sendmessageMain = new ArrayList();
        {
        //load the Driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try
            {
                //conn = DriverManager.getConnection(DATABASE_URL,"mos7216","1090277");
                conn = DriverManager.getConnection(DATABASE_URL, "virulkarv3140", "1577403");
                st = conn.createStatement();
                rs = st.executeQuery("Select Distinct sender from message where receiver = '" + loginUser.getUser_ID() + "'");
                
                while(rs.next())
                {       
                    Message obj_newmessage=new Message();
                    obj_newmessage.setReceiver(rs.getString(1));
                    receivemessageMain.add(obj_newmessage);
                }

//                if(receivemessageMain.size() == 0)
//                {
//                    receivemessageMain.add("No message received!");
//                }
                
                rs = st.executeQuery("Select Distinct receiver from message where sender = '" + loginUser.getUser_ID() + "'");
                
                while(rs.next())
                {       
                    Message obj_newmessage=new Message();
                    obj_newmessage.setReceiver(rs.getString(1));
                    sendmessageMain.add(obj_newmessage);
                }
                
//                if(sendmessageMain.size() == 0)
//                {
//                    sendmessageMain.add("No message sent!");
//                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    conn.close();
                    st.close();
                    rs.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            for(Object r: receivemessageMain)
                {
                    messageMain.add(r);
                }
            for(Object s: sendmessageMain)
                {
                    messageMain.add(s);
                }
            if(messageMain.size() != 0)
            {
                for(int i = 0;i<messageMain.size()-1;i++)
                {
                    for(int j = 1; j<messageMain.size();j++)
                    {
                        if(messageMain.get(i) == messageMain.get(j))
                        {
                            messageMain.remove(j);
                        }
                    }
                }
            }
//            else
//            {
//                Message obj_newmessage=new Message();
//                obj_newmessage.setReceiver("No Receiver");
//                messageMain.add("No Message Found!");
//            }  
        return messageMain;        
        }

    }
}
