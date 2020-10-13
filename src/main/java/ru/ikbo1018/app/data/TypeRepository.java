package ru.ikbo1018.app.data;

import ru.ikbo1018.app.models.Type;

import java.util.List;

public interface TypeRepository {
    Type getById(int id) throws IllegalArgumentException;
    List<Type> getAll();
}
