package io.javabrains.springbootquickstart.courseapidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
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
        return productRepository.findById(id).get();
    }

    public void addProduct(Product product)
    {
            productRepository.save(product);
    }

    public void updateProduct(Integer id,Product product)
    {
            productRepository.save(product);
    }

    public void deleteProduct(Integer id)
    {
            productRepository.deleteById(id);
    }
}
