package io.javabrains.springbootquickstart.courseapidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController implements ProductControllerInterface{
    @Autowired
    private ProductService productService;

    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    public Product getProduct(@PathVariable Integer id)
    {
        return productService.getProduct(id);
    }

    public void addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
    }

    public List<Product> getProductsForAdmin()
    {
        return productService.getAllProducts();
    }

    public Product getProductForAdmin(@PathVariable Integer product_id)
    {
        return productService.getProduct(product_id);
    }

    public void updateProduct(@RequestBody Product product,@PathVariable Integer id)
    {
        productService.updateProduct(id,product);
    }

    public void deleteProduct(@PathVariable Integer id)
    {
        productService.deleteProduct(id);
    }


}
