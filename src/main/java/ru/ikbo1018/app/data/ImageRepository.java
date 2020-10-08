package ru.ikbo1018.app.data;

import ru.ikbo1018.app.models.Image;

import java.sql.SQLException;

public interface ImageRepository {
    Image create(final Image image) throws IllegalArgumentException;
}
