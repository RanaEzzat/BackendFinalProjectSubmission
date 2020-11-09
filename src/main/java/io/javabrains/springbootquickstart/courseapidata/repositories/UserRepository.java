package io.javabrains.springbootquickstart.courseapidata.repositories;

import io.javabrains.springbootquickstart.courseapidata.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String userName);
}
