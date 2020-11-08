package io.javabrains.springbootquickstart.courseapidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController implements UserControllerInterface {
    @Autowired
    private UserService userService;

    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    public User getUser(@PathVariable Integer id)
    {
        return userService.getUser(id);
    }

    public void addUser(@RequestBody User user)
    {
        userService.addUser(user);
    }

    public void updateUser(@RequestBody User user, @PathVariable Integer id)
    {
        userService.updateUser(id,user);
    }

    public void deleteUser(@PathVariable Integer id)
    {
        userService.deleteUser(id);
    }

}
