package rollMyAPI.services;

import rollMyAPI.exceptions.NoAPIKey;
import rollMyAPI.mapper.UserMapper;
import rollMyAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    //==================================================
    //add new user and generate API Key
    public User insertUser(User user) {

        user.setApiKey(generateKey());
        userMapper.insertUser(user);

        System.out.println(user.toString());
        return userMapper.getByName(user.getFirst_name());
    }

    //==================================================

    public static String generateKey(){

        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        byte[] encoded = secretKey.getEncoded();
        return DatatypeConverter.printHexBinary(encoded).toLowerCase();
    }
//=========================================================

    public boolean authenticate(String apiKey) throws NoAPIKey {

        if (apiKey.isEmpty() || apiKey == null){
            throw new NoAPIKey("You do not have an API Key");
        }

        boolean isValid = userMapper.validateUserApiKey(apiKey);

        if(isValid){

            return true;
        }
        return false;
    }

    //==========================================================

    //update user by its id
    public User updateById(User user) {
        userMapper.updateUser(user);
        return userMapper.getByName(user.getFirst_name());
    }

    //delete
    public User deleteById(int id) {
        userMapper.deleteUser(id);
        return userMapper.getByID(id);
    }

    //get all users using mybatis
    public ArrayList<User> getAllUsers (){
        return userMapper.getAllUsers();
    }

    //get user by id
    public User getById(int id){
        return userMapper.getByID(id);
    }



}

