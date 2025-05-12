package com.example.parolmanager;

public class Employee {
    private String siteName;
    private String login;
    private String name;
    private String opisanie;

    public Employee() {
    }

    public Employee(String taskName, String taskDescription, String taskDate, String taskPriority) {
        this.siteName = taskName;
        this.login = taskDescription;
        this.name = taskDate;
        this.opisanie = taskPriority;
    }

    public String getTaskName() {
        return siteName;
    }

    public String getTaskDescription() {
        return login;
    }

    public String getTaskDate() {
        return name;
    }

    public String getTaskPriority() {
        return opisanie;
    }

    public void setEmployeeName(String Name) {
        this.siteName = Name;
    }

    public void setEmployeeDesig(String Description) {
        this.login = Description;
    }

    public void setEmployeeSalary(String Date) {
        this.name = Date;
    }

    public void setPrior(String Priority) {
        this.opisanie = Priority;
    }
}
