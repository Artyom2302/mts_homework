package ru.mtsbank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@ConfigurationProperties(prefix = "properties.animal.names")
public class CreateServiceProperties {

    public String[] getCatNames() {
        return catNames;
    }

    public String[] getDogNames() {
        return dogNames;
    }

    public void setCatNames(String[] catNames) {
        this.catNames = catNames;
    }

    public void setDogNames(String[] dogNames) {
        this.dogNames = dogNames;
    }

    private String[] catNames;


    private String[] dogNames;

}
