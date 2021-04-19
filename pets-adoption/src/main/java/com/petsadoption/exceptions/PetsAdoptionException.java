package com.petsadoption.exceptions;

public class PetsAdoptionException extends Exception {

    public PetsAdoptionException(String msg){
        super(msg);
    }
    public PetsAdoptionException(String msg, Throwable cause) {
        super(msg, cause);
    }

}