
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ChandanBandi
 */

@ManagedBean
@SessionScoped

public class WelcomePage {
     private int user_ID ;
    private String userName;
    private String password;
   
    
    
    public void userProfile() {}
    
    public User loginUser = Login.loginUser;
   
    public String name = loginUser.getF_Name();

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
        
    }
    
     

    
    
}
