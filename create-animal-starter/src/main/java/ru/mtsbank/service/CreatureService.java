package ru.mtsbank.service;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtsbank.entity.Creature;
import ru.mtsbank.repo.CreatureRepository;

import java.util.List;

@Service
@Setter
@Getter
public class CreatureService {

    private final CreatureRepository creatureRepository;

    @Autowired
    public CreatureService(CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }


    public List<Creature> findAll(){
        return creatureRepository.findAll();
    }
}
