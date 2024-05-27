package ru.mtsbank.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mtsbank.entity.User;
import ru.mtsbank.repo.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService  {
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public User save(User user) {
        return userRepository.save(user);
    }
    public User create(User user){
        if (userRepository.existsByName(user.getName())){
            System.out.println("Пользователь уже существует");
            return null;
        }
        return save(user);
    }
    public User getByName(String username){
        return userRepository.findByName(username).orElse(null);
    }

    private User getByUserName(String username) {
        return userRepository.findByName(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUserName;
    }


}
