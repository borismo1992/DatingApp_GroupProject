
import javax.faces.bean.ManagedBean;

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
public class User {
    
     private int user_ID;
    private String userName;
    private String F_Name;
    private String L_Name;
    private String gender;
    private String password;
    private int age;
    private String city;
    private String interest;
    private int views;
    private String timeStamp;

    public User(int user_ID, String userName, String F_Name, String L_Name, String gender, String password, int age, String city, String interest) {
        this.user_ID = user_ID;
        this.userName = userName;
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.gender = gender;
        this.password = password;
        this.age = age;
        this.city = city;
        this.interest = interest;
    }

    public User(int user_ID, String userName, String F_Name, String L_Name, String gender, int age, String city) {
        this.user_ID = user_ID;
        this.userName = userName;
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    public User(){
        
    }
    
    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

        public String getF_Name() {
        return F_Name;
    }

    public void setF_Name(String F_Name) {
        this.F_Name = F_Name;
    }

    public String getL_Name() {
        return L_Name;
    }

    public void setL_Name(String L_Name) {
        this.L_Name = L_Name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    public User(int id, String userName, String fName){
        user_ID =id;
        this.userName = userName;
        this.F_Name = fName;
    }

    public User(int user_ID,String F_Name, String L_Name, String gender, int age, int views) {
        this.user_ID = user_ID;
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.gender = gender;
        this.age = age;
        this.views = views;
    }

    public User(int user_ID, String F_Name, String L_Name, String gender, int age, String city) {
        this.user_ID = user_ID;
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }
    
    

    public User(String userName, String F_Name, String L_Name, String gender, int age, String city) {
        this.userName = userName;
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    public User(String F_Name, String L_Name, String gender, int age, String city, String interest) {
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.gender = gender;
        this.age = age;
        this.city = city;
        this.interest = interest;
    }
    
    
    
    
   public  String toString(){
       String userString = getUser_ID()+ " " +getUserName()+" "+getF_Name() + " "+ getL_Name();
       return userString;
   }
    
}
