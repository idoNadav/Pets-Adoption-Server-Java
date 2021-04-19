package com.petsadoption.services;


import com.petsadoption.common.User;
import com.petsadoption.common.PetCategory;
import com.petsadoption.exceptions.PetsAdoptionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petsadoption.common.Pet;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class PetService {

    private final static Logger logger = Logger.getLogger(PetService.class);

    private DbServiceFactory dbServiceFactory;
    private IDbService dbService;

    @Autowired
    public PetService(DbServiceFactory dbServiceFactory) {
        this.dbServiceFactory = dbServiceFactory;
    }

    @PostConstruct
    public void init() {
        dbService = dbServiceFactory.getDbService();
    }

    public Pet addPet(String name, int age, String color, double weight, boolean availability, String description,
                      String category, String picture_link, String userId) throws PetsAdoptionException {

        if (!dbService.validateUserId(userId)) {
            logger.error("The user id : " + "  " + userId + " " + "is not exist ");
            throw new PetsAdoptionException("The user id : " + "  " + userId + " " + "is not exist ");
        }
        String id = UUID.randomUUID().toString();
        User user = dbService.getUser(userId);
        Pet newPet = new Pet(id, name, age, color, weight, true, description, category, picture_link, userId, user.getName());
        dbService.addPet(newPet);
        logger.debug("Add pet successfully : " + id);
        return newPet;
    }

    public Pet getPet(String petId) throws PetsAdoptionException {
        if (!dbService.validatePetId(petId)) {
            logger.error("The pet id" + petId + "is not exist ");
            throw new PetsAdoptionException("The pet id" + petId + "is not exist ");
        }
        Pet pet = dbService.getPet(petId);
        logger.debug("Get pet successfully :" + petId);
        return pet;
    }

    public List<Pet> getAllPets() {
        List<Pet> pets = dbService.getAllPets();
        logger.debug("Get all pets successfully :" + pets);
        return pets;
    }

    public List<PetCategory> getAllCategories() {
        List<PetCategory> categories = Arrays.asList(PetCategory.values());
        logger.info("Get pet categories successfully: " + categories);
        return categories;
    }

    public User getUser(String petId) throws PetsAdoptionException {
        if (!dbService.validatePetId(petId)) {
            logger.error("The pet id is not exist :" + " " + petId);
            throw new PetsAdoptionException(("The pet id is not exist :" + " " + petId));
        }
        User user = dbService.getUserByPetId(petId);
        logger.debug("Get user by pet id successfully");
        return user;
    }

    public List<Pet> getPetsByCategory(String category) {
        List<Pet> pets = dbService.getPetsByCategory(category);
        logger.debug("Get pet by category successfully :" + category);
        return pets;
    }

    public Pet updatePet(String id, String name, int age, double weight, boolean availability, String description,
                         String category, String picture_link) throws PetsAdoptionException {

        if (!dbService.validatePetId(id)) {
            logger.error("The pet id is not exist :" + " " + id);
            throw new PetsAdoptionException("The pet id is not exist :" + " " + id);
        }
        Pet pet = dbService.updatePet(id, name, age, weight, availability, description, category, picture_link);
        logger.debug("Successfully update pet " + pet);
        return pet;
    }

    public void removePet(String petId) throws PetsAdoptionException {
        if (!dbService.validatePetId(petId)) {
            logger.error("The pet id is not exist :" + " " + petId);
            throw new PetsAdoptionException("The id is not exist :" + petId);
        }
        dbService.removePet(petId);
        logger.debug("Successfully removed pet : " + petId);
    }

    public void uploadFile(MultipartFile image) throws IOException {
        String projectPath = System.getProperty("user.dir");
        BufferedReader reader = new BufferedReader(new FileReader(projectPath+"\\pets-adoption\\src\\main\\java\\com\\petsadoption\\controllers\\parameters\\root_path"));
        String root = reader.readLine();
        String file_path = root + image.getOriginalFilename();
        reader.close();
        byte[] bytes = image.getBytes();
        Path path = Paths.get(file_path);
        Files.write(path, bytes);
    }

}