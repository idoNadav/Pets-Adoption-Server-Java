package com.petsadoption.controllers.parameters;

import java.util.Objects;

public class UpdatePasswordRequest {
    String newPassword;
    String validationQuestion;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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
        UpdatePasswordRequest that = (UpdatePasswordRequest) o;
        return Objects.equals(newPassword, that.newPassword) &&
                Objects.equals(validationQuestion, that.validationQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newPassword, validationQuestion);
    }

    @Override
    public String toString() {
        return "UpdatePasswordRequest{" +
                "newPassword='" + newPassword + '\'' +
                ", validationQuestion='" + validationQuestion + '\'' +
                '}';
    }
}
