package ru.ikbo1018.app.data;

import ru.ikbo1018.app.models.Appeal;

import java.util.List;

public interface AppealRepository {
    List<Appeal> getAccountAppealsById(int accountId) throws IllegalArgumentException;
}
