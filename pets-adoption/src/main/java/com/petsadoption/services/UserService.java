package com.petsadoption.services;

import com.petsadoption.common.User;
import com.petsadoption.common.Pet;
import com.petsadoption.exceptions.PetsAdoptionException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService {

    private final static Logger logger = Logger.getLogger(PetService.class);
    private DbServiceFactory dbServiceFactory;
    private IDbService dbService;

    @Autowired
    public UserService(DbServiceFactory dbServiceFactory) {
        this.dbServiceFactory = dbServiceFactory;
    }

    @PostConstruct
    public void init (){
        dbService = dbServiceFactory.getDbService();
    }

    public User addUser(String name, String email, String phoneNumber, String password , String validationQuestion) throws PetsAdoptionException {
        if (dbService.validateUserId(email)) {
            logger.error("The user exists in the system" + email);
            throw new PetsAdoptionException("The user :" + " "+ email +" "+ "exists in the system " );
        }
        User newUser = new User(email, name, email, phoneNumber, password , validationQuestion);
        dbService.addUser(newUser);
        logger.debug("Added user successfully" + newUser);
        return newUser;
    }

    public User getUser(String userId) throws PetsAdoptionException {
        if (!dbService.validateUserId(userId)) {
            logger.error("The id is not exist :" + " " + userId);
            throw new PetsAdoptionException(("The id is not exist :" + " " + userId));
        }
        User user = dbService.getUser(userId);
        logger.debug("Get user by id :" + userId + "successfully");
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = dbService.getAllUsers();
        logger.debug("Get all users successfully" + users);
        return users;
    }

    public List<Pet> getPetsByUser(String userId) throws PetsAdoptionException {
        if (!dbService.validateUserId(userId)) {
            logger.error("The id is not exist :" + " " + userId);
            throw new PetsAdoptionException(("The id is not exist :" + " " + userId));
        }
        List<Pet> petsList = dbService.getPetsByUserId(userId);
        logger.debug("Get pets list by user id successfully" + petsList);
        return petsList;
    }

    public User updateUser(String id, String name, String email, String phoneNumber, String password) throws PetsAdoptionException {
        if (!dbService.validateUserId(id)) {
            logger.error("The id is not exist :" + " " + id);
            throw new PetsAdoptionException("The id is not exist :" + " " + id);
        }
        if(dbService.validateUserId(email)){
            logger.error("The user exists in the system" + email);
            throw new PetsAdoptionException("The user exists in the system :" + " " + email);
        }
        User user = dbService.updateUser(id, name, email, phoneNumber, password);
        logger.debug("Successfully update user " + user);
        return user;
    }

    public boolean updatePassword(String userId , String password , String validationQuestion) throws PetsAdoptionException{
        if (!dbService.validateUserId(userId)) {
            logger.error("The user not exists in the system" + userId);
            throw new PetsAdoptionException("The user not exists in the system :" + " " + userId);
        }

        if(!dbService.getUser(userId).getValidationQuestion().equals(validationQuestion)){
            logger.error("Wrong answer:" + " " + validationQuestion);
            throw new PetsAdoptionException("Wrong answer:" + " " + validationQuestion);
        }
        dbService.updatePassword(userId , password);
        return true;
    }

    public void removeUser(String userId) throws PetsAdoptionException {
        if (!dbService.validateUserId(userId)) {
            logger.error("The user id is not exist :" + " " + userId);
            throw new PetsAdoptionException("The user id is not exist :" + " " + userId);
        }
        dbService.removeUser(userId);
        logger.debug("Successfully removed user" + userId);
    }


    public boolean logIn(String email, String password) {
        return dbService.logInValidate(email, password);
    }
}
