package com.userpassword.security.bussineslayer__democontroller;

import com.userpassword.security.jwt.JWTGenerationAndValidation;
import com.userpassword.security.models.DemoUser;
import com.userpassword.security.repository.UserRepp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BussinessOp {

    private UserRepp userRepp;
    @Autowired // setter injection
    private void setUserRepp(final UserRepp userRepp) {
        this.userRepp = userRepp;
    }

    private AuthenticationManager manager;
    @Autowired // setter injection
    private void setAuthenticationManager(final AuthenticationManager manager) {
        this.manager = manager;
    }

    private JWTGenerationAndValidation jwtGenerator;
    @Autowired
    private void setJwtGenerator(final JWTGenerationAndValidation jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }
//implementing password hashing
    public DemoUser addUser(DemoUser user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepp.save(user);
    }
//implemeting password username authentication
    public String userLogged(DemoUser user) {
        Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(auth.isAuthenticated()) return jwtGenerator.generatejwt(user.getUsername());
        else return "error";
    }

}
