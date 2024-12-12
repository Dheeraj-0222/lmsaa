package com.lmsapp;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class A {
    public static void main(String[] args) {
      //  PasswordEncoder e = new BCryptPasswordEncoder();
      //  System.out.println(e.encode("password")); // $2a$10$fX823e483c7784593617e4.d0947a3f5447e3433c3c3a0361f5e514b

        String encodedPwd = BCrypt.hashpw("testing", BCrypt.gensalt(10));
        System.out.println(encodedPwd);
    }
}
