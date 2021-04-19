package com.petsadoption;

import com.petsadoption.controllers.PetController;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetsAdoptionApplication {

    private final static Logger logger = Logger.getLogger(PetController.class);

    public static void main(String[] args) {
        SpringApplication.run(PetsAdoptionApplication.class, args);
        BasicConfigurator.configure();
        logger.info("PAS is up and running...");
    }
}
