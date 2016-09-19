package com.otto.beekeeperbackendfinal.Model;

/**
 * Created by 212026992 on 9/2/2016.
 */
public class User {


    private String firstName;
    private String lastName;
    private String username;
    private String password;


    public User() {
    }





    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = name;
    }

    public String getSurname() {
        return lastName;
    }

    public void setSurname(String surname) {
        this.lastName = surname;
    }

    public String getPhone() {
        return username;
    }

    public void setPhone(String phone) {
        this.username = phone;
    }

    public String getIDnumber() {
        return password;
    }

    public void setIDnumber(String IDnumber) {
        this.password = IDnumber;
    }


}