package io.javabrains.springbootquickstart.courseapidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/admin/orders")
    public List<Order> getAllOrders()
    {
        return orderService.getAllOrders();
    }

    @RequestMapping("/admin/orders/{id}")
    public Order getOrder(@PathVariable Integer id)
    {
        return orderService.getOrder(id);
    }

    //Create the mappers for the new services here
/*
    @RequestMapping(method= RequestMethod.POST, value="/user/orders")//change it to admin
    public void addOrder(@RequestBody Order order)
    {
        orderService.addOrder(order);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/user/orders/{order_id}")
    public void updateOrder(@RequestBody Order order,@PathVariable Integer id)
    {
        orderService.updateOrder(id,order);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/user/orders/{id}")
    public void deleteOrder(@PathVariable Integer user_id,@PathVariable Integer id)
    {
        orderService.deleteOrder(id);
    }*/

}