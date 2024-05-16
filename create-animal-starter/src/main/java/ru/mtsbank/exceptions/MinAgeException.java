package ru.mtsbank.exceptions;

public class MinAgeException extends IllegalArgumentException{
    private int argumentCausedException;
    public MinAgeException(int argument){
        this.argumentCausedException = argument;
    }

    @Override
    public String getMessage() {
        return "Аргумент возраста должен быть больше, чем "+argumentCausedException;
    }
}
