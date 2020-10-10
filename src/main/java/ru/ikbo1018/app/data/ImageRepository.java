package ru.ikbo1018.app.data;

import ru.ikbo1018.app.models.Image;

import java.sql.SQLException;
import java.util.List;

public interface ImageRepository {
    Image create(final Image image) throws IllegalArgumentException;
    List<Image> getAppealImages(int appealId) throws IllegalArgumentException;
    Image getImage(int imageId) throws IllegalArgumentException;
}
