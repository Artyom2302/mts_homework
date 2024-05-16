package ru.mtsbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.entity.Creature;
import ru.mtsbank.repo.CreatureRepository;

@Controller
@RequestMapping("/creatures")
public class UICreatureController {
    final private CreatureRepository creatureRepository;

    public UICreatureController(CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("creaturesList", creatureRepository.findAll());
        return "index";
    }

    @GetMapping("/new")
    public String createPage(Model model) {
        model.addAttribute("creature", new Creature());
        return "create";
    }
    @PostMapping(params = "action=create")
    public String post(Model model,Creature creature){
        creatureRepository.save(creature);
        return "redirect:/creatures";
    }
    @PostMapping(params = "action=cancel")
    public String cancel(Model model,Creature creature){
        return "redirect:/creatures";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        creatureRepository.deleteById(id);
        return "redirect:/creatures";
    }

}
