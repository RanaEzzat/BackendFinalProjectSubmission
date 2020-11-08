package io.javabrains.springbootquickstart.courseapidata;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface ProductControllerInterface {
    @RequestMapping("/user/{user_id}/products")
    public List<Product> getAllProducts();

    @RequestMapping("/user/{user_id}/products/{id}")
    public Product getProduct(@PathVariable Integer id);

    @RequestMapping(method= RequestMethod.POST, value="/admin/{admin_id}/products")//change it to admin
    public void addProduct(@RequestBody Product product);

    @RequestMapping(method= RequestMethod.GET, value="/admin/{admin_id}/products")//change it to admin
    public List<Product> getProductsForAdmin();

    @RequestMapping(method= RequestMethod.GET, value="/admin/{admin_id}/products/{product_id}")//change it to admin
    public Product getProductForAdmin(@PathVariable Integer product_id);

    @RequestMapping(method= RequestMethod.PUT, value="/admin/{admin_id}/products/{id}")//change it to admin
    public void updateProduct(@RequestBody Product product,@PathVariable Integer id);


    @RequestMapping(method= RequestMethod.DELETE, value="/admin/{admin_id}/products/{id}")//change it to admin
    public void deleteProduct(@PathVariable Integer id);

}
