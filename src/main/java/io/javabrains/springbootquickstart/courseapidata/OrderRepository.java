package io.javabrains.springbootquickstart.courseapidata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

//String because the primary key id of the topic is a string

public interface OrderRepository extends JpaRepository<Order,Integer> {

}
