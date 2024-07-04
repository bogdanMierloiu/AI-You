package com.bogdanmierloiu.SpringAI.VectorAPI.repo;

import com.bogdanmierloiu.SpringAI.VectorAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByEmail(String email);


    Optional<User> findByAddress(String address);
}