package labs.pm.data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Instant;
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

    private Path reportsFolder = Path.of(config.getString("reports.folder"));
    private Path dataFolder = Path.of(config.getString("data.folder"));
    private Path tempFolder = Path.of(config.getString("temp.folder"));

    private static Map<String, ResourceFormatter> formatters = Map.of("en-GB", new ResourceFormatter(Locale.UK),
            "en-US", new ResourceFormatter(Locale.US), "fr-FR", new ResourceFormatter(Locale.FRANCE), "ru-RU",
            new ResourceFormatter(new Locale("ru", "RU")), "zh-CH", new ResourceFormatter(Locale.CHINA));

    private static final Logger logger = Logger.getLogger(ProductManager.class.getName());

    public ProductManager(Locale locale) {
        this(locale.toLanguageTag());
    }

    public ProductManager(String languageTag) {
        changeLocale(languageTag);
        loadAllData();
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
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void printProductReport(Product product) throws IOException {
        var reviews = products.get(product);

        // StringBuilder txt = new StringBuilder();
        Path productFile = reportsFolder
                .resolve(MessageFormat.format(config.getString("report.file"), product.getId()));
        try (PrintWriter out = new PrintWriter(
                new OutputStreamWriter(Files.newOutputStream(productFile, StandardOpenOption.CREATE), "UTF-8"))) {
            out.append(formatter.formatProduct(product) + System.lineSeparator());
            // txt.append('\n'); 要用 System.LineSeperator 不然每個系統的 LineSeperator 不一樣

            Collections.sort(reviews);

            if (reviews.isEmpty()) {
                out.append(formatter.getText("no.reviews") + System.lineSeparator());
            } else {
                out.append(reviews.stream().map(r -> formatter.formatReview(r) + System.lineSeparator())
                        .collect(Collectors.joining()));
            }
        }
    }

    public void printProducts(Predicate<Product> filter, Comparator<Product> sorter) {
        StringBuilder txt = new StringBuilder();

        products.keySet().stream().sorted(sorter).filter(filter)
                .forEach(p -> txt.append(formatter.formatProduct(p) + System.lineSeparator()));
    }

    public void dumpData() {
        try {
            if (Files.notExists(tempFolder)) {
                Files.createDirectory(tempFolder);
            }

            Path tempFile = tempFolder.resolve(MessageFormat.format(config.getString("temp.file"), Instant.now()));
            try (ObjectOutputStream out = new ObjectOutputStream(
                    Files.newOutputStream(tempFile, StandardOpenOption.CREATE))) {
                out.writeObject(products);
                products = new HashMap<>();
            }
        } catch (Exception ex) {
            logger.severe("Error dumping data" + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked") // 因為讀近來的東西已經沒有 Type 了，所以在 HashMap 會出 Warning，所以用 Suppress 壓下去
    public void restoreData() {
        try {
            Path tempFile = Files.list(tempFolder).filter(path -> path.getFileName().toString().endsWith("temp"))
                    .findFirst().orElseThrow();
            try (ObjectInputStream in = new ObjectInputStream(
                    Files.newInputStream(tempFile, StandardOpenOption.DELETE_ON_CLOSE))) {
                products = (HashMap) in.readObject();
            }
        } catch (Exception ex) {
            logger.severe("Error restoring data" + ex.getMessage());
        }
    }

    private void loadAllData() {
        try {
            products = Files.list(dataFolder).filter(file -> file.getFileName().toString().startsWith("product"))
                    .map(file -> loadProduct(file)).filter(product -> product != null)
                    .collect(Collectors.toMap(product -> product, product -> loadReviews(product)));
        } catch (Exception ex) {
            logger.severe("ERROR: loading data" + ex.getMessage());
        }
    }

    private Product loadProduct(Path file) {
        Product product = null;
        try {
            product = parseProduct(
                    Files.lines(dataFolder.resolve(file), Charset.forName("UTF-8")).findFirst().orElseThrow());
        } catch (Exception ex) {
            logger.severe("Error load product" + ex.getMessage());
        }

        return product;
    }

    private List<Review> loadReviews(Product product) {
        List<Review> reviews = null;
        Path file = dataFolder.resolve(MessageFormat.format(config.getString("reviews.data.file"), product.getId()));

        if (Files.notExists(file)) {
            reviews = new ArrayList<>();
        } else {
            try {
                reviews = Files.lines(file, Charset.forName("UTF-8")).map(text -> parseReview(text))
                        .filter(review -> review != null).collect(Collectors.toList());
            } catch (IOException ex) {
                logger.warning("Error loading reviews" + ex.getMessage());
            }
        }
        return reviews;
    }

    private Review parseReview(String text) {
        Review review = null;
        try {
            Object[] values = reviewFormat.parse(text);

            var value1 = Rateable.convert(Integer.parseInt((String) values[0]));
            var value2 = (String) values[1];

            review = new Review(value1, value2);
        } catch (ParseException | java.lang.NumberFormatException ex) {
            logger.log(Level.WARNING, "Error Parsing Review " + ex.getMessage());
        }
        return review;
    }

    private Product parseProduct(String text) {
        Product product = null;

        try {
            Object[] values = productFormat.parse(text);
            var id = Integer.parseInt((String) values[1]);
            String name = (String) values[2];
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble((String) values[3]));
            Rating rating = Rateable.convert(Integer.parseInt((String) values[4]));

            switch ((String) values[0]) {
            case "D":
                product = new Drink(id, name, price, rating);
                break;
            case "F":
                LocalDate bestBefore = LocalDate.parse((String) values[5]);
                product = new Food(id, name, price, rating, bestBefore);
                break;
            }

        } catch (ParseException | java.lang.NumberFormatException | DateTimeParseException ex) {
            logger.log(Level.WARNING, "Error Parsing Product" + ex.getMessage());
        }

        return product;
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
