package ru.mtsbank.controller;

import org.springframework.web.bind.annotation.*;
import ru.mtsbank.entity.Creature;
import ru.mtsbank.repo.CreatureRepository;

import java.util.List;

@RestController
@RequestMapping("api/creatures")
public class RestCreatureController {
    final private CreatureRepository creatureRepository;

    public RestCreatureController(CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }

    @GetMapping()
    List<Creature> getCreature(){
        return creatureRepository.findAll();
    }


    @PostMapping()
    Creature postCreature(@RequestBody Creature creature){
        return creatureRepository.save(creature);
    }
    @DeleteMapping("/{id}")
    String postCreature(@PathVariable long id){
        creatureRepository.deleteById(id);
        return "Deleted";
    }

}
