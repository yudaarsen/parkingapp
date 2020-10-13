package ru.ikbo1018.app.data;

import ru.ikbo1018.app.models.Status;

import java.util.List;

public interface StatusRepository {
    Status getById(int id) throws IllegalArgumentException;
    List<Status> getAll();
}
