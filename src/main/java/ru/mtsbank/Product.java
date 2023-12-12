package ru.mtsbank;

public class Product {
    /**Описание класса
     * Класс продукты содержит
     * 1)Три поля:
     * -Кол-во товаров
     * -Сумма товара - цена за один товар
     * -Скидка на товар в процентах
     * 2)Метод для подсчетов
     * */
    int count = 0;//Кол-во товара
    double price = 0;//Сумма товара - цена за один товар
    double discount = 0;//Скидка

    Product(int count,double price,double discount){
        this.count = count;
        this.price = price;
        this.discount = discount;
    }


}
