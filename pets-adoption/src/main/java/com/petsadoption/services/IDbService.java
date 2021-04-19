package com.petsadoption.services;

import com.petsadoption.common.User;
import com.petsadoption.common.Pet;

import java.util.List;

public interface IDbService {

    void addPet(Pet pet);

    Pet getPet(String petId);

    List<Pet> getAllPets();

    List<Pet> getPetsByCategory(String category);

    List<Pet> getPetsByUserId(String userId);

    Pet updatePet(String petId, String name, int age, double weight, boolean availability, String description,
                  String category, String picture_link);

    void removePet(String petId);

    void addUser(User user);

    User getUser(String userId);

    List<User> getAllUsers();

    User getUserByPetId(String petId);

    User updateUser(String id, String name, String email, String phoneNumber, String password);

    void removeUser(String userId);

    boolean validateUserId(String userId);

    boolean validatePetId(String petId);
    void updatePassword(String userId , String password );

    boolean logInValidate(String email , String password);

}
