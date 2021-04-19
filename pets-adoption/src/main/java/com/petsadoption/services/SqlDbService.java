package com.petsadoption.services;

import com.petsadoption.common.User;
import com.petsadoption.common.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class SqlDbService implements IDbService {

    private JdbcTemplate jdbcTemplate ;

    @Autowired
    public SqlDbService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users(userId , name , email , phoneNumber , password , validation_question) VALUES( ? , ? , ? , ? , ? , ?)";
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getPassword() , user.getValidationQuestion());
    }

    @Override
    public User getUser(String userId) {
        String sql = "select* from users where userId = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new UserMapper());
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT* FROM users";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public List<Pet> getPetsByUserId(String userId) {
        String sql = "select* from pets where userId=?";
        return jdbcTemplate.query(sql, new PetMapper(), userId);
    }

    @Override
    public User updateUser(String id, String name, String email, String phoneNumber, String password) {
        String sql = "update users set name=?  , email=?  , phoneNumber=? , password=? where userId=?";
        jdbcTemplate.update(sql, name, email, phoneNumber, password, id);
        jdbcTemplate.update("update users set userId=? where userId=?" , email , id);
        return getUser(email);
    }

    @Override
    public void updatePassword(String userId , String password){
        String sql = "update users set password=?  where userId=?";
        jdbcTemplate.update(sql,password , userId);
    }

    @Override
    public void removeUser(String userId) {
        String deletePetsQuery = "delete from pets where userId=?";
        jdbcTemplate.update(deletePetsQuery, userId);
        String deleteQuery = "delete from users where userId = ?";
        jdbcTemplate.update(deleteQuery, userId);
    }

    @Override
    public void addPet(Pet pet) {
        String sql = "INSERT INTO PETS(petId , name , age , color , weight ,availability ,description, category , picture_link , userName , userId) " +
                "VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,?)";
        jdbcTemplate.update(sql, pet.getId(), pet.getName(), pet.getAge(), pet.getColor(), pet.getWeight(),
                pet.getAvailability(), pet.getDescription(), pet.getCategory(), pet.getPicture_link(), pet.getUserName(), pet.getUserId());
    }

    @Override
    public Pet getPet(String petId) {
        String sql = "select* from pets where petId = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{petId}, new PetMapper());
    }

    @Override
    public User getUserByPetId(String petId) {
        String sql = "select userId from pets where petId=?";
        String userId = jdbcTemplate.queryForObject(sql, new Object[]{petId}, String.class);
        return getUser(userId);
    }

    @Override
    public List<Pet> getAllPets() {
        String sql = "select* from pets where availability =true";
        return jdbcTemplate.query(sql, new PetMapper());
    }

    @Override
    public void removePet(String petId) {
        String deleteQuery = "delete from pets where petId = ?";
        jdbcTemplate.update(deleteQuery, petId);
    }

    @Override
    public Pet updatePet(String petId, String name, int age, double weight, boolean availability, String description,
                         String category, String picture_link) {
        String sql = "update pets set name=? , age=? , weight=? , availability=? , description=? , category=? , picture_link=?   where petId=?";
        jdbcTemplate.update(sql, name, age, weight, availability, description, category, picture_link, petId);
        return getPet(petId);
    }

    @Override
    public List<Pet> getPetsByCategory(String category) {
        String sql = "select* from pets where availability =true and category=?";
        return jdbcTemplate.query(sql, new Object[]{category}, new PetMapper());
    }

    @Override
    public boolean logInValidate(String email, String password) {
        String sql = "select count(*) from users where userId=? and password=?";
        return (jdbcTemplate.queryForObject(sql, new Object[]{email, password}, Integer.class) > 0);
    }

    @Override
    public boolean validateUserId(String userId) {
        String sql = "select count(*) from users where userId=?";
        return (jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class) > 0);
    }

    @Override
    public boolean validatePetId(String petId) {
        String sql = "select count(*) from pets where petId=? ";
        return (jdbcTemplate.queryForObject(sql, new Object[]{petId}, Integer.class) > 0);
    }

    void removeAllRecords(){
        String query1 = "SET FOREIGN_KEY_CHECKS = 0; ";
        String query2 = "TRUNCATE table users; ";
        String query3 = "TRUNCATE table pets; ";
        String query4= "SET FOREIGN_KEY_CHECKS = 1;";
        jdbcTemplate.update(query1);
        jdbcTemplate.update(query2);
        jdbcTemplate.update(query3);
        jdbcTemplate.update(query4);
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return new User(resultSet.getString("userId"), resultSet.getString("name"),
                    resultSet.getString("email"), resultSet.getString("phoneNumber"),
                    resultSet.getString("Password") , resultSet.getString("validation_question"));
        }
    }

    private static final class PetMapper implements RowMapper<Pet> {

        @Override
        public Pet mapRow(ResultSet resultSet, int rowNum) throws SQLException {

            return new Pet(resultSet.getString("petId"), resultSet.getString("name"),
                    resultSet.getInt("age"), resultSet.getString("color"), resultSet.getDouble("weight"),
                    resultSet.getBoolean("availability"), resultSet.getString("description"), resultSet.getString("category"),
                    resultSet.getString("picture_link"), resultSet.getString("userId"), resultSet.getString("userName"));
        }
    }
}