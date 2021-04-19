package com.petsadoption.controllers;

import com.petsadoption.common.User;
import com.petsadoption.common.Pet;
import com.petsadoption.common.PetCategory;
import com.petsadoption.exceptions.PetsAdoptionException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.petsadoption.controllers.parameters.AddPetRequest;
import com.petsadoption.controllers.parameters.UpdatePetRequest;
import com.petsadoption.services.PetService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(UrlMappings.PETS_URL)
@CrossOrigin(value ="http://localhost:3000")
public class PetController {

    public String uploadDirect = System.getProperty("user.dir") + "/uploads";
    private final static Logger logger = Logger.getLogger(PetController.class);
    private PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/pet")
    public Pet addPet(@RequestBody AddPetRequest addPetRequest) throws PetsAdoptionException {
        if (!addRequestValidation(addPetRequest)) {
            logger.error("Invalid input" + addPetRequest);
            throw new PetsAdoptionException("Invalid input " + addPetRequest);
        }
        if(addPetRequest.getName() == null){
            addPetRequest.setName("");
        }
        Pet newPet = petService.addPet(addPetRequest.getName(), addPetRequest.getAge(), addPetRequest.getColor(),
                addPetRequest.getWeight(), addPetRequest.isAvailability(), addPetRequest.getDescription(), addPetRequest.getCategory(),
                addPetRequest.getPicture_link(), addPetRequest.getUserId());

        logger.info("Added successfully " + addPetRequest.toString());
        return newPet;
    }

    @GetMapping("/pet/{id}")
    public Pet getPet(@PathVariable String id) throws PetsAdoptionException {
        if (StringUtils.isEmpty(id)) {
            logger.error("Invalid input" + id);
            throw new PetsAdoptionException("Invalid input " + id);
        }
        Pet pet = petService.getPet(id);
        logger.info("Get successfully pet " + pet);
        return pet;
    }

    @GetMapping(value = "/get/all")
    public List<Pet> getAllPets() {
        List<Pet> pets = petService.getAllPets();
        logger.info("Get all pets successfully " + pets);
        return pets;
    }

    @GetMapping(value = "/categories")
    public List<String> getCategories() {
        List<PetCategory> categories = petService.getAllCategories();
        List<String> stringCategories = categories.stream().map(PetCategory::name).collect(Collectors.toList());
        logger.info("Get Categories list successfully" + stringCategories);
        return stringCategories;
    }

    @GetMapping(value = "/category/{category}")
    public List<Pet> getPetsByCategory(@PathVariable String category) throws PetsAdoptionException {

        if (StringUtils.isEmpty(category) || PetCategory.getPetCategory(category) == null) {
            logger.error("Invalid input category " + category);
            throw new PetsAdoptionException("Invalid input category " + category);
        }
        List<Pet> pets = petService.getPetsByCategory(category);
        logger.info("Get pets by category " + category + " successfully");
        return pets;
    }

    @GetMapping(value = "/pet/user/{petId}")
    public User getUserByPetId(@PathVariable String petId) throws PetsAdoptionException {
        User user = petService.getUser(petId);
        logger.info("get user by pet is successfully" + user);
        return user;
    }

    @PutMapping("/pet/{id}")
    public Pet updatePet(@RequestBody UpdatePetRequest updateRequest, @PathVariable String id) throws PetsAdoptionException {
        if (!updateRequestValidation(updateRequest, id)) {
            logger.error("Invalid input " + updateRequest);
            throw new PetsAdoptionException("Invalid input " + updateRequest);
        }
        if(updateRequest.getName()==null){
            updateRequest.setName("");
        }
        Pet updatedPet = petService.updatePet(id, updateRequest.getName(), updateRequest.getAge(), updateRequest.getWeight(),
                updateRequest.isAvailability(), updateRequest.getDescription(), updateRequest.getCategory(),
                updateRequest.getPicture_link());
        logger.info("Updated successfully " + updatedPet);
        return updatedPet;
    }

    @DeleteMapping("/pet/{id}")
    public String deletePet(@PathVariable String id) throws PetsAdoptionException {
        if (StringUtils.isEmpty(id)) {
            logger.error("Invalid input id " + id);
            throw new PetsAdoptionException("Invalid input id " + id);
        }
        petService.removePet(id);
        logger.info("Deleted pet with id " + id + " successfully");
        return id;
    }

    @PostMapping("/pet/uploadImage")
    public String uploadImage(@RequestParam("imageFile") MultipartFile image) throws IOException {
      String returnVal = "";
      try {
          petService.uploadFile(image);
      }catch (Exception e){
          logger.error("Cant find image");
      }
      return returnVal;
    }

    private boolean addRequestValidation(AddPetRequest addPetRequest) {

        return !( StringUtils.isEmpty(addPetRequest.getColor())  || StringUtils.isEmpty(addPetRequest.getDescription()) ||
                StringUtils.isEmpty(addPetRequest.getCategory()) || PetCategory.getPetCategory(addPetRequest.getCategory()) == null
                || StringUtils.isEmpty(addPetRequest.getPicture_link()) || StringUtils.isEmpty(addPetRequest.getUserId())
                );
    }

    private boolean updateRequestValidation(UpdatePetRequest updatepetRequest, String id) {

        return !(StringUtils.isEmpty(id) || StringUtils.isEmpty(updatepetRequest.getDescription()) ||
                StringUtils.isEmpty(updatepetRequest.getPicture_link()) || StringUtils.isEmpty(updatepetRequest.getCategory())
                || PetCategory.getPetCategory(updatepetRequest.getCategory()) == null);
    }
}