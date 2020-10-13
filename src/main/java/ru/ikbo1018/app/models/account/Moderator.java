package ru.ikbo1018.app.models.account;

public class Moderator extends Account{
    @Override
    public boolean moderate() {
        return true;
    }
}
