package ru.ikbo1018.app.models.account;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public abstract class Account {
    private int id;
    private String firstName;
    private String lastName;
    private String midName;
    private String email;
    private String password;
    @DateTimeFormat(pattern = "MM/dd/YYYY")
    private Date regDate;
    private int accountRoleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.length() == 0) {
            this.firstName = "";
            return;
        }
        String tmp = firstName.toLowerCase();
        this.firstName = tmp.substring(0, 1).toUpperCase() + tmp.substring(1);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.length() == 0) {
            this.lastName = "";
            return;
        }
        String tmp = lastName.toLowerCase();
        this.lastName = tmp.substring(0,1).toUpperCase() + tmp.substring(1);
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        String tmp = midName.toLowerCase();
        this.midName = tmp.substring(0, 1).toUpperCase() + tmp.substring(1);
    }

    public int getAccountRoleId() {
        return accountRoleId;
    }

    public void setAccountRoleId(int accountRoleId) {
        this.accountRoleId = accountRoleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public abstract boolean moderate();
}
