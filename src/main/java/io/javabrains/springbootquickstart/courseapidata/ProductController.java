package io.javabrains.springbootquickstart.courseapidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/products")
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @RequestMapping("/products/{id}")
    public Product getProduct(@PathVariable Integer id)
    {
        return productService.getProduct(id);
    }

    @RequestMapping(method= RequestMethod.POST, value="/products")
    public void addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/products/{id}")
    public void updateProduct(@RequestBody Product product, @PathVariable Integer id)
    {
        productService.updateProduct(id,product);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/products/{id}")
    public void deleteProduct(@PathVariable Integer id)
    {
        productService.deleteProduct(id);
    }

}
