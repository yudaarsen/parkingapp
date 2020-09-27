package ru.ikbo1018.app.data;

import ru.ikbo1018.app.models.Type;

public interface TypeRepository {
    Type getById(int id) throws IllegalArgumentException;
}
