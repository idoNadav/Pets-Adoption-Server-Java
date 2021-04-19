package com.petsadoption.controllers;

import com.petsadoption.common.User;
import com.petsadoption.common.Pet;
import com.petsadoption.controllers.parameters.AddUserRequest;
import com.petsadoption.controllers.parameters.UpdatePasswordRequest;
import com.petsadoption.controllers.parameters.UpdateUserRequest;
import com.petsadoption.exceptions.PetsAdoptionException;
import com.petsadoption.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(UrlMappings.USERS_URL)
@CrossOrigin(value = "http://localhost:3000")
public class UserController {

    private final static Logger logger = Logger.getLogger(PetController.class);
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public User addUser(@RequestBody AddUserRequest addUserRequest) throws PetsAdoptionException {
        logger.error("Invalid input" + addUserRequest);
        if (!addUserRequestValidation(addUserRequest)) {
            logger.error("Invalid input" + addUserRequest);
            throw new PetsAdoptionException("Invalid input " + addUserRequest);
        }
        User newUser = userService.addUser(addUserRequest.getName(), addUserRequest.getEmail(),
                addUserRequest.getPhoneNumber(), addUserRequest.getPassword() , addUserRequest.getValidationQuestion());
        logger.info("Added successfully " + addUserRequest.toString());
        return newUser;
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable String userId) throws PetsAdoptionException {
        if (StringUtils.isEmpty(userId)) {
            logger.error("Invalid input user id:" + userId);
            throw new PetsAdoptionException("Invalid input user id " + userId);
        }
       User user = userService.getUser(userId);
       logger.info("Get successfully user " + userId);
       return user;
    }

    @GetMapping("/get/all")
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        logger.info("Get all users successfully " + users);
        return users;
    }

    @GetMapping("/user/pets/{userId}")
    public List<Pet> getPetsByUser(@PathVariable String userId) throws PetsAdoptionException {
        if (StringUtils.isEmpty(userId)) {
            logger.error("Invalid input user id " + userId);
            throw new PetsAdoptionException("Invalid input user id " + userId);
        }
        List<Pet> pets = userService.getPetsByUser(userId);
        logger.info("Get pets list by user id successfully" + pets);
        return pets;
    }

    @PutMapping("/user/{userId}")
    public User updateUser(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable String userId) throws PetsAdoptionException {
        if (!updateUserRequestValidation(updateUserRequest, userId)) {
            logger.error("Invalid input" + updateUserRequest);
            throw new PetsAdoptionException("Invalid input " + updateUserRequest);
        }
       User updateUser = userService.updateUser(userId, updateUserRequest.getName(), updateUserRequest.getEmail(),
               updateUserRequest.getPhoneNumber(), updateUserRequest.getPassword());
        logger.info("Updated successfully " + updateUser);
        return updateUser;
    }

    @PutMapping("/user/password/{userId}")
    public boolean updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest, @PathVariable String userId)  throws PetsAdoptionException{
        return (!userService.updatePassword(userId , updatePasswordRequest.getNewPassword() , updatePasswordRequest.getValidationQuestion()));
    }

    @DeleteMapping("/user/{userId}")
    public String removeUser(@PathVariable String userId) throws PetsAdoptionException {
        if (StringUtils.isEmpty(userId)) {
            logger.error("Invalid input id " + userId);
            throw new PetsAdoptionException("Invalid input id " + userId);
        }
        userService.removeUser(userId);
        logger.info("Deleted user with id " + userId + " successfully");
        return userId;
    }
    
    private boolean updateUserRequestValidation(UpdateUserRequest updateUserRequest, String userId) {
        return !(StringUtils.isEmpty(updateUserRequest.getName()) || StringUtils.isEmpty(updateUserRequest.getEmail()) ||
                StringUtils.isEmpty(updateUserRequest.getPhoneNumber()) || StringUtils.isEmpty(updateUserRequest.getPassword()) ||
                StringUtils.isEmpty(userId));
    }

    private boolean addUserRequestValidation(AddUserRequest addUserRequest) {
        return !(StringUtils.isEmpty(addUserRequest.getName()) || StringUtils.isEmpty(addUserRequest.getEmail()) ||
                StringUtils.isEmpty(addUserRequest.getPhoneNumber()) || StringUtils.isEmpty(addUserRequest.getPassword()) ||
                !addUserRequest.getEmail().contains("@"));
    }
}
