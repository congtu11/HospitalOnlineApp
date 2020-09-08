package com.example.loginpassport.model;

public class NewsModel {
    private int id;
    private String title;
    private String content1;
    private String img1;
    private  String content2;
    private String img2;
    private String created_at;
    private  String updated_at;
    public  NewsModel(int id, String title, String content1, String img1,String content2, String img2 , String created_at, String updated_at) {
        this.id=id;
        this.title=title;
        this.content1=content1;
        this.content2=content2;
        this.img1=img1;
        this.img2=img2;
        this.created_at=created_at;
        this.updated_at=updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
