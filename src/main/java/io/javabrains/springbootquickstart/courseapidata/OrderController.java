package io.javabrains.springbootquickstart.courseapidata;

import io.javabrains.springbootquickstart.courseapidata.interfaces.OrderControllerInterface;
import io.javabrains.springbootquickstart.courseapidata.models.Order;
import io.javabrains.springbootquickstart.courseapidata.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OrderController implements OrderControllerInterface {
    @Autowired
    private OrderService orderService;

    public List<Order> getAllOrders()
    {
        return orderService.getAllOrders();
    }

    public Order getOrder(@PathVariable Integer id)
    {
        return orderService.getOrder(id);
    }

     public void addToCart(@RequestHeader (name="Authorization") String token, @PathVariable Integer product_id)
    {
        orderService.addToCart(token,product_id);
    }

    public Order getUserCartOrder(@RequestHeader (name="Authorization") String token)
    {
        return orderService.getUserCartOrder(token);
    }

    public void removeFromCart(@RequestHeader (name="Authorization") String token,@PathVariable Integer product_id)
    {
        orderService.removeFromCart(token,product_id);
    }

    public List<Order> getAllUserOrders(@RequestHeader (name="Authorization") String token)
    {
        return orderService.getAllUserOrders(token);
    }

    public Order checkOutOrder(@RequestHeader (name="Authorization") String token)
    {

        return orderService.checkOutOrder(token);
    }

}