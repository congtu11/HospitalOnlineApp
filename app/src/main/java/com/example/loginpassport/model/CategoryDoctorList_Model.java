package com.example.loginpassport.model;

public class CategoryDoctorList_Model {
    private String title;
    private int image_item;
    private int numberEvent;

    public CategoryDoctorList_Model(String title, int image_item, int numberEvent) {
        super();
        this.title=title;
        this.image_item=image_item;
        this.numberEvent=numberEvent;
    }

    public int getImage_item() {
        return image_item;
    }

    public String getTitle() {
        return title;
    }

    public void setImage_item(int image_item) {
        this.image_item = image_item;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberEvent() {
        return numberEvent;
    }

    public void setNumberEvent(int numberEvent) {
        this.numberEvent = numberEvent;
    }
}
