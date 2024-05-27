package ru.mtsbank.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mtsbank.entity.User;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByName(String name);
    boolean existsByName(String username);

}
