package ru.mtsbank.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.mtsbank.entity.Creature;
import ru.mtsbank.repo.CreatureRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UICreatureController.class)

public class UICreatureControllerTest {
    @MockBean
    CreatureRepository creatureRepository;
    @Autowired
    MockMvc mvc;



    @BeforeEach
    void setCreatureRepository(){
        Creature creature = new Creature();
        creature.setId(1);
        creature.setName("Животное №1234");
        creature.setAge(1);
        creature.setBirthDate(LocalDate.now());
        Mockito.when(this.creatureRepository.findAll()).thenReturn(List.of(creature));
    }

    @Test
    void indexTest() throws Exception {
        mvc.perform(get("/creatures"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("creaturesList"));
    }

    @Test
    void newTest() throws Exception {
        mvc.perform(get("/creatures/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("create"))
                .andExpect(model().attributeExists("creature"));
    }

    @Test
    void deleteTest() throws Exception {
        mvc.perform(delete("/creatures/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/creatures"));
    }
    @Test
    void postTest() throws Exception{
        mvc.perform(delete("/creatures"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/creatures"));
    }




}
