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

    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "Burger", "Grilled beef patty with Mayo, Ketchup and fresh lettuce.",
                    50, "https://i.pinimg.com/originals/c0/45/99/c04599f05e5f30e6fc69b3b7a66a10d7.jpg", 800),
            new Product(2, "Pizza", "Italian pizza topped with pepperoni and mozzarella!",
                    150, "https://cdn.shopify.com/s/files/1/0248/2411/9343/products/Pepperoni_1024x1024.png?v=1574684715", 1500)
    ));

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

    public void updateProduct(Integer id, Product product)
    {
        productRepository.save(product);
    }

    public void deleteProduct(Integer id)
    {
        productRepository.deleteById(id);
    }
}
