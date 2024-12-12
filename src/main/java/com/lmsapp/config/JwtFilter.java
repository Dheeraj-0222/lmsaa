package com.lmsapp.config;

import com.lmsapp.entity.User;
import com.lmsapp.repository.UserRepository;
import com.lmsapp.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;



import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private UserRepository userRepository;

    public JwtFilter(JWTService jwtService ,UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String token = request.getHeader("Authorization");
        String jwtToken = token.substring(7);
        String username = jwtService.getUsername(jwtToken);

        Optional<User> opUsername = userRepository.findByUsername(username);
        if (opUsername.isPresent()){
            User user =opUsername.get();
            UsernamePasswordAuthenticationToken
                    userToken = new UsernamePasswordAuthenticationToken(user, null,null);
            userToken.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(userToken);
        }
        filterChain.doFilter(request, response);
    }
}
