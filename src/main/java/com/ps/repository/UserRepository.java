package com.ps.repository;

import com.ps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

//class/entity name, but id data type
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByEmail(String email);
    // findBy then field ka naam (here Email ) jo ki entitiy me tha

    @Query(
            "select u from User u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%"
    )
    public List<User> searchUser(@Param("query") String query);
}
