package com.example.parolmanager;

public class Emloyee_login {
    //обьявление переменных
    private String login;
    private String pass;

    //констуркторы
    public Emloyee_login() {
    }

    public Emloyee_login(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    //геттеры
    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    //сеттеры
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
