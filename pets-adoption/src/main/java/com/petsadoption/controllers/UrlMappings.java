package com.petsadoption.controllers;

 class UrlMappings {

   private static final String BASE_PATH = "/pas/v1/admin";
   private static final String PET_ADOPTION = "/pets/adoption/";

   static final String PETS_URL = BASE_PATH + PET_ADOPTION + "pets";
   static final String USERS_URL = BASE_PATH + PET_ADOPTION + "users";
   static final String LOG_IN = BASE_PATH + PET_ADOPTION + "authentication";
}
