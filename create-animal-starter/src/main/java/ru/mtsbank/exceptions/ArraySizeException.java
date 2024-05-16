package ru.mtsbank.exceptions;

public class ArraySizeException extends Exception{
    private int arraySize;
    public ArraySizeException(int argument){
        this.arraySize = argument;
    }

    @Override
    public String getMessage() {
        return "Размер массива должен должен быть больше, чем "+arraySize;
    }
}
