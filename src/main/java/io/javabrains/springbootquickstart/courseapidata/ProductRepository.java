package io.javabrains.springbootquickstart.courseapidata;

import org.springframework.data.repository.CrudRepository;

//String because the primary key id of the topic is a string

public interface ProductRepository extends CrudRepository<Product,Integer> {

}
