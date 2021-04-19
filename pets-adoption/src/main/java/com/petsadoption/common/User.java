package com.petsadoption.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String validationQuestion;
    private List<String> petsList = new ArrayList<>();

    public User(String id, String name, String email, String phoneNumber, String password , String validationQuestion) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.validationQuestion = validationQuestion;
    }

    public void addToPetsList(String petId) {
        petsList.add(petId);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidationQuestion() {
        return validationQuestion;
    }

    public void setValidationQuestion(String validationQuestion) {
        this.validationQuestion = validationQuestion;
    }

    public List<String> getPetsList() {
        return petsList;
    }

    public void setPetsList(List<String> petsList) {
        this.petsList = petsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(password, user.password) &&
                Objects.equals(validationQuestion, user.validationQuestion) &&
                Objects.equals(petsList, user.petsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phoneNumber, password, validationQuestion, petsList);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", validationQuestion='" + validationQuestion + '\'' +
                ", petsList=" + petsList +
                '}';
    }
}