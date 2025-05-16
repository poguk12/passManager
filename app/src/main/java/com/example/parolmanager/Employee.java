package com.example.parolmanager;

public class Employee {
    //обяьвление переменных
    private String siteName;
    private String login;
    private String pass;
    private String description;

    //констуркторы
    public Employee() {
    }
    public Employee(String siteName, String login, String pass, String description) {
        this.siteName = siteName;
        this.login = login;
        this.pass = pass;
        this.description = description;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getDescription() {
        return description;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}