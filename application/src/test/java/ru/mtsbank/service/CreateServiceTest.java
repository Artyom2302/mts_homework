package ru.mtsbank.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mtsbank.animals.Animal;

import java.util.List;
import java.util.Map;

@SpringBootTest
@Disabled
public class CreateServiceTest {
    @Autowired
    CreateAnimalServiceImpl createAnimalService;

    @ParameterizedTest
    @ValueSource(ints = {1,3,4,5,6})
    void createNAnimalsMethodTest(int N){
        Map<String, List<Animal>> createdAnimal = createAnimalService.createAnimals(N);
        int sizeAnimal = 0;
        for(String key:createdAnimal.keySet()){
            List<Animal> animals = createdAnimal.get(key);
            for(Animal animal: animals){
                Assertions.assertEquals(key,animal.toString());
            }
            sizeAnimal+=animals.size();
        }
        Assertions.assertEquals(N,sizeAnimal);

    }
}
