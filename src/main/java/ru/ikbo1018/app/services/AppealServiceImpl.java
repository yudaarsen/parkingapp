package ru.ikbo1018.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.data.AppealRepository;
import ru.ikbo1018.app.models.Appeal;

import java.util.List;

@Component
public class AppealServiceImpl implements AppealService{

    @Autowired
    private AppealRepository repository;

    public List<Appeal> getAccountAppeals(int accountId) throws IllegalArgumentException {
        return repository.getAccountAppealsById(accountId);
    }

    @Override
    public Appeal createAppeal(Appeal appeal) throws IllegalArgumentException {
        return repository.create(appeal);
    }
}
