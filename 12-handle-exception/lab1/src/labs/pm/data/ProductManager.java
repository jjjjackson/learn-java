package labs.pm.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.logging.Logger;

public class ProductManager {

    private Map<Product, List<Review>> products = new HashMap<>();
    private ResourceFormatter formatter;

    private ResourceBundle config = ResourceBundle.getBundle("labs/pm/data/config");
    private MessageFormat reviewFormat = new MessageFormat(config.getString("review.data.format"));
    private MessageFormat productFormat = new MessageFormat(config.getString("product.data.format"));

    private static Map<String, ResourceFormatter> formatters = Map.of("en-GB", new ResourceFormatter(Locale.UK),
            "en-US", new ResourceFormatter(Locale.US), "fr-FR", new ResourceFormatter(Locale.FRANCE), "ru-RU",
            new ResourceFormatter(new Locale("ru", "RU")), "zh-CH", new ResourceFormatter(Locale.CHINA));

    private static final Logger logger = Logger.getLogger(ProductManager.class.getName());

    public ProductManager(Locale locale) {
        this(locale.toLanguageTag());
    }

    public ProductManager(String languageTag) {
        changeLocale(languageTag);
    }

    public static Set<String> getSupportedLocale() {
        return formatters.keySet();
    }

    public void changeLocale(String languageTag) {
        formatter = formatters.getOrDefault(languageTag, formatters.get("en-GB"));
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        Product product = new Food(id, name, price, rating, bestBefore);

        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        Product product = new Drink(id, name, price, rating);

        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product findProduct(int id) throws ProductManagerException {
        return products.keySet().stream().filter(p -> p.getId() == id).findFirst()
                .orElseThrow(() -> new ProductManagerException("Product with id " + id + " not found"));
    }

    public Product reviewProduct(int id, Rating rating, String comments) {
        try {
            return reviewProduct(findProduct(id), rating, comments);
        } catch (ProductManagerException ex) {
            logger.log(Level.INFO, ex.getMessage());
        }
        return null;
    }

    public Product reviewProduct(Product product, Rating rating, String comments) {

        List<Review> reviews = products.get(product);

        products.remove(product, reviews);
        reviews.add(new Review(rating, comments));

        product = product.applyRating(Rateable.convert(
                (int) Math.round(reviews.stream().mapToInt(r -> r.getRating().ordinal()).average().orElse(0))));

        products.put(product, reviews);
        return product;
    }

    public void printProductReport(Integer id) {
        try {
            printProductReport(findProduct(id));
        } catch (ProductManagerException ex) {
            logger.log(Level.INFO, ex.getMessage());
        }
    }

    public void printProductReport(Product product) {
        var reviews = products.get(product);

        StringBuilder txt = new StringBuilder();
        txt.append(formatter.formatProduct(product));
        txt.append('\n');

        Collections.sort(reviews);

        if (reviews.isEmpty()) {
            txt.append(formatter.getText("no.reviews") + "\n");
        } else {
            txt.append(reviews.stream().map(r -> formatter.formatReview(r) + "\n").collect(Collectors.joining()));
        }

        System.out.println(txt);
    }

    public void printProducts(Predicate<Product> filter, Comparator<Product> sorter) {
        StringBuilder txt = new StringBuilder();

        products.keySet().stream().sorted(sorter).filter(filter)
                .forEach(p -> txt.append(formatter.formatProduct(p) + "\n"));
        System.out.println(txt);
    }

    public void parseReview(String text) {
        try {
            Object[] values = reviewFormat.parse(text);
            var value1 = Integer.parseInt((String) values[0]);
            var value2 = Rateable.convert(Integer.parseInt((String) values[1]));
            var value3 = (String) values[2];

            reviewProduct(value1, value2, value3);
        } catch (ParseException | java.lang.NumberFormatException ex) {
            logger.log(Level.WARNING, "Error Parsing Review" + ex.getMessage());
        }
    }

    public void parseProduct(String text) {
        try {
            Object[] values = productFormat.parse(text);
            var id = Integer.parseInt((String) values[1]);
            String name = (String) values[2];
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble((String) values[3]));
            Rating rating = Rateable.convert(Integer.parseInt((String) values[4]));

            switch ((String) values[0]) {
            case "D":
                createProduct(id, name, price, rating);
                break;
            case "F":
                LocalDate bestBefore = LocalDate.parse((String) values[5]);
                createProduct(id, name, price, rating, bestBefore);
                break;
            }

        } catch (ParseException | java.lang.NumberFormatException | DateTimeParseException ex) {
            logger.log(Level.WARNING, "Error Parsing Review" + ex.getMessage());
        }
    }

    // public Map<String, String> getDiscount() {
    // return products.keySet().stream()
    // .collect(Collectors.groupingBy(p -> p.getRating(),
    // Collectors.collectingAndThen(
    // Collectors.summingDouble(product -> product.getDiscount().doubleValue(),
    // discount -> formatter.moneyFormat.format(discount)))));
    // }

    private static class ResourceFormatter {
        private Locale locale;
        private ResourceBundle resources;
        private NumberFormat moneyFormat;
        private DateTimeFormatter dateFormate;

        private ResourceFormatter(Locale locale) {
            this.locale = locale;
            resources = ResourceBundle.getBundle("labs/pm/data/resources", locale);
            moneyFormat = NumberFormat.getCurrencyInstance(locale);
            dateFormate = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        }

        private String formatProduct(Product product) {
            return MessageFormat.format(resources.getString("product"), product.getName(),
                    moneyFormat.format(product.getPrice()), product.getRating().getStarts(),
                    dateFormate.format(product.getBestBefore()), product.getId());
        }

        private String formatReview(Review review) {
            return MessageFormat.format(resources.getString("review"), review.getRating(), review.getComments());
        }

        private String getText(String key) {
            return resources.getString(key);
        }

    }
}
