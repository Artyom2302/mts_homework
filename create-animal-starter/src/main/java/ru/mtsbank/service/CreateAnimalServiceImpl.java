package ru.mtsbank.service;

import ru.mtsbank.animals.Animal;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;
import ru.mtsbank.config.CreateServiceProperties;



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
            ((Cat) animal).setName(getRandomName(props.getCatNames()));
        }
        if (animal instanceof Dog){
            ((Dog) animal).setName(getRandomName(props.getDogNames()));
        }
        return animal;
    }

    @Override
    public Animal[] createAnimals() {
        int i = 0;
        Animal[] animals = new Animal[10];
        do {
            animals[i] = createRandomAnimal(getRandomAnimalType());
            System.out.println("------");
            i++;
        }while (i<10);
        return animals;
    }


    public Animal[] createAnimals(int number){
        Animal[] animals = new Animal[10];
        for (int i = 0; i <number; i++) {
            animals[i] = createRandomAnimal(getRandomAnimalType());
            System.out.println("------");
        }
        return animals;
    }
    public Animal[] createAnimalsImpl(){
        return CreateAnimalService.super.createAnimals();
    }
}
