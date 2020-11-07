package io.javabrains.springbootquickstart.courseapidata;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_info")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="type")
    private String type;
    @Column(name="total")
    private String total;
    @ManyToOne
    private User user;
    @ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="order_product",
            joinColumns = {@JoinColumn(name="order_id")},
            inverseJoinColumns = { @JoinColumn(name="product_id")})
    private List<Product> products = new ArrayList<>();

    public Order()
    {

    }
    public Order(int id, String type, String total) {
        super();
        this.id = id;
        this.type = type;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
