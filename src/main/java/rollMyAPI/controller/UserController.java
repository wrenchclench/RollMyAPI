package rollMyAPI.controller;

import java.util.ArrayList;

import org.codehaus.groovy.control.messages.ExceptionMessage;
import rollMyAPI.exceptions.NoAPIKey;
import rollMyAPI.exceptions.UnauthorizedException;
import rollMyAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rollMyAPI.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    //RequestMapping maps URLs to methods

    //Get
    @RequestMapping("/")
    public ArrayList<User> getUsers(@RequestParam("api-key") String apiKey) throws UnauthorizedException, NoAPIKey {

        if (userService.authenticate(apiKey)) {
            return userService.getAllUsers();
        } else {
            throw new UnauthorizedException("API key invalid");
        }
    }

    @RequestMapping("/{id}")
    public User getById(@PathVariable(value="id")int id) {

        return userService.getById(id);
    }


    //==========================================================

    //Create
    @RequestMapping(method = RequestMethod.POST, value = "/new")
    public User addNew(@RequestBody User user) {

        return userService.insertUser(user);
    }

    //==========================================================

    //Update
    @RequestMapping(method = RequestMethod.PATCH, value = "/")
    public User updateById(@RequestBody User user) {

        return userService.updateById(user);
    }

    //Delete
    @RequestMapping(method= RequestMethod.DELETE, value="/")
    public User deleteById(@RequestParam(value="id")int id){
        return userService.deleteById(id);
    }


}
