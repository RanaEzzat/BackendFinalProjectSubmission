package io.javabrains.springbootquickstart.courseapidata.services;

import io.javabrains.springbootquickstart.courseapidata.errors.NonAdminAccessException;
import io.javabrains.springbootquickstart.courseapidata.errors.ProductDoesNotExistException;
import io.javabrains.springbootquickstart.courseapidata.models.Product;
import io.javabrains.springbootquickstart.courseapidata.models.User;
import io.javabrains.springbootquickstart.courseapidata.repositories.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.Logger;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;

    private static final Logger logger = LogManager.getLogger(ProductService.class);

    public List<Product> getAllProducts()
    {
        logger.info("Entering the method getAllProducts() in class ProductService:");
        logger.info("No inputs");
    List<Product> products = new ArrayList<>();
        productRepository.findAll()
            .forEach(products::add);

        logger.info("Output: "+products);
    return products;
    }

    public Product getProduct(Integer id)
    {
        logger.info("Entering the method getProduct() in class ProductService:");
        logger.info("Input: id:"+id);
        try{
            productRepository.findById(id).get();
        }
        catch(NoSuchElementException e)
        {
            logger.error("The method getProduct() returned a ProductDoesNotExistException.");
            throw new ProductDoesNotExistException("There is no product with the following id "+id+" in our website, please" +
                    " reenter the id of the product you're looking for");
        }
        Product product= productRepository.findById(id).get();
        logger.info("Output: "+product);
        return product;
    }

    public boolean isAdmin(Integer admin_id)
    {
        logger.info("Entering the method isAdmin() in class ProductService:");
        logger.info("Input: admin_id:"+admin_id);
        String[] userRoles = userService.getUser(admin_id).getRoles().split(",");
        for(int i=0;i<userRoles.length;i++)
        {
            if(userRoles[i].equals("ROLE_ADMIN"))
            {
                logger.info("Output: true");
                return true;
            }
        }
        logger.info("Output: false");
        return false;
    }
    public void addProduct(Product product,Integer admin_id)
    {
        logger.info("Entering the method addProduct() in class ProductService:");
        logger.info("Input: product:"+product+" admin_id:"+admin_id);
        if(!(isAdmin(admin_id)))
        {
            logger.error("The method addProduct() returned a NonAdminAccessException.");

            throw new NonAdminAccessException("Your id "+ admin_id + " is not an admin id!"+
                    "You are trying to access a feature that is only allowed for admins to use!");
        }

        productRepository.save(product);
        logger.info("Output: success");
    }

    public void updateProduct(Integer id,Product product,Integer admin_id)
    {
        logger.info("Entering the method updateProduct() in class ProductService:");
        logger.info("Input: id:" +id+" product:"+product+" admin_id:"+admin_id);
        if(!(isAdmin(admin_id)))
        {
            logger.error("The method updateProduct() returned a NonAdminAccessException.");
            throw new NonAdminAccessException("Your id "+ admin_id + " is not an admin id!"+
                    "You are trying to access a feature that is only allowed for admins to use!");
        }
            productRepository.save(product);
        logger.info("Output: success");
    }

    public void deleteProduct(Integer id,Integer admin_id)
    {
        logger.info("Entering the method deleteProduct() in class ProductService:");
        logger.info("Input: id:"+id+" admin_id:"+admin_id);
        if(!(isAdmin(admin_id)))
        {
            logger.error("The method deleteProduct() returned a NonAdminAccessException.");
            throw new NonAdminAccessException("Your id "+ admin_id + " is not an admin id!"+
                    "You are trying to access a feature that is only allowed for admins to use!");
        }
        productRepository.deleteById(id);
        logger.info("Output: success");
    }
}
