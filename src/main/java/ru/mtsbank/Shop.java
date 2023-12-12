package ru.mtsbank;

public class Shop {
    public static void priceCalculation(Product product){
        double price = product.price * product.count;

        String sumWithoutDiscount = String.format("Cумма без скидки: %.2f",price);
        System.out.println(sumWithoutDiscount);
        String sumWithDiscount = String.format("Cумма со скидкой: %.2f",price-price*product.discount/100);
        System.out.println(sumWithDiscount);
    }

    public static void main(String[] args) {
        Product productFirst = new Product(2,500,0.75);
        Product productSecond = new Product(3,600,42.575);
        Product productThird = new Product(4,700,59.1);

        System.out.println("Стоимость для товара №1");
        priceCalculation(productFirst);

        System.out.println("Стоимость для товара №2");
        priceCalculation(productSecond);

        System.out.println("Стоимость для товара №3");
        priceCalculation(productThird);
    }
}
