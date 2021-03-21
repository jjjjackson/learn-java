package labs.pm.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import labs.pm.data.*;

public class Shop {
    public static void main(String[] args) {
        ProductManager pm = new ProductManager(Locale.UK);
        Product drink = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.FOUR_STAR, LocalDate.now());
        drink = pm.reviewProduct(drink, Rating.THREE_STAR, "Nice ");
        drink = pm.reviewProduct(drink, Rating.TWO_STAR, "Rather weak Tea");
        pm.printProductReport(drink);

        Product cookie = pm.createProduct(101, "Cookie", BigDecimal.valueOf(1.99), Rating.FOUR_STAR, LocalDate.now());
        cookie = pm.reviewProduct(cookie, Rating.THREE_STAR, "Nice ");
        cookie = pm.reviewProduct(cookie, Rating.FOUR_STAR, "Rather weak Tea");
        pm.printProductReport(cookie);

    }
}
