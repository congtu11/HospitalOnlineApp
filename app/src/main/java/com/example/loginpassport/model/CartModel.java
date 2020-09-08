package com.example.loginpassport.model;

public class CartModel {
    private int id;
    private int amount;
    private String name_medicine;
    private int price;
    private String avatar;
    private int checkout_code;
    public  CartModel() {

    }
    public CartModel(int id,int amount,int checkout_code, String name_medicine, int price,String avatar) {
        this.id=id;
        this.name_medicine=name_medicine;
        this.price=price;
        this.checkout_code=checkout_code;
        this.amount=amount;
        this.avatar=avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_medicine() {
        return name_medicine;
    }

    public void setName_medicine(String name_medicine) {
        this.name_medicine = name_medicine;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getCheckout_code() {
        return checkout_code;
    }

    public void setCheckout_code(int checkout_code) {
        this.checkout_code = checkout_code;
    }
}
