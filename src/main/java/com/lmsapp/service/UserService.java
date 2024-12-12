package com.lmsapp.service;

import com.lmsapp.entity.User;
import com.lmsapp.payload.LoginDto;
import com.lmsapp.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private JWTService jwtService;




    public UserService(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }
    public String verifyLogin(
            LoginDto loginDto
    ){
        Optional<User> opUser = userRepository.findByUsername(loginDto.getUsername());
        if(opUser.isPresent()){
            User user = opUser.get();
            if(BCrypt.checkpw(loginDto.getPassword(),user.getPassword())){
                String token = jwtService.generateToken(user.getUsername());
                return token;
            }
        }else {
            return null;
        }
       return null;
    }
}
