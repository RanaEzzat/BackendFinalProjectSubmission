package io.javabrains.springbootquickstart.courseapidata.services;

import io.javabrains.springbootquickstart.courseapidata.errors.NonAdminAccessException;
import io.javabrains.springbootquickstart.courseapidata.errors.UserDoesNotExistException;
import io.javabrains.springbootquickstart.courseapidata.errors.UserNameTakenException;
import io.javabrains.springbootquickstart.courseapidata.models.User;
import io.javabrains.springbootquickstart.courseapidata.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    private List<User> users = new ArrayList<>(Arrays.asList(
            ));

    public List<User> getAllUsers()
    {
        List<User> users = new ArrayList<>();
        userRepository.findAll()
                .forEach(users::add);
        return users;
    }

    public User getUser(Integer id)
    {

        try {
            User user = userRepository.findById(id).get();
        }
        catch(NoSuchElementException e)
        {
            throw new UserDoesNotExistException("No user with the following id "+ id + " exists in our database." +
                    "Please enter a valid user id.");
        }
        User user = userRepository.findById(id).get();
        return user;
    }

    public void addUser(User user)
    {
        boolean flag=false;
        List<User> users=getAllUsers();
        for(int i=0;i<users.size();i++)
        {
            if(user.getUserName().equals(users.get(i).getUserName())&&user.getId()!=users.get(i).getId())
                flag=true;
        }

        if(!flag)
            userRepository.save(user);
        else
        {
            throw new UserNameTakenException("Please try using another username because the username you entered" +
                    " is already taken by another user");
        }
    }


    public void updateUser(Integer id, User user)
    {
        userRepository.save(user);
    }

    public void deleteUser(Integer id)
    {
        try {
            User user = userRepository.findById(id).get();
        }
        catch(NoSuchElementException e)
        {
            throw new UserDoesNotExistException("No user with the following id "+ id + " exists in our database." +
                    "Please enter a valid user id.");
        }

        userRepository.deleteById(id);
    }



}
