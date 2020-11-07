package io.javabrains.springbootquickstart.courseapidata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders()
    {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll()
                .forEach(orders::add);
        return orders;
    }

    public Order getOrder(Integer id)
    {
        return orderRepository.findById(id).get();
    }

    public void addOrder(Order order)
    {
        orderRepository.save(order);
    }

    public void updateOrder(Integer id, Order order)
    {
        orderRepository.save(order);
    }

    public void deleteOrder(Integer id)
    {
        orderRepository.deleteById(id);
    }
}