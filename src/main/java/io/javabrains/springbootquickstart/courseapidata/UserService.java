package io.javabrains.springbootquickstart.courseapidata;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        return userRepository.findById(id).get();
    }

    public void addUser(User user)
    {
        boolean flag=false;
        List<User> users=getAllUsers();
        for(int i=0;i<users.size();i++)
        {
            if(user.getUserName()==users.get(i).getUserName()&&user.getId()!=users.get(i).getId())
                flag=true;
        }
        if(!flag)
            userRepository.save(user);
        //if flag==true the username is already taken
    }


    public void updateUser(Integer id, User user)
    {
        userRepository.save(user);
    }

    public void deleteUser(Integer id)
    {
        userRepository.deleteById(id);
    }



}
