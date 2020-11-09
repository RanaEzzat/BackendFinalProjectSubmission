package io.javabrains.springbootquickstart.courseapidata.interfaces;

import io.javabrains.springbootquickstart.courseapidata.models.Product;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductControllerInterface {
    @RequestMapping("/user/products")
    public List<Product> getAllProducts();

    @RequestMapping("/user/products/{id}")
    public Product getProduct(@PathVariable Integer id);

    @RequestMapping(method= RequestMethod.POST, value="/admin/products")//change it to admin
    public void addProduct(@RequestBody Product product,@RequestHeader(name="Authorization") String token);

    @RequestMapping(method= RequestMethod.GET, value="/admin/products")//change it to admin
    public List<Product> getProductsForAdmin();

    @RequestMapping(method= RequestMethod.GET, value="/admin/products/{product_id}")//change it to admin
    public Product getProductForAdmin(@PathVariable Integer product_id);

    @RequestMapping(method= RequestMethod.PUT, value="/admin/products/{id}")//change it to admin
    public void updateProduct(@RequestBody Product product, @PathVariable Integer id, @RequestHeader (name="Authorization") String token);


    @RequestMapping(method= RequestMethod.DELETE, value="/admin/products/{id}")//change it to admin
    public void deleteProduct(@PathVariable Integer id,@RequestHeader (name="Authorization") String token);

}
