package io.javabrains.springbootquickstart.courseapidata;

import io.javabrains.springbootquickstart.courseapidata.interfaces.OrderControllerInterface;
import io.javabrains.springbootquickstart.courseapidata.models.Order;
import io.javabrains.springbootquickstart.courseapidata.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

     public void addToCart(@PathVariable Integer user_id,@PathVariable Integer product_id)
    {
        orderService.addToCart(user_id,product_id);
    }

    public Order getUserCartOrder(@PathVariable Integer user_id)
    {
        return orderService.getUserCartOrder(user_id);
    }

    public void removeFromCart(@PathVariable Integer user_id,@PathVariable Integer product_id)
    {
        orderService.removeFromCart(user_id,product_id);
    }

    public List<Order> getAllUserOrders(@PathVariable Integer user_id)
    {
        return orderService.getAllUserOrders(user_id);
    }

    public Order checkOutOrder(@PathVariable Integer user_id)
    {
        return orderService.checkOutOrder(user_id);
    }

}