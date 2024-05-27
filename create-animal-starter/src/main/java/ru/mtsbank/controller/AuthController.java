package ru.mtsbank.controller;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mtsbank.dto.JWTTokenResponse;
import ru.mtsbank.dto.UserDTO;
import ru.mtsbank.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@Data
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/sign-up")
    public JWTTokenResponse signUp(@RequestBody UserDTO UserDTO) {
        return authenticationService.signUp(UserDTO);
    }
    @PostMapping("/sign-in")
    public JWTTokenResponse signIn(@RequestBody UserDTO UserDTO) {
        return authenticationService.signIn(UserDTO);
    }
}
