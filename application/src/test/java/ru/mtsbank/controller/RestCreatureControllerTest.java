package ru.mtsbank.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.mtsbank.entity.Creature;
import ru.mtsbank.repo.CreatureRepository;




import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class RestCreatureControllerTest {
    @MockBean
    CreatureRepository creatureRepository;
    @Autowired
    MockMvc mvc;

    @Test
    void getCreatureTest() throws Exception {
        Creature creature = new Creature();
        creature.setId(1);
        creature.setName("Животное №1234");
        creature.setAge(1);
        Mockito.when(this.creatureRepository.findAll()).thenReturn(List.of(creature));
        mvc.perform(get("/api/creatures"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Животное №1234"))
                .andExpect(jsonPath("$[0].age").value(1));
    }

    @Test
    void postCreatureTest() throws Exception {

        Creature creature = new Creature();
        creature.setId(1);
        creature.setName("Животное №1234");
        creature.setAge(1);
        when(creatureRepository.save(any(Creature.class))).thenReturn(creature);
        mvc.perform(post("/api/creatures")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"id\": 1," +
                                "\"name\":\"Животное №1234\"" +
                                ",\"typeId\":0,\"age\":1," +
                                "\"breed\":null," +
                                "\"birthDate\":[2024,5,14]}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.age").value(1))
                .andExpect(jsonPath("$.name").value("Животное №1234"));
        verify(creatureRepository).save(any(Creature.class));
    }
    @Test
    void deleteCreatureTest() throws Exception {
        long creatureId = 1L;
        mvc.perform(delete("/api/creatures/{id}", creatureId))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));

        verify(creatureRepository).deleteById(creatureId);
    }

}
