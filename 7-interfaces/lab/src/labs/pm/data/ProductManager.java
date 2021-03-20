package labs.pm.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProductManager {
    private Product product;
    private Review review;

    private ResourceBundle resources;
    private NumberFormat moneyFormat;
    private DateTimeFormatter dateFormate;

    public ProductManager(Locale locale) {
        // 如果是Package下面的 Resource 記得要連 Resource name 一起
        resources = ResourceBundle.getBundle("labs/pm/data/resources", locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);
        dateFormate = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
    }

    // Product Factory

    // 把 Product Food Drink 的 constructor 變成 Protected，讓 factory 統一管控
    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        product = new Food(id, name, price, rating, bestBefore);
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        product = new Drink(id, name, price, rating);
        return product;
    }

    public Product reviewProduct(Product product, Rating rating, String comments) {
        review = new Review(rating, comments);
        this.product = product.applyRating(rating);
        return this.product;
    }

    public void printProductReport() {
        StringBuilder txt = new StringBuilder();
        txt.append(MessageFormat.format(resources.getString("product"), product.getName(),
                moneyFormat.format(product.getPrice()), product.getRating().getStarts(),
                dateFormate.format(product.getBestBefore())));
        txt.append('\n');

        if (review != null) {
            txt.append(MessageFormat.format(resources.getString("review"), review.getRating(), review.getComments()));
        }

        System.out.println(txt);
    }
}
