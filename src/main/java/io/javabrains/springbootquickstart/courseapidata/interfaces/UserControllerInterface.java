package io.javabrains.springbootquickstart.courseapidata.interfaces;

import io.javabrains.springbootquickstart.courseapidata.models.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface UserControllerInterface {
    @RequestMapping("/users")
    public List<User> getAllUsers();

    @RequestMapping("/users/{id}")
    public User getUser(@PathVariable Integer id);

    @RequestMapping(method= RequestMethod.POST, value="/users")
    public void addUser(@RequestBody User user);

    @RequestMapping(method= RequestMethod.PUT, value="/users/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Integer id);

    @RequestMapping(method= RequestMethod.DELETE, value="/users/{id}")
    public void deleteUser(@PathVariable Integer id);

}
