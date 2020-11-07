package io.javabrains.springbootquickstart.courseapidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/users")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @RequestMapping("/users/{id}")
    public User getUser(@PathVariable Integer id)
    {
        return userService.getUser(id);
    }

    @RequestMapping(method= RequestMethod.POST, value="/users")//change it to admin
    public void addUser(@RequestBody User user)
    {
        userService.addUser(user);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/users/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Integer id)
    {
        userService.updateUser(id,user);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/users/{id}")
    public void deleteUser(@PathVariable Integer id)
    {
        userService.deleteUser(id);
    }

}
