package ru.mtsbank.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.mtsbank.animals.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;

@Component
public class AnimalObjectMapper {

    public class AnimalSerializer extends StdSerializer<Animal>{
        public AnimalSerializer() {
            this(null);
        }
        protected AnimalSerializer(Class<Animal> t) {
            super(t);
        }

        @Override
        public void serialize(Animal value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeStartObject();
            gen.writeStringField("type",value.getClass().getSimpleName());
            gen.writeStringField("breed",value.getBread());
            gen.writeStringField("name", value.getName());
            gen.writeStringField("character", value.getCharacter());
            gen.writeNumberField("cost", value.getCost()==null?BigDecimal.valueOf(0):value.getCost());
            gen.writeObjectField("birthDate",value.getBirthDate());
            gen.writeStringField("secretInformation", Base64.getEncoder().encodeToString(value.getSecretInformation().getBytes()));
            gen.writeEndObject();
        }

        @Override
        public void serializeWithType(Animal value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
            gen.writeStartObject();
            gen.writeStringField("type",value.getClass().getSimpleName());
            gen.writeStringField("breed",value.getBread());
            gen.writeStringField("name", value.getName());
            gen.writeStringField("character", value.getCharacter());
            gen.writeNumberField("cost", value.getCost()==null?BigDecimal.valueOf(0):value.getCost());
            gen.writeObjectField("birthDate",value.getBirthDate());
            gen.writeStringField("secretInformation", Base64.getEncoder().encodeToString(value.getSecretInformation().getBytes()));
            gen.writeEndObject();
        }
    }
    public class AnimalDeserializer extends StdDeserializer<Animal>{
        public AnimalDeserializer() {
            this(null);
        }
        protected AnimalDeserializer(Class<?> vc) {
            super(vc);
        }
        @Override
        public Animal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            JsonNode node = p.getCodec().readTree(p);
            String type = node.get("type").asText();
            String breed = node.get("breed").asText();
            String name = node.get("name").asText();
            String character = node.get("character").asText();
            BigDecimal cost = node.has("cost") ? node.get("cost").decimalValue() : BigDecimal.ZERO;
            LocalDate birthDate = LocalDate.parse(node.get("birthDate").asText());
            String secretInformation = new String(Base64.getDecoder().decode(node.get("secretInformation").asText()));
            Animal animal;
            switch (type) {
                case "Dog":
                    animal = new Dog(breed, name, cost, character, birthDate);
                    break;
                case "Cat":
                    animal = new Cat(breed, name, cost, character, birthDate);
                    break;
                case "Shark":
                    animal = new Shark(breed, name, character, birthDate);
                    break;
                case "Wolf":
                    animal = new Wolf(breed, name, character, birthDate);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown type: " + type);
            }
            animal.setSecretInformation(secretInformation);
            return animal;
        }

        @Override
        public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
            JsonNode node = p.getCodec().readTree(p);
            String type = node.get("type").asText();
            String breed = node.get("breed").asText();
            String name = node.get("name").asText();
            String character = node.get("character").asText();
            BigDecimal cost = node.has("cost") ? node.get("cost").decimalValue() : BigDecimal.ZERO;
            LocalDate birthDate = LocalDate.parse(node.get("birthDate").asText());
            String secretInformation = new String(Base64.getDecoder().decode(node.get("secretInformation").asText()));
            Animal animal;
            switch (type) {
                case "Dog":
                    animal = new Dog(breed, name, cost, character, birthDate);
                    break;
                case "Cat":
                    animal = new Cat(breed, name, cost, character, birthDate);
                    break;
                case "Shark":
                    animal = new Shark(breed, name, character, birthDate);
                    break;
                case "Wolf":
                    animal = new Wolf(breed, name, character, birthDate);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown breed: " + breed);
            }
            animal.setSecretInformation(secretInformation);
            return animal;
        }
    }

    public ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    void configure(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(df);
        SimpleModule module = new SimpleModule();
        module.addSerializer(Animal.class,new AnimalSerializer());
        module.addDeserializer(Animal.class,new AnimalDeserializer());
        objectMapper.registerModule(module);
        objectMapper.findAndRegisterModules();
    }

}
