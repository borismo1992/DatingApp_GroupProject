/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ChandanBandi
 */
public class SearchUser {
    private String gender;
    private String city;
    private int minAge;
    private int maxAge;
    
    public SearchUser(){
        
    }

    public SearchUser(String gender, String city, int minAge) {
        this.gender = gender;
        
        this.city = city;
         this.minAge = minAge;
        this.maxAge = maxAge;
    }

    
    
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
    
    
    
}
