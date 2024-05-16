package ru.mtsbank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mtsbank.entity.Creature;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreatureRepository extends JpaRepository<Creature, Long> {
}
