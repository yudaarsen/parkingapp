package ru.ikbo1018.app.models.account;

import org.springframework.format.annotation.DateTimeFormat;
import ru.ikbo1018.app.models.AccountRole;

import java.util.Date;

public class Account {
    private int id;
    private String firstName;
    private String lastName;
    private String midName;
    private String email;
    private String password;
    @DateTimeFormat(pattern = "MM/dd/YYYY")
    private Date regDate;
    private AccountRole accountRole;

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
        String tmp = firstName.toLowerCase();
        this.firstName = tmp.substring(0, 1).toUpperCase() + tmp.substring(1);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
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

    public AccountRole getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(AccountRole accountRole) {
        this.accountRole = accountRole;
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
}
