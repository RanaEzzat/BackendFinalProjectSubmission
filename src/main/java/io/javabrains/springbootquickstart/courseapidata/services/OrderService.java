package io.javabrains.springbootquickstart.courseapidata.services;

import io.javabrains.springbootquickstart.courseapidata.errors.OrderDoesNotExistException;
import io.javabrains.springbootquickstart.courseapidata.errors.ProductDoesNotExistException;
import io.javabrains.springbootquickstart.courseapidata.errors.UserDoesNotExistException;
import io.javabrains.springbootquickstart.courseapidata.models.Order;
import io.javabrains.springbootquickstart.courseapidata.models.Product;
import io.javabrains.springbootquickstart.courseapidata.models.User;
import io.javabrains.springbootquickstart.courseapidata.repositories.OrderRepository;
import io.javabrains.springbootquickstart.courseapidata.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;





    //user/{user_id}/product/{product_id} DONE
    public void addToCart(Integer user_id,Integer product_id)
    {
        try
        {
            userService.getUser(user_id);
        }
        catch(NoSuchElementException e)
        {
            throw new UserDoesNotExistException("You can't add to the cart of a user that does not exist.");
        }

        try
        {
            productService.getProduct(product_id);
        }
        catch(NoSuchElementException e)
        {
            throw new ProductDoesNotExistException("You can't add a product that does not exist to the cart.");
        }

        User user=userService.getUser(user_id);
        List<Order> userOrders=user.getOrders();
        int numberOfUserOrders=userOrders.size();
        int numberOfOrders=getAllOrders().size();
        boolean doesUserHaveACartOrder = false;
        Order order= new Order();

        if(numberOfUserOrders>0) {
            for (int i = 0; i < userOrders.size(); i++) {
                if (userOrders.get(i).getType().equals("cart"))
                {
                    doesUserHaveACartOrder = true;
                    order=userOrders.get(i);
                    break;
                }
            }
            if (!doesUserHaveACartOrder) {
                order=new Order(numberOfOrders+1,"cart","0",new ArrayList<Product>());

            }
        }
        else
        {
            order=new Order(numberOfOrders+1,"cart","0",new ArrayList<Product>());
        }

        List<Product> productsInOrder=order.getProducts();
        productsInOrder.add(productService.getProduct(product_id));
        int price=productService.getProduct(product_id).getPrice();
        int orderTotal=Integer.parseInt(order.getTotal())+price;
        order.setTotal(""+orderTotal);
        order.setProducts(productsInOrder);
        orderRepository.save(order);

        if(!doesUserHaveACartOrder)
        {
            userOrders.add(order);
        }

        userService.updateUser(user_id,userService.getUser(user_id));
    }

    //DELETE "user/{user_id}/order/{product_oder}"
    public void removeFromCart(Integer user_id,Integer product_id)
    {
        User user = userService.getUser(user_id);
        Order order= getUserCartOrder(user_id);
        List<Product> productsInOrder=order.getProducts();
        Product product;
        boolean productFound=false;
        for(int i=0;i<productsInOrder.size();i++)
        {
            if(productsInOrder.get(i).getId()==product_id)
            {
                product=productsInOrder.get(i);
                productsInOrder.remove(product);
                int price=product.getPrice();
                int totalPrice=Integer.parseInt(order.getTotal())-price;
                order.setProducts(productsInOrder);
                order.setTotal(""+totalPrice);
                productFound=true;
                break;
            }
        }

        if(!productFound)
        {
            throw new ProductDoesNotExistException("The product that you are trying to remove from the cart" +
                    " does not exist in the cart.");
        }

        orderRepository.save(order);
        userService.updateUser(user_id,userService.getUser(user_id));
    }

    //GET /admin/orders/
    public List<Order> getAllOrders()
    {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll()
                .forEach(orders::add);
        return orders;
    }

    //GET /admin/orders/{id}
    public Order getOrder(Integer order_id)
    {
        try
        {
            orderRepository.findById(order_id).get();
        }
        catch(NoSuchElementException e)
        {
            throw new OrderDoesNotExistException("No order with the following id "+ order_id+" exists.");
        }
        return orderRepository.findById(order_id).get();
    }

    //GET /user/{user_id}/order
    public Order getUserCartOrder(Integer user_id)
    {
        try
        {
            userService.getUser(user_id);
        }
        catch(NoSuchElementException e)
        {
            throw new UserDoesNotExistException("The user id that you are trying " +
                    "to get their cart order does not exist");
        }
        List<Order> userOrders=userService.getUser(user_id).getOrders();
        for(int i=0;i<userOrders.size();i++)
        {
            if(userOrders.get(i).getType().equals("cart"))
                return userOrders.get(i);
        }
        return null;
    }

    //GET /user/{user_id}/order_history
    public List<Order> getAllUserOrders(Integer user_id)
    {
        try{
            userService.getUser(user_id);
        }
        catch(NoSuchElementException exception) {
            System.out.println("The user id that you are trying " +
                  "to get their cart order history does not exist");
        };
        List<Order> userOrders=userService.getUser(user_id).getOrders();
        return userOrders;
    }

    //PUT /user/{user_id}/order
    public Order checkOutOrder(Integer user_id)
    {
        Order order;
        try
        {
            userService.getUser(user_id);
        }
        catch(NoSuchElementException e)
        {
            throw new UserDoesNotExistException("You must be logged in to checkout.");
        }

        try
        {
            order= getUserCartOrder(user_id);
            order.setType("purchased"); //No longer in cart
            orderRepository.save(order);
            userService.updateUser(user_id,userService.getUser(user_id));

        }
        catch(NullPointerException e)
        {
            throw new OrderDoesNotExistException("You can't check out when your cart is empty.");
        }



        return order;
    }

}