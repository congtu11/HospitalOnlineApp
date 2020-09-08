package com.example.loginpassport.model;

public class Doctor_Model {
    private int id;
    private String name;
    private String category;
    private String email;
    private int id_doc_review;
    private String description;
    private String avt_dt;
    private float voting;
    private String address;
    private int phone;

    public Doctor_Model() {

    }

    public Doctor_Model(int id,
                        String name,
                        String category,
                        String email,
                        int id_doc_review,
                        String description,
                        String avt_dt,
                        float voting,
                        String address,
                        int phone

    ) {

        super();
        this.id=id;
        this.name = name;
        this.category = category;
        this.email=email;
        this.id_doc_review=id_doc_review;
        this.description = description;
        this.avt_dt = avt_dt;
        this.voting=voting;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_doc_review() {
        return id_doc_review;
    }

    public void setId_doc_review(int id_doc_review) {
        this.id_doc_review = id_doc_review;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvt_dt() {
        return avt_dt;
    }

    public void setAvt_dt(String avt_dt) {
        this.avt_dt = avt_dt;
    }

    public float getVoting() {
        return voting;
    }

    public void setVoting(float voting) {
        this.voting = voting;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
