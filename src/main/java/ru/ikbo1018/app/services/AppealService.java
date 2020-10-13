package ru.ikbo1018.app.services;

import ru.ikbo1018.app.models.Appeal;
import ru.ikbo1018.app.models.Image;
import ru.ikbo1018.app.models.Status;

import java.util.List;

public interface AppealService {
    List<Appeal> getAccountAppeals(int accountId) throws IllegalArgumentException;
    Appeal createAppeal(Appeal appeal) throws IllegalArgumentException;
    Appeal getAppeal(int appealId) throws IllegalArgumentException;
    Appeal updateAppeal(Appeal appeal) throws IllegalArgumentException;
    List<Image> getAppealImages(int appealId);
    Appeal getUncheckedAppeal() throws IllegalArgumentException;
}
