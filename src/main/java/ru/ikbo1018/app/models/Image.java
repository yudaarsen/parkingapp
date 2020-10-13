package ru.ikbo1018.app.models;

import java.sql.Blob;

public class Image {
    private int id;
    private int appealId;
    private byte[] data;

    public Image() {
    }

    public Image(Image obj) {
        this.setId(obj.getId());
        this.setAppealId(obj.getAppealId());
        this.setData(obj.getData());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppealId() {
        return appealId;
    }

    public void setAppealId(int appealId) {
        this.appealId = appealId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
