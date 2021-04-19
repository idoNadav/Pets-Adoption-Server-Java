package com.petsadoption.controllers.parameters;

import java.util.Objects;

public class AddUserRequest {

    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String validationQuestion;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddUserRequest that = (AddUserRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(password, that.password) &&
                Objects.equals(validationQuestion, that.validationQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phoneNumber, password, validationQuestion);
    }

    @Override
    public String toString() {
        return "AddUserRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", validationQuestion='" + validationQuestion + '\'' +
                '}';
    }
}