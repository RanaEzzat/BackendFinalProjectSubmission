package io.javabrains.springbootquickstart.courseapidata;

import io.javabrains.springbootquickstart.courseapidata.interfaces.ProductControllerInterface;
import io.javabrains.springbootquickstart.courseapidata.models.Product;
import io.javabrains.springbootquickstart.courseapidata.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController implements ProductControllerInterface {
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

    public void addProduct(@RequestBody Product product, @PathVariable Integer admin_id)
    {
        productService.addProduct(product,admin_id);
    }

    public List<Product> getProductsForAdmin()
    {
        return productService.getAllProducts();
    }


    public Product getProductForAdmin(@PathVariable Integer product_id)
    {
        return productService.getProduct(product_id);
    }

    public void updateProduct(@RequestBody Product product,@PathVariable Integer id,@PathVariable Integer admin_id)
    {
        productService.updateProduct(id,product,admin_id);
    }

    public void deleteProduct(@PathVariable Integer id,@PathVariable Integer admin_id)
    {
        productService.deleteProduct(id,admin_id);
    }


}
