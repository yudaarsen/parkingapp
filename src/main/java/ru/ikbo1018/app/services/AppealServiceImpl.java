package ru.ikbo1018.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ikbo1018.app.data.AppealRepository;
import ru.ikbo1018.app.data.ImageRepository;
import ru.ikbo1018.app.models.Appeal;
import ru.ikbo1018.app.models.Image;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppealServiceImpl implements AppealService{

    @Autowired
    private AppealRepository repository;

    @Autowired
    private ImageRepository imageRepository;

    public List<Appeal> getAccountAppeals(int accountId) throws IllegalArgumentException {
        return repository.getAccountAppealsById(accountId);
    }

    @Override
    public Appeal createAppeal(Appeal appeal) throws IllegalArgumentException {
        return repository.create(appeal);
    }

    @Override
    public Appeal getAppeal(int appealId) throws IllegalArgumentException {
        return repository.getAppeal(appealId);
    }

    @Override
    public Appeal updateAppeal(Appeal appeal) throws IllegalArgumentException {
        return repository.updateAppeal(appeal);
    }

    @Override
    public List<Image> getAppealImages(int appealId) {
        try {
            return imageRepository.getAppealImages(appealId);
        } catch (IllegalArgumentException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Appeal getUncheckedAppeal() throws IllegalArgumentException{
        return repository.getUncheckedAppeal();
    }
}
