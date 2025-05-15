package com.example.parolmanager;

public class Emloyee_login {
    private String login;
    private String pass;

    public Emloyee_login() {
    }

    // Конструктор должен использовать понятные имена, соответствующие БД
    public Emloyee_login(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
