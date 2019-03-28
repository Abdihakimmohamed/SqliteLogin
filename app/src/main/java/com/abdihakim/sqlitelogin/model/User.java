package com.abdihakim.sqlitelogin.model;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;

    /**
     *
     * created by Abdisky in 2019
     */

    // We will be using getters and setters to be able to get user data
    // Get data
    public int getId(){
        return id;
    }
    public void setId (int id){

        this.id = id;
    }

    public String getName(){

        return name;
    }
    public void setName(String name){

        this.name = name;
    }
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email =email;
    }

    public String getPassword (){
        return password;
    }

    public void setPassword (String password){

        this.password = password;
    }
}
