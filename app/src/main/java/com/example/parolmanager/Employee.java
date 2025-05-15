package com.example.parolmanager;

public class Employee {
    private String siteName;
    private String login;
    private String pass;  // Было name, теперь pass для соответствия БД
    private String description;  // Было opisanie, теперь description

    public Employee() {
    }

    // Конструктор должен использовать понятные имена, соответствующие БД
    public Employee(String siteName, String login, String pass, String description) {
        this.siteName = siteName;
        this.login = login;
        this.pass = pass;
        this.description = description;
    }

    // Геттеры должны соответствовать именам полей БД
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

    // Сеттеры также должны быть согласованы
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