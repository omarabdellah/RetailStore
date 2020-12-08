package com.omar.dao;

import com.omar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findById(long id);

    User findByUsername(String username);

}
