package ru.mtsbank.service;

import ru.mtsbank.animals.Animal;

import java.time.LocalDate;
import java.util.List;

public interface SearchService {
    public String[] findLeapYearNames(Animal[] animals);
    public Animal[] findOlderAnimal(Animal[] animals,LocalDate dateForSearch);
    public List findDublicate(Animal[] animals);
}
