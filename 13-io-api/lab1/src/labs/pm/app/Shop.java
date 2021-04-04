package labs.pm.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import labs.pm.data.*;

public class Shop {
    public static void main(String[] args) {
        ProductManager pm = new ProductManager("en-GB");
        pm.printProductReport(101);

        Product cake = pm.createProduct(103, "Cake", BigDecimal.valueOf(34.99), Rating.FOUR_STAR, LocalDate.now());
        cake = pm.reviewProduct(cake, Rating.ONE_STAR, "Nice ");
        cake = pm.reviewProduct(cake, Rating.TWO_STAR, "Rather weak Cake");

        pm.dumpData();
        pm.restoreData();
    }
}
