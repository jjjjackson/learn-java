package labs.pm.app;

import java.math.BigDecimal;
import java.time.LocalDate;

import labs.pm.data.*;

public class Shop {
    public static void main(String[] args) {
        ProductManager pm = new ProductManager();
        Product[] products = {
                pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.FOUR_STAR, LocalDate.now()),
                pm.createProduct(102, "Tea 5", BigDecimal.valueOf(15.99), Rating.FIVE_STAR),
                pm.createProduct(103, "Tea 4", BigDecimal.valueOf(16.99), Rating.FOUR_STAR, LocalDate.now()) };

        for (var product : products) {
            System.out.println(product);
            System.out.println(product.getPrice());
            // System.out.println(product.getId() + " " + product.getName() + " " +
            // product.getPrice() + " "
            // + product.getDiscount() + " " + product.getRate());
        }

        Product cloneProduct = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.FOUR_STAR,
                LocalDate.now());
        System.out.println(products[0] == cloneProduct); // false
        System.out.println(products[0].equals(cloneProduct)); // true

        // Immutable
        Product product = products[2].applyRating(Rating.THREE_STAR);
        System.out.println(product);

        Product food = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.FOUR_STAR, LocalDate.now());
        var date = food.getBestBefore();
        System.out.println(date);
    }
}
