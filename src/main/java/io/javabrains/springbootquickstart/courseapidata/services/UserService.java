package io.javabrains.springbootquickstart.courseapidata.services;

import io.javabrains.springbootquickstart.courseapidata.errors.NonAdminAccessException;
import io.javabrains.springbootquickstart.courseapidata.errors.UserDoesNotExistException;
import io.javabrains.springbootquickstart.courseapidata.errors.UserNameTakenException;
import io.javabrains.springbootquickstart.courseapidata.models.User;
import io.javabrains.springbootquickstart.courseapidata.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
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

    private static final Logger logger = LogManager.getLogger(UserService.class);

    public List<User> getAllUsers()
    {
        logger.info("Entering the method getAllUsers() in class UserService.");
        logger.info("No inputs");
        List<User> users = new ArrayList<>();
        userRepository.findAll()
                .forEach(users::add);
        logger.info("Output: "+users);
        return users;
    }

    public User decodeToken(String token)
    {
        System.out.println("HELLLLLLLLLLLO   "+ token);
        token=token.substring(7);
        System.out.println("HELLLLLLLLLLLO   "+ token);
        String payload = token.split("\\.")[1];
        try {
            String usernamestr=new String(Base64.decodeBase64(payload),"UTF-8");
            System.out.println(usernamestr);
            String sub=usernamestr.split(",")[0];
            System.out.println(sub);
            String usersub=sub.split(":")[1];
            System.out.println(usersub);
            String username="";
            for(int i=1;i<usersub.length()-1;i++)
            {
                username+=usersub.charAt(i);
            }
            System.out.println(username);
            List<User> users=getAllUsers();
            for(int i=0;i<users.size();i++)
            {
                if(users.get(i).getUserName().equals(username))
                    return users.get(i);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User getUser(int id)
    {

        logger.info("Entering the method getUser() in class UserService.");
        logger.info("Input: id:"+id);

        try {
            User user = userRepository.findById(id).get();
        }
        catch(NoSuchElementException e)
        {
            logger.error("The method getUser() returned a UserDoesNotExistException.");
            throw new UserDoesNotExistException("No user with the following id "+ id + " exists in our database." +
                    "Please enter a valid user id.");
        }
        User user = userRepository.findById(id).get();
        logger.info("Output: "+user);
        return user;
    }

    public void addUser(User user)
    {
        logger.info("Entering the method addUser() in class UserService.");
        logger.info("Input: user:"+ user);

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
            logger.error("The method addUser() returned a UserNameTakenException.");

            throw new UserNameTakenException("Please try using another username because the username you entered" +
                    " is already taken by another user");
        }
        logger.info("Success");
    }


    public void updateUser(Integer id, User user)
    {
        logger.info("Entering the method updateUser() in class UserService.");
        logger.info("Inputs: id:"+id+ " user:"+user);
        userRepository.save(user);
        logger.info("Success");
    }

    public void deleteUser(Integer id)
    {
        logger.info("Entering the method deleteUser() in class UserService.");
        logger.info("Input: id:"+id);
        try {
            User user = userRepository.findById(id).get();
        }
        catch(NoSuchElementException e)
        {
            logger.error("The method deleteUser() returned a UserDoesNotExistException.");
            throw new UserDoesNotExistException("No user with the following id "+ id + " exists in our database." +
                    "Please enter a valid user id.");
        }

        userRepository.deleteById(id);
        logger.info("Success");
    }



}
