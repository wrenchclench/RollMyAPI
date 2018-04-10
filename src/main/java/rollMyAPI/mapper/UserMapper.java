package rollMyAPI.mapper;

import rollMyAPI.model.User;
import org.apache.ibatis.annotations.*;
import java.util.ArrayList;

@Mapper
public interface UserMapper {

    String GET_ALL__ACTIVE_USERS = "SELECT * FROM `mybatis-test`.Users where isActive = 1";
    String GET_BY_ID = "SELECT * FROM `mybatis-test`.Users where id = #{id}";
    String INSERT_USER = "INSERT INTO `mybatis-test`.Users (first_name, last_name, isActive, age, apiKey) " +
            "VALUES (#{first_name}, #{last_name}, #{isActive}, #{age}, #{apiKey})";
    String UPDATE_USER = "UPDATE `mybatis-test`.Users SET first_name = #{first_name}, " +
            "last_name = #{last_name}, isActive = #{isActive} WHERE id = #{id}";
    String DELETE_USER = "UPDATE `mybatis-test`.Users set isActive = 0 WHERE id = #{id}";
    String GET_BY_NAME = "SELECT * FROM `mybatis-test`.Users where first_name = #{first_name}";
    String CUSTOM_QUERY = "SELECT * FROM `mybatis-test`.users where age = #{age}";
    String CUSTOM_QUERY2 = "SELECT * FROM `mybatis-test`.users where last_name = #{last_name}";
    String VALIDATE_KEY = "SELECT isActive from `mybatis-test`.users where apiKey = #{apiKey}";

    @Select(GET_BY_NAME)
    public User getByName(String name);

    @Select(GET_ALL__ACTIVE_USERS)
    public ArrayList<User> getAllUsers();

    @Select(GET_BY_ID)
    public User getByID(int id);

    @Select(CUSTOM_QUERY)
    public ArrayList<User> getUserByAge(int age);

    @Select(CUSTOM_QUERY2)
    public ArrayList<User> getUserByLastName();

    @Insert(INSERT_USER)
    public int insertUser (User user);

    @Update(UPDATE_USER)
    public int updateUser (User user);

    @Delete(DELETE_USER)
    public int deleteUser(int id);

    @Select(VALIDATE_KEY)
    boolean validateUserApiKey(String apiKey);
}
