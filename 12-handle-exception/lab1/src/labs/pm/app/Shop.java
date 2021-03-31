package labs.pm.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

import labs.pm.data.*;

public class Shop {
    public static void main(String[] args) {
        ProductManager pm = new ProductManager("en-GB");
        Product drink = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.FOUR_STAR, LocalDate.now());
        // pm.printProductReport(101);
        // drink = pm.reviewProduct(drink, Rating.THREE_STAR, "Nice ");
        // drink = pm.reviewProduct(drink, Rating.TWO_STAR, "Rather weak Tea");
        // pm.parseReview("101,4,Nice hot cup of tea");
        // pm.parseReview("101,2,Nice hot cup of tea");
        // pm.parseReview("101,4,Nice hot cup of tea");
        // pm.parseReview("101,x,Nice hot cup of tea");
        // pm.parseReview("101,5,Nice hot cup of tea");
        // pm.parseReview("101,3,Nice hot cup of tea");
        pm.parseProduct("D,102,Coffee,1.99,2,2021-10-10");
        pm.printProductReport(102);
        // pm.printProductReport(drink);

        // pm.changeLocale("ru-RU");
        // Product cookie = pm.createProduct(102, "Cookie", BigDecimal.valueOf(2.99),
        // Rating.FOUR_STAR, LocalDate.now());
        // cookie = pm.reviewProduct(cookie, Rating.FIVE_STAR, "Nice ");
        // cookie = pm.reviewProduct(cookie, Rating.FOUR_STAR, "Rather weak Tea");
        // pm.printProductReport(cookie);

        // pm.changeLocale("zh-CH");
        // Product cake = pm.createProduct(103, "Cake", BigDecimal.valueOf(34.99),
        // Rating.FOUR_STAR, LocalDate.now());
        // cake = pm.reviewProduct(cake, Rating.ONE_STAR, "Nice ");
        // cake = pm.reviewProduct(cake, Rating.TWO_STAR, "Rather weak Cake");
        // pm.printProductReport(cake);

        // Comparator<Product> ratingSorter = (p1, p2) -> p2.getRating().ordinal() -
        // p1.getRating().ordinal();
        // Comparator<Product> priceSorter = (p1, p2) ->
        // p2.getPrice().compareTo(p1.getPrice());
        // pm.printProducts((p) -> p.getPrice().floatValue() < 2,
        // ratingSorter.thenComparing(priceSorter).reversed());

        // Comparator<Product> priceSorter2 = (p1, p2) -> p2.getRating().ordinal() -
        // p1.getRating().ordinal()
        // - p2.getPrice().compareTo(p1.getPrice());
        // pm.printProducts(priceSorter2.reversed());

        // pm.getDiscount().forEach((rating, discount) -> System.out.println(rating +
        // "\t" + discount));
    }
}
