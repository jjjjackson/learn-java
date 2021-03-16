package labs.pm.app;

import java.math.BigDecimal;

import labs.pm.data.*;

public class Shop {
    public static void main(String[] args) {
        Product[] products = { new Product(101, "Tea", BigDecimal.valueOf(1.99)),
                new Product(102, "Tea 5", BigDecimal.valueOf(15.99), Rating.FIVE_STAR),
                new Product(103, "Tea 4", BigDecimal.valueOf(16.99), Rating.FOUR_STAR), new Product() };

        for (var product : products) {
            System.out.println(product.getId() + " " + product.getName() + " " + product.getPrice() + " "
                    + product.getDiscount() + " " + product.getRate());
        }

        // Immutable
        Product product = products[2].applyRating(Rating.THREE_STAR);
        System.out.println(product.getId() + " " + product.getName() + " " + product.getPrice() + " "
                + product.getDiscount() + " " + product.getRate());
    }
}
