
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

@ManagedBean
@SessionScoped
@RequestScoped
public class Search extends User implements Serializable {

    public User loginUser = Login.loginUser;
       
    ArrayList<User> usersList = new ArrayList<>();
    
    
    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<User> usersList) {
        this.usersList = usersList;
    }

    public ArrayList searchByGender() {


        //load the Driver
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
        ResultSet resultSet = null;   //set of results

        try {

            connection = DriverManager.getConnection(DATABASE_URL,
                    "virulkarv3140", "1577403");
            statement = connection.createStatement();

            String condition = "";

            String sql = "SELECT * FROM dating_user WHERE user_Id != '" + loginUser.getUser_ID() + "' and user_Id not in (select user_2 from friend_list)";

            usersList.clear();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                usersList.add(new User(resultSet.getInt("user_Id"), resultSet.getString("first_Name"), resultSet.getString("last_Name"), resultSet.getString("gender"),resultSet.getInt("age"),resultSet.getString("city")));
            }

        } catch (SQLException e) {
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
        
            ArrayList<User> searchResultList = new ArrayList<>();
        
       

        return usersList;

    }

    public ArrayList searchResult() {
        return getUsersList();
    }
    
    public SearchUser searchU = new SearchUser();

    public SearchUser getSearchU() {
        return searchU;
    }

    public void setSearchU(SearchUser searchU) {
        this.searchU = searchU;
    }

}
