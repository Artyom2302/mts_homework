package ru.mtsbank.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mtsbank.dto.UserDTO;
import ru.mtsbank.entity.User;
import ru.mtsbank.service.AuthenticationService;
import ru.mtsbank.service.UserService;
import ru.mtsbank.util.JWTTokenUtils;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final JWTTokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        model.addAttribute("userDTO",new UserDTO());
        return "registration";
    };
    @PostMapping("/registration")
    public String registrationPost(UserDTO userDTO,HttpServletResponse response){
        String token = authenticationService.signUp(userDTO).getToken();
        if (token==null){
            System.out.println("12345");
            return "/registration";
        }
        System.out.println(token);
        Cookie cookie = new Cookie("token",token);
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
        System.out.println("token: " + token);
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("userDTO",new UserDTO());
        return "login";
    };

    @PostMapping("/login")
    public String authorise(UserDTO userDTO, HttpServletResponse response,Model model){
        String token = authenticationService.signIn(userDTO).getToken();
        if (token==null){
            return "login";
        }
        Cookie cookie = new Cookie("token",token);
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
        System.out.println("token: " + token);
        return "redirect:/creatures";
    };

}
