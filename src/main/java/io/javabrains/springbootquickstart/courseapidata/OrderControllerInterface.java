package io.javabrains.springbootquickstart.courseapidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface OrderControllerInterface {


    @RequestMapping("/admin/orders")
    public List<Order> getAllOrders();

    @RequestMapping("/admin/orders/{id}")
    public Order getOrder(@PathVariable Integer id);

    @RequestMapping(method= RequestMethod.PUT, value="user/{user_id}/product/{product_id}")//change it to admin
    public void addToCart(@PathVariable Integer user_id,@PathVariable Integer product_id);

    @RequestMapping(method= RequestMethod.GET,value="/user/{user_id}/order")
    public Order getUserCartOrder(@PathVariable Integer user_id);


    @RequestMapping(method= RequestMethod.DELETE, value="user/{user_id}/order/{product_id}")//change it to admin
    public void removeFromCart(@PathVariable Integer user_id,@PathVariable Integer product_id);

    @RequestMapping(method= RequestMethod.GET,value="/user/{user_id}/order_history")
    public List<Order> getAllUserOrders(@PathVariable Integer user_id);

    @RequestMapping(method= RequestMethod.PUT, value="user/{user_id}/order")
    public void checkOutOrder(@PathVariable Integer user_id);

}
