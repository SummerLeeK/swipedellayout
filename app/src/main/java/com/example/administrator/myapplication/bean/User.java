package com.example.administrator.myapplication.bean;

public class User {

    private String user;

    private int age;

    private String card;

    public User(String user, int age, String card) {
        this.user = user;
        this.age = age;
        this.card = card;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", age=" + age +
                ", card='" + card + '\'' +
                '}';
    }
}
