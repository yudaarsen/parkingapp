package ru.ikbo1018.app.services;

import ru.ikbo1018.app.models.Appeal;

import java.util.List;

public interface AppealService {
    List<Appeal> getAccountAppeals(int accountId) throws IllegalArgumentException;
    Appeal createAppeal(Appeal appeal) throws IllegalArgumentException;
}
