package labs.pm.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class ProductManager {
    // private Product product;
    // private Review review;

    private ResourceBundle resources;
    private NumberFormat moneyFormat;
    private DateTimeFormatter dateFormate;
    private Map<Product, List<Review>> products = new HashMap<>();

    public ProductManager(Locale locale) {
        // 如果是Package下面的 Resource 記得要連 Resource name 一起
        resources = ResourceBundle.getBundle("labs/pm/data/resources", locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);
        dateFormate = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
    }

    // Product Factory

    // 把 Product Food Drink 的 constructor 變成 Protected，讓 factory 統一管控
    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        Product product = new Food(id, name, price, rating, bestBefore);

        // 如果不在的話就插入 putIfAbsent
        // Food 和 Drink 會用 equals 去比較是否一樣
        // 這樣就不會讓兩個重複的 Product 被 Create
        // 而且如果重複的話會讓 Review 的 ArrayList 被重置
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        Product product = new Drink(id, name, price, rating);
        return product;
    }

    public Product findProduct(int id) {
        Product result = null;
        for (Product product : products.keySet()) {
            if (product.getId() == id) {
                result = product;
                break;
            }
        }
        return result;
    }

    public Product reviewProduct(int id, Rating rating, String comments) {
        Product product = findProduct(id);
        return reviewProduct(product, rating, comments);
    }

    public Product reviewProduct(Product product, Rating rating, String comments) {

        List<Review> reviews = products.get(product);

        // 因為 Product 是 Immutable 的，所以操作之後的 Reference 是不一樣的
        products.remove(product, reviews);
        reviews.add(new Review(rating, comments));

        int sum = 0;
        for (Review review : reviews) {
            sum += review.getRating().ordinal();
        }
        // review = new Review(rating, comments);
        int starAverage = Math.round((float) sum / reviews.size());
        product = product.applyRating(Rateable.convert(starAverage));

        products.put(product, reviews);
        return product;
    }

    public void printProductReport(Product product) {
        var reviews = products.get(product);

        StringBuilder txt = new StringBuilder();
        txt.append(MessageFormat.format(resources.getString("product"), product.getName(),
                moneyFormat.format(product.getPrice()), product.getRating().getStarts(),
                dateFormate.format(product.getBestBefore())));
        txt.append('\n');

        Collections.sort(reviews);
        for (var review : reviews) {
            txt.append(MessageFormat.format(resources.getString("review"), review.getRating(), review.getComments()));
            txt.append('\n');
        }

        if (reviews.isEmpty()) {
            txt.append(resources.getString("no.reviews"));
            txt.append('\n');
        }

        System.out.println(txt);
    }
}
