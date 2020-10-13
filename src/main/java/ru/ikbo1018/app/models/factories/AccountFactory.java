package ru.ikbo1018.app.models.factories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.models.account.Account;
import ru.ikbo1018.app.models.account.Moderator;
import ru.ikbo1018.app.models.account.User;

@Component
public class AccountFactory {

    @Value("${userRoleId}")
    private int userRoleId;

    @Value("${moderatorRoleId}")
    private int moderatorRoleId;

    public Account create(int accountRoleId) {
        if(accountRoleId == userRoleId)
            return new User();
        else if(accountRoleId == moderatorRoleId)
            return new Moderator();
        else
            throw new RuntimeException("Unknown " + accountRoleId + " account role ID");
    }
}
