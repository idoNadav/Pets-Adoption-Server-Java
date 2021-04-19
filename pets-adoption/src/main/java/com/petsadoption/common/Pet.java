package com.petsadoption.common;

import java.util.Objects;

public class Pet {

    private String id;
    private String name;
    private int age;
    private String color;
    private double weight;
    private boolean availability;
    private String description;
    private String category;
    private String picture_link;
    private String userId;
    private String userName;

    public Pet(String id, String name, int age, String color, double weight, boolean availability,
               String description, String category, String picture_link, String userId, String userName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;
        this.weight = weight;
        this.availability = availability;
        this.description = description;
        this.category = category;
        this.picture_link = picture_link;
        this.userId = userId;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPicture_link() {
        return picture_link;
    }

    public void setPicture_link(String picture_link) {
        this.picture_link = picture_link;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return getAge() == pet.getAge() &&
                Double.compare(pet.getWeight(), getWeight()) == 0 &&
                getAvailability() == pet.getAvailability() &&
                Objects.equals(getId(), pet.getId()) &&
                Objects.equals(getName(), pet.getName()) &&
                Objects.equals(getColor(), pet.getColor()) &&
                Objects.equals(getDescription(), pet.getDescription()) &&
                Objects.equals(getCategory(), pet.getCategory()) &&
                Objects.equals(getPicture_link(), pet.getPicture_link()) &&
                Objects.equals(getUserId(), pet.getUserId()) &&
                Objects.equals(getUserName(), pet.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getColor(), getWeight(), getAvailability(), getDescription(), getCategory(), getPicture_link(), getUserId(), getUserName());
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", availability=" + availability +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", picture_link='" + picture_link + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}