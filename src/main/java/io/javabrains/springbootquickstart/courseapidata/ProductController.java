package io.javabrains.springbootquickstart.courseapidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/user/{user_id}/products")
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @RequestMapping("/user/{user_id}/products/{id}")
    public Product getProduct(@PathVariable Integer id)
    {
        return productService.getProduct(id);
    }

    @RequestMapping(method= RequestMethod.POST, value="/admin/{admin_id}/products")//change it to admin
    public void addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
    }

    @RequestMapping(method= RequestMethod.GET, value="/admin/{admin_id}/products")//change it to admin
    public List<Product> getProductsForAdmin()
    {
        return productService.getAllProducts();
    }

    @RequestMapping(method= RequestMethod.GET, value="/admin/{admin_id}/products/{product_id}")//change it to admin
    public Product getProductForAdmin(@PathVariable Integer product_id)
    {
        return productService.getProduct(product_id);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/admin/{admin_id}/products/{id}")//change it to admin
    public void updateProduct(@RequestBody Product product,@PathVariable Integer id)
    {
        productService.updateProduct(id,product);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/admin/{admin_id}/products/{id}")//change it to admin
    public void deleteProduct(@PathVariable Integer id)
    {
        productService.deleteProduct(id);
    }


}
