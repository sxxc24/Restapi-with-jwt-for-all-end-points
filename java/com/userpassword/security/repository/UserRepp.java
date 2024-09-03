package com.userpassword.security.repository;

import com.userpassword.security.models.DemoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepp extends JpaRepository<DemoUser,Integer > {
    DemoUser findByUsername(String username);
}
