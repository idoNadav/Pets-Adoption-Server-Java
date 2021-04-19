package com.petsadoption.services;

import com.petsadoption.common.User;
import com.petsadoption.common.Pet;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InMemoryDbService implements IDbService {

    private HashMap<String, Pet> petsDb = new HashMap<>();
    private HashMap<String, User> userDb = new HashMap<>();

    @Override
    public void addPet(Pet pet) {
        petsDb.put(pet.getId(), pet);
        userDb.get(pet.getUserId()).addToPetsList(pet.getId());
    }

    @Override
    public Pet getPet(String petId) {
        return petsDb.get(petId);
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        petsDb.forEach((id, pet) -> {
            if (pet.getAvailability()) {
                pets.add(pet);
            }
        });
        return pets;
    }

    @Override
    public List<Pet> getPetsByUserId(String userId) {
        List<String> petsIds = userDb.get(userId).getPetsList();
        return petsIds.stream().map(petsDb::get).collect(Collectors.toList());
    }

    @Override
    public List<Pet> getPetsByCategory(String category) {
        List<Pet> pets = new ArrayList<>();
        petsDb.forEach((id, pet) -> {
            if (category.equals(pet.getCategory()) && pet.getAvailability())
                pets.add(pet);
        });
        return pets;
    }

    @Override
    public void updatePassword(String userId , String password){
            userDb.get(userId).setPassword(password);
    }

    @Override
    public Pet updatePet(String petId, String name, int age, double weight, boolean availability, String description,
                         String category, String picture_link) {
        Pet pet = petsDb.get(petId);
        pet.setName(name);
        pet.setAge(age);
        pet.setWeight(weight);
        pet.setAvailability(availability);
        pet.setDescription(description);
        pet.setCategory(category);
        pet.setPicture_link(picture_link);
        return pet;
    }

    @Override
    public void removePet(String petId) {
        Pet removed = petsDb.remove(petId);
        userDb.get(removed.getUserId()).getPetsList().remove(petId);
    }

    @Override
    public void addUser(User user) {
        userDb.put(user.getId(), user);
    }

    @Override
    public User getUser(String userId) {
        return userDb.get(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userDb.values());
    }

    @Override
    public User getUserByPetId(String petId) {
        return userDb.get(petsDb.get(petId).getUserId());
    }

    @Override
    public User updateUser(String id, String name, String email, String phoneNumber, String password) {
        User user = new User(email ,name , email ,phoneNumber , password ,"");
        List<String> petsList = userDb.get(id).getPetsList();
        user.setPetsList(petsList);
        userDb.remove(id);
        userDb.put(email , user);
        return user;
    }

    @Override
    public void removeUser(String userId) {
        User removed = userDb.remove(userId);
        petsDb.keySet().removeAll(removed.getPetsList());
    }

    @Override
    public boolean validateUserId(String userId) {
        return userDb.containsKey(userId);
    }

    @Override
    public boolean validatePetId(String petId) {
        return petsDb.containsKey(petId);
    }

    @Override
    public boolean logInValidate(String email, String password) {
        if (!userDb.containsKey(email)){
            return false;
        }
        User user = userDb.get(email);
        return (user.getEmail().equals(email) && user.getPassword().equals(password));
    }
}
