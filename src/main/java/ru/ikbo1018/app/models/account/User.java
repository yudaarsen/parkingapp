package ru.ikbo1018.app.models.account;

public class User extends Account{
    @Override
    public boolean moderate() {
        return false;
    }
}
