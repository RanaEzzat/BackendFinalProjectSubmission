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
    private UserRepository userRepository;
    private UserService userService;
    private ProductService productService;


    //Only the admin has access over all the orders
    public List<Order> getAllOrders()
    {
       List<Order> orders = new ArrayList<>();
       orderRepository.findAll()
                    .forEach(orders::add);
        return orders;
    }

    //Only the admin can access any order
    public Order getOrder(Integer order_id)
    {

        List<Order> orders = new ArrayList<>();
       orderRepository.findAll()
                    .forEach(orders::add);

        for(int i=0;i<orders.size();i++)
        {
            if(orders.get(i).getId()==order_id)
                return orders.get(i);
        }
        return null;
    }




    //user/{id}/order
    public List<Order> getAllUserOrders(Integer user_id)
    {
        List<Order> orders = getAllOrders();
        List<Order> allUserOrders = new ArrayList<>();
        for(int i=0;i<orders.size();i++)
        {
            if(orders.get(i).getId()==user_id)
                allUserOrders.add(orders.get(i));
        }
        return allUserOrders;
    }


    //user/{id}/order/{id}
    public Order getUserOrder(Integer user_id,Integer order_id)
    {
        List<Order> userOrders= getAllUserOrders(user_id);
        //or use this way: userRepository.findById(user_id).get().getOrders();
        for(int i=0;i<userOrders.size();i++)
        {
            if(userOrders.get(i).getId()==order_id)
                return userOrders.get(i);
        }
        return null;
    }

    //When user purchases a product:
    //Check if user has an order in their order list?
    //No? call addUserOrder and addProductToOrder.
    //Yes? check if the user has one order with type="cart" if yes-> call addProductToOrder
    //if no-> call addUserOrder and addProductToOrder.
    //then update the total of the order



    //user/{id}/product/{id}
    public void addProductToOrder(Integer user_id,Integer product_id)
    {
        List<Order> userOrders=getAllUserOrders(user_id);
        Order order=new Order();
        int numberOfUserOrders=userOrders.size();
        if(numberOfUserOrders>0) {
            boolean doesUserHaveACartOrder = false;
            for (int i = 0; i < userOrders.size(); i++) {
                if (userOrders.get(i).getType().equals("cart"))
                {
                    doesUserHaveACartOrder = true;
                    order=userOrders.get(i);
                    break;
                }
            }
            if (!doesUserHaveACartOrder) {
                order=new Order(numberOfUserOrders+1,"cart","0",userService.getUser(user_id),new ArrayList<Product>());
                addUserOrder(user_id,order);
            }
        }
        else
        {
            order=new Order(numberOfUserOrders+1,"cart","0",userService.getUser(user_id),new ArrayList<Product>());
            addUserOrder(user_id,order);
        }

        List<Product> productsInOrder=order.getProducts();
        productsInOrder.add(productService.getProduct(product_id));
        int price=productService.getProduct(product_id).getPrice();
        int orderTotal=Integer.parseInt(order.getTotal())+price;
        order.setTotal(""+orderTotal);
        order.setProducts(productsInOrder);
        updateUserOrder(user_id,order.getId());
        userService.updateUser(user_id,userService.getUser(user_id));
    }

    //user/{id}/order/{product}
    public void removeProductFromOrder(Integer user_id,Integer order_id,Integer product_id)
    {
        Order order= getOrder(order_id);
        List<Product> productsInOrder=order.getProducts();
        for(int i=0;i<productsInOrder.size();i++)
        {
            if(productsInOrder.get(i).getId()==product_id)
            {
                productsInOrder.remove(productsInOrder.get(i));
                break;
            }
        }
        int price=productService.getProduct(product_id).getPrice();
        int totalPrice=Integer.parseInt(order.getTotal())+price;
        order.setProducts(productsInOrder);
        order.setTotal(""+totalPrice);
        updateUserOrder(user_id,order.getId());
        userService.updateUser(user_id,userService.getUser(user_id));
    }


    //user/{id}/product/{id}
    public void addUserOrder(Integer user_id,Order order)
    {
        //add a new order and update the order list of user
        orderRepository.save(order);
        User user=userService.getUser(user_id);
        List<Order> usersOrders=user.getOrders();
        usersOrders.add(order);
        user.setOrders(usersOrders);
        userService.updateUser(user_id,userService.getUser(user_id));
    }

    //user/{id}/order/{id}
    public void deleteUserOrder(Integer user_id,Integer order_id) //if order type = cart
    {
        orderRepository.deleteById(order_id);
    }

    //user/{id}/order/{id}
    public void updateUserOrder(Integer user_id,Integer order_id) //if order type = cart
    {
        Order order=getOrder(order_id);
        if(order.getUser().getId()==user_id)
            orderRepository.save(getOrder(order_id));
    }

    public void purchaseOrder(Integer user_id,Integer order_id)
    {
        Order order=getOrder(order_id);
        order.setType("purchased"); //No longer in cart
        updateUserOrder(user_id,order_id);
        userService.updateUser(user_id,userService.getUser(user_id));
    }






}