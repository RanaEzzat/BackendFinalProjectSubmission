package io.javabrains.springbootquickstart.courseapidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/orders")
    public List<Order> getAllOrders()
    {
        return orderService.getAllOrders();
    }

    @RequestMapping("/orders/{id}")
    public Order getOrder(@PathVariable Integer id)
    {
        return orderService.getOrder(id);
    }

    @RequestMapping(method= RequestMethod.POST, value="/orders")//change it to admin
    public void addOrder(@RequestBody Order order)
    {
        orderService.addOrder(order);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/orders/{id}")
    public void updateOrder(@RequestBody Order order, @PathVariable Integer id)
    {
        orderService.updateOrder(id,order);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/orders/{id}")
    public void deleteOrder(@PathVariable Integer id)
    {
        orderService.deleteOrder(id);
    }

}
