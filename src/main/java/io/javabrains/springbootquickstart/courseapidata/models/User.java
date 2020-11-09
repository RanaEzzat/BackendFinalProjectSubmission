package io.javabrains.springbootquickstart.courseapidata.models;

import io.javabrains.springbootquickstart.courseapidata.models.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="userName")
    private String userName;
    @Column(name="password")
    private String password;
    @Column(name="active")
    private boolean active;
    @Column(name="roles")
    private String roles;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();



    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    /*public boolean isUser()
    {
        String[] userRoles =(roles).split(",");
        for(int i=0;i<userRoles.length;i++)
        {
            if(userRoles[i].contains("ROLE_USER"))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin()
    {
        String[] userRoles =(roles).split(",");
        for(int i=0;i<userRoles.length;i++)
        {
            if(userRoles[i].contains("ROLE_ADMIN"))
            {
                return true;
            }
        }
        return false;
    }*/
}