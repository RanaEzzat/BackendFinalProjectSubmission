package io.javabrains.springbootquickstart.courseapidata.interfaces;

import io.javabrains.springbootquickstart.courseapidata.models.Order;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface OrderControllerInterface {


    @RequestMapping("/admin/{admin_id}/orders")
    public List<Order> getAllOrders();

    @RequestMapping("/admin/{admin_id}/orders/{id}")
    public Order getOrder(@PathVariable Integer id);

    @RequestMapping(method= RequestMethod.PUT, value="user/products/{product_id}")//change it to admin
    public void addToCart(@RequestHeader (name="Authorization") String token,@PathVariable Integer product_id);

    @RequestMapping(method= RequestMethod.GET,value="/user/order")
    public Order getUserCartOrder(@RequestHeader (name="Authorization") String token);


    @RequestMapping(method= RequestMethod.DELETE, value="user/order/{product_id}")//change it to admin
    public void removeFromCart(@RequestHeader (name="Authorization") String token,@PathVariable Integer product_id);

    @RequestMapping(method= RequestMethod.GET,value="/user/order_history")
    public List<Order> getAllUserOrders(@RequestHeader (name="Authorization") String token);

    @RequestMapping(method= RequestMethod.PUT, value="user/order")
    public Order checkOutOrder(@RequestHeader(name="Authorization") String token);

}
