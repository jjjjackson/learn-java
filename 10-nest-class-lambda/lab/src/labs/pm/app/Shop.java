package labs.pm.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

import labs.pm.data.*;

public class Shop {
    public static void main(String[] args) {
        ProductManager pm = new ProductManager("en-GB");
        Product drink = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.FOUR_STAR, LocalDate.now());
        drink = pm.reviewProduct(drink, Rating.THREE_STAR, "Nice ");
        drink = pm.reviewProduct(drink, Rating.TWO_STAR, "Rather weak Tea");
        // pm.printProductReport(drink);

        pm.changeLocale("ru-RU");
        Product cookie = pm.createProduct(102, "Cookie", BigDecimal.valueOf(2.99), Rating.FOUR_STAR, LocalDate.now());
        cookie = pm.reviewProduct(cookie, Rating.FIVE_STAR, "Nice ");
        cookie = pm.reviewProduct(cookie, Rating.FOUR_STAR, "Rather weak Tea");
        // pm.printProductReport(cookie);

        pm.changeLocale("zh-CH");
        Product cake = pm.createProduct(103, "Cake", BigDecimal.valueOf(34.99), Rating.FOUR_STAR, LocalDate.now());
        cake = pm.reviewProduct(cake, Rating.ONE_STAR, "Nice ");
        cake = pm.reviewProduct(cake, Rating.TWO_STAR, "Rather weak Cake");
        // pm.printProductReport(cake);

        Comparator<Product> ratingSorter = (p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal();
        Comparator<Product> priceSorter = (p1, p2) -> p2.getPrice().compareTo(p1.getPrice());
        pm.printProducts(ratingSorter.thenComparing(priceSorter).reversed());
        // Compare 的 DESC or ASC 是看 p1 p2 的順序的

        Comparator<Product> priceSorter2 = (p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal()
                - p2.getPrice().compareTo(p1.getPrice());
        pm.printProducts(priceSorter2.reversed());
    }
}
