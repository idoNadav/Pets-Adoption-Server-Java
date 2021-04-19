package com.petsadoption.controllers;

import com.petsadoption.controllers.parameters.LogInRequest;
import com.petsadoption.exceptions.PetsAdoptionException;
import com.petsadoption.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMappings.LOG_IN)
@CrossOrigin(value = "http://localhost:3000")
public class LogInController {

    private final static Logger logger = Logger.getLogger(PetController.class);

    private UserService userService;

    @Autowired
    public LogInController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public boolean logIn(@RequestBody LogInRequest logInRequest )throws PetsAdoptionException {
        if(!logInRequestValidation(logInRequest)){
            logger.error("Invalid input " + logInRequest);
            throw new PetsAdoptionException("Invalid input " + logInRequest);
        }
        if(!userService.logIn(logInRequest.getEmail() , logInRequest.getPassword())){
            logger.error("Email or password are incorrect :" + " " + logInRequest);
            return false;
        }
        logger.info("Logged in successfully" + logInRequest);
        return true;
    }

    private boolean logInRequestValidation(LogInRequest logInRequest){
        return !( StringUtils.isEmpty(logInRequest.getEmail())|| StringUtils.isEmpty(logInRequest.getPassword())||
                !logInRequest.getEmail().contains("@"));
    }
}
