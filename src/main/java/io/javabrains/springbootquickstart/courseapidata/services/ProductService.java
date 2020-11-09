package io.javabrains.springbootquickstart.courseapidata.services;

import io.javabrains.springbootquickstart.courseapidata.errors.NonAdminAccessException;
import io.javabrains.springbootquickstart.courseapidata.errors.ProductDoesNotExistException;
import io.javabrains.springbootquickstart.courseapidata.models.Product;
import io.javabrains.springbootquickstart.courseapidata.models.User;
import io.javabrains.springbootquickstart.courseapidata.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;


    public List<Product> getAllProducts()
    {
    List<Product> products = new ArrayList<>();
        productRepository.findAll()
            .forEach(products::add);
    return products;
    }

    public Product getProduct(Integer id)
    {
        try{
            productRepository.findById(id).get();
        }
        catch(NoSuchElementException e)
        {
            throw new ProductDoesNotExistException("There is no product with the following id "+id+" in our website, please" +
                    " reenter the id of the product you're looking for");
        }
        Product product= productRepository.findById(id).get();
        return product;
    }

    public boolean isAdmin(Integer admin_id)
    {
        String[] userRoles = userService.getUser(admin_id).getRoles().split(",");
        for(int i=0;i<userRoles.length;i++)
        {
            if(userRoles[i].equals("ROLE_ADMIN"))
                return true;
        }
        return false;
    }
    public void addProduct(Product product,@PathVariable Integer admin_id)
    {
        if(!(isAdmin(admin_id)))
        {
            throw new NonAdminAccessException("Your id "+ admin_id + " is not an admin id!"+
                    "You are trying to access a feature that is only allowed for admins to use!");
        }

        productRepository.save(product);
    }

    public void updateProduct(Integer id,Product product,@PathVariable Integer admin_id)
    {
        if(!(isAdmin(admin_id)))
        {
            throw new NonAdminAccessException("Your id "+ admin_id + " is not an admin id!"+
                    "You are trying to access a feature that is only allowed for admins to use!");
        }
            productRepository.save(product);
    }

    public void deleteProduct(Integer id,@PathVariable Integer admin_id)
    {
        if(!(isAdmin(admin_id)))
        {
            throw new NonAdminAccessException("Your id "+ admin_id + " is not an admin id!"+
                    "You are trying to access a feature that is only allowed for admins to use!");
        }
        productRepository.deleteById(id);
    }
}
