package ru.mtsbank.service;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mtsbank.dto.JWTTokenResponse;
import ru.mtsbank.dto.UserDTO;
import ru.mtsbank.entity.Role;
import ru.mtsbank.entity.User;
import ru.mtsbank.util.JWTTokenUtils;

@Service
@Data
public class AuthenticationService {
    private final UserService userService;
    private final JWTTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JWTTokenResponse signUp(UserDTO userDTO){
        if (userService.getByName(userDTO.getName())==null){
            User user = new User(userDTO.name,
                    passwordEncoder.encode(userDTO.getPassword()),Role.ADMIN);
            userService.create(user);
            String jwt = jwtTokenUtils.generateToken(user);
            return new JWTTokenResponse(jwt);
        }
       return null;
    }

    public JWTTokenResponse signIn(UserDTO userDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDTO.getName(),
                userDTO.getPassword()
        ));
        var user = userService
                .userDetailsService()
                .loadUserByUsername(userDTO.name);
        var jwt = jwtTokenUtils.generateToken(user);
        return new JWTTokenResponse(jwt);

    }
}
