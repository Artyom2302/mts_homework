package ru.mtsbank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtsbank.animals.Animal;
import ru.mtsbank.exceptions.ArraySizeException;
import ru.mtsbank.util.AnimalObjectMapper;

import jakarta.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileWorkService {
    static private  String Path = "resources/result/";
    @Autowired
    AnimalsRepository animalsRepository;

    @Autowired
    AnimalObjectMapper animalObjectMapper;

    @PostConstruct
    void createDirectories(){
        Path path = Paths.get("resources");
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            Path pathAnimals = path.resolve("animals");
            Path pathResult =  path.resolve("result");
            Path pathSecretInfo =  path.resolve("secretStore");
            Files.createDirectories(pathAnimals);
            Files.createDirectories(pathResult);
            Files.createDirectories(pathSecretInfo);
        } catch (IOException e) {
            System.out.println("Не получилось создать папку");
            throw new RuntimeException(e);
        }

    }

    void writeToLogFileTxt(List<Animal> animals){
        try(RandomAccessFile writer =new RandomAccessFile("resources/animals/logData.txt","rw")){
            FileChannel channel = writer.getChannel();
            int number = 0;
            for (Animal animal:animals) {
                number+=1;
                String str =number+" "+animal.getBread()+" "+animal.getName()+" "+(animal.getCost()==null?"---":animal.getCost())+" "+animal.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\n";
                ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
                channel.write(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static  String  getStringFromSecretInfo(){
        try(RandomAccessFile reader = new RandomAccessFile("resources/secretStore/secretInformation.txt","rw")){
            FileChannel channel = reader.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)channel.size());
            channel.read(byteBuffer);
            byteBuffer.flip();
            String strings = new String(byteBuffer.array(), StandardCharsets.UTF_8);
            String[] secretInfo =  strings.split("\n");
            int index = (int)(Math.random()*secretInfo.length);
            return secretInfo[index];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void writeFindMinCost() throws ArraySizeException, JsonProcessingException {
        List<String> result = animalsRepository.findMinConstAnimals();
        Path filePath = Paths.get(Path + "FindMinConst.json");

        try(FileOutputStream stream = new FileOutputStream(filePath.toFile())) {
            animalObjectMapper.objectMapper.writeValue(stream,result);
        } catch (IOException e) {
            System.out.println("Не записалось в файл");
            throw new RuntimeException(e);
        }
    }
    List<String> readFindMinCost() throws JsonProcessingException {
        Path filePath = Paths.get(Path + "FindMinConst.json");
        try(FileInputStream stream = new FileInputStream(filePath.toFile())) {
            List<String> result= animalObjectMapper.objectMapper.readValue(stream, new TypeReference<>() {});
            return result;
        } catch (IOException e) {
            System.out.println("Не считалось с файла");
            throw new RuntimeException(e);
        }
    }

    void writeFindOldAndExpensive() throws ArraySizeException, JsonProcessingException {
        List<Animal> result = animalsRepository.findOldAndExpensive();
        Path filePath = Paths.get(Path + "findOldAndExpensive.json");
        try(FileOutputStream stream = new FileOutputStream(filePath.toFile())) {
            animalObjectMapper.objectMapper.writeValue(stream,result);
        } catch (IOException e) {
            System.out.println("Не записалось в файл");
            throw new RuntimeException(e);
        }
    }
    List<Animal> readFindOldAndExpensive(){
        Path filePath = Paths.get(Path + "findOldAndExpensive.json");
        try(FileInputStream stream = new FileInputStream(filePath.toFile())) {
            List<Animal> result= animalObjectMapper.objectMapper.readValue(stream, new TypeReference<>() {});
            return result;
        } catch (IOException e) {
            System.out.println("Не считалось с файла");
            throw new RuntimeException(e);
        }
    }
    void writeAvgAge() throws ArraySizeException, JsonProcessingException {
        double result = animalsRepository.findAverageAge();
        Path filePath = Paths.get(Path + "findAverageAge.json");
        try(FileOutputStream stream = new FileOutputStream(filePath.toFile())) {
            animalObjectMapper.objectMapper.writeValue(stream,result);
        } catch (IOException e) {
            System.out.println("Не записалось в файл");
            throw new RuntimeException(e);
        }
    }
    double readAveAge(){
        Path filePath = Paths.get(Path + "findAverageAge.json");
        try(FileInputStream stream = new FileInputStream(filePath.toFile())) {
            return animalObjectMapper.objectMapper.readValue(stream, Double.class);
        } catch (IOException e) {
            System.out.println("Не считалось с файла");
            throw new RuntimeException(e);
        }
    }
    void writeFindDuplicate() throws ArraySizeException, JsonProcessingException {
        Map<String, Integer> result = animalsRepository.findDuplicate();
        Path filePath = Paths.get(Path+"findDuplicate.json");
        try(FileOutputStream stream = new FileOutputStream(filePath.toFile())) {
            animalObjectMapper.objectMapper.writeValue(stream,result);
        } catch (IOException e) {
            System.out.println("Не записалось в файл");
            throw new RuntimeException(e);
        }
        System.out.println(readFindDuplicate());
    }
    Map<String,Integer> readFindDuplicate(){
        Path filePath = Paths.get(Path+"findDuplicate.json");
        try(FileInputStream stream = new FileInputStream(filePath.toFile())) {
            return animalObjectMapper.objectMapper.readValue(stream, new TypeReference<>() {});
        } catch (IOException e) {
            System.out.println("Не считалось с файла");
            throw new RuntimeException(e);
        }
    }
    void writeFindLeapYearFile() throws ArraySizeException, JsonProcessingException {
        Map<String, LocalDate> result = animalsRepository.findLeapYearNames();
        Path filePath = Paths.get(Path + "findLeapYear.json");
        try(FileOutputStream stream = new FileOutputStream(filePath.toFile())) {
            animalObjectMapper.objectMapper.writeValue(stream,result);
        } catch (IOException e) {
            System.out.println("Не записалось в файл");
            throw new RuntimeException(e);
        }
    }
    Map<String, LocalDate> readFindLeapYearFile(){
        Path filePath = Paths.get(Path + "findLeapYear.json");
        try(FileInputStream stream = new FileInputStream(filePath.toFile())) {
            return animalObjectMapper.objectMapper.readValue(stream, new TypeReference<>() {});
        } catch (IOException e) {
            System.out.println("Не считалось с файла");
            throw new RuntimeException(e);
        }
    }
    void writeOlderAnimal(int age) throws ArraySizeException, JsonProcessingException {
        Path filePath = Paths.get(Path + "findOlderAnimal.json");
        Map<Animal, Integer> result = animalsRepository.findOlderAnimal(age);
        List<ObjectNode> jsonArray = new ArrayList<>();
        for (Map.Entry<Animal, Integer> entry : result.entrySet()) {
            Animal animal = entry.getKey();
            Integer animalAge = entry.getValue();
            ObjectNode objectNode = animalObjectMapper.objectMapper.createObjectNode();
            JsonNode jNode = animalObjectMapper.objectMapper.convertValue(animal, ObjectNode.class);
            objectNode.set("animal", jNode);
            objectNode.put("age", animalAge);
            jsonArray.add(objectNode);
        }
        try(FileOutputStream stream = new FileOutputStream(filePath.toFile())) {
            animalObjectMapper.objectMapper.writeValue(stream,jsonArray);
        } catch (IOException e) {
            System.out.println("Не считалось с файла");
            throw new RuntimeException(e);
        }
    }
    Map<Animal,Integer> readOlderAnimal(){
        Path filePath = Paths.get(Path + "findOlderAnimal.json");
        try(FileInputStream stream = new FileInputStream(filePath.toFile())) {
            Map<Animal,Integer> result = new HashMap<>();
            JsonNode nodes = animalObjectMapper.objectMapper.readTree(stream);
            for(JsonNode node:nodes){
                Animal animal = animalObjectMapper.objectMapper.convertValue(node.get("animal"),Animal.class);
                Integer age = animalObjectMapper.objectMapper.convertValue(node.get("age"),Integer.class);
                result.put(animal,age);
            }
            return result;
        } catch (IOException e) {
            System.out.println("Не считалось с файла");
            throw new RuntimeException(e);
        }
    }

}
