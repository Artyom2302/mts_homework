package ru.mtsbank.service;

import ru.mtsbank.animals.Animal;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;
import ru.mtsbank.config.CreateServiceProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CreateAnimalServiceImpl implements CreateAnimalService {



    private CreateServiceProperties props;



    CreateAnimalService.AnimalType type;

    public CreateAnimalServiceImpl(CreateServiceProperties props) {
        this.props = props;
    }

    public void setType(CreateAnimalService.AnimalType type) {
        this.type = type;
    }
    public AnimalType getType() {
        return type;
    }


    String getRandomName(String[] names){
        int n = (int)Math.floor(Math.random() * names.length);
        return  names[n];
    };

    public Animal getRandomAnimal(){
        Animal animal =CreateAnimalService.super.createRandomAnimal(type);
        if (animal instanceof Cat){
            ((Cat) animal).setName(getRandomName(props.getCatNames()) + " #" +(int)(365*Math.random()));
        }
        if (animal instanceof Dog){
            ((Dog) animal).setName(getRandomName(props.getDogNames())  + " #" +(int)(365*Math.random()));
        }
        return animal;
    }

    @Override
    public Map<String, List<Animal>> createAnimals() {
        var result = new HashMap<String,List<Animal>>();
        for (int i = 0; i < 3; i++) {
            int j = 0;
            AnimalType animalType = getRandomAnimalType();
            List<Animal> animals = new ArrayList<>();
            do {
                animals.add(createRandomAnimal(animalType));
                j++;
            }while (j<3);
            result.put(animalType.getTypeName(),animals);
        }
        return result;
    }


    public Map<String, List<Animal>> createAnimals(int number){
        var result = new HashMap<String,List<Animal>>();
        for (int i = 0; i < number; i++) {
            AnimalType animalType = getRandomAnimalType();
            List<Animal> animals;
            if(result.containsKey(animalType.getTypeName())){
                animals= result.get(animalType.getTypeName());
            }
            else {
                animals = new ArrayList<>();
                result.put(animalType.getTypeName(),animals);
            }
            animals.add(createRandomAnimal(animalType));

        }
        return result;
    }
    public Map<String, List<Animal>> createAnimalsImpl(){
        return CreateAnimalService.super.createAnimals();
    }
}
