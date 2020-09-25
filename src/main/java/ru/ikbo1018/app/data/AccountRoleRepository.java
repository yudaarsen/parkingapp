package ru.ikbo1018.app.data;

import ru.ikbo1018.app.models.AccountRole;

public interface AccountRoleRepository {
    AccountRole getAccountRoleById(int id) throws IllegalArgumentException;
}
