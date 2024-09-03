package com.userpassword.security.service;

import com.userpassword.security.models.DemoUser;
import com.userpassword.security.principles.UserIfPrinciple;
import com.userpassword.security.repository.UserRepp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {
    private UserRepp repo;
    @Autowired
    public UserInfoService(UserRepp repo){
        this.repo = repo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DemoUser user = repo.findByUsername(username);
      //  System.out.println(username + "userinfo service");
        return new UserIfPrinciple(user);
    }
}
