package labs.pm.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import java.util.stream.Collectors;

public class ProductManager {

  private Map<Product, List<Review>> products = new HashMap<>();
  private ResourceFormatter formatter;

  private static Map<String, ResourceFormatter> formatters = Map.of(
    "en-GB",
    new ResourceFormatter(Locale.UK),
    "en-US",
    new ResourceFormatter(Locale.US),
    "fr-FR",
    new ResourceFormatter(Locale.FRANCE),
    "ru-RU",
    new ResourceFormatter(new Locale("ru", "RU")),
    "zh-CH",
    new ResourceFormatter(Locale.CHINA)
  );

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

  // Product Factory

  // 把 Product Food Drink 的 constructor 變成 Protected，讓 factory 統一管控
  public Product createProduct(
    int id,
    String name,
    BigDecimal price,
    Rating rating,
    LocalDate bestBefore
  ) {
    Product product = new Food(id, name, price, rating, bestBefore);

    // 如果不在的話就插入 putIfAbsent
    // Food 和 Drink 會用 equals 去比較是否一樣
    // 這樣就不會讓兩個重複的 Product 被 Create
    // 而且如果重複的話會讓 Review 的 ArrayList 被重置
    products.putIfAbsent(product, new ArrayList<>());
    return product;
  }

  public Product createProduct(
    int id,
    String name,
    BigDecimal price,
    Rating rating
  ) {
    Product product = new Drink(id, name, price, rating);
    return product;
  }

  public Product findProduct(int id) {
    return products
      .keySet()
      .stream()
      .filter(p -> p.getId() == id)
      .findFirst()
      .orElseGet(() -> null);
    // Product result = null;
    // for (Product product : products.keySet()) {
    // if (product.getId() == id) {
    // result = product;
    // break;
    // }
    // }
    // return result;
  }

  public Product reviewProduct(int id, Rating rating, String comments) {
    Product product = findProduct(id);
    return reviewProduct(product, rating, comments);
  }

  public Product reviewProduct(
    Product product,
    Rating rating,
    String comments
  ) {
    List<Review> reviews = products.get(product);

    // 因為 Product 是 Immutable 的，所以操作之後的 Reference 是不一樣的
    products.remove(product, reviews);
    reviews.add(new Review(rating, comments));

    product =
      product.applyRating(
        Rateable.convert(
          (int) Math.round(
            reviews
              .stream()
              .mapToInt(r -> r.getRating().ordinal())
              .average()
              .orElse(0)
          )
        )
      );

    // int sum = 0;
    // for (Review review : reviews) {
    // sum += review.getRating().ordinal();
    // }
    // // review = new Review(rating, comments);
    // int starAverage = Math.round((float) sum / reviews.size());
    // product = product.applyRating(Rateable.convert(starAverage));

    products.put(product, reviews);
    return product;
  }

  public void printProductReport(Product product) {
    List<Review> reviews = products.get(product);

    StringBuilder txt = new StringBuilder();
    txt.append(formatter.formatProduct(product));
    txt.append('\n');

    Collections.sort(reviews);

    if (reviews.isEmpty()) {
      txt.append(formatter.getText("no.review") + "\n");
    } else {
      txt.append(
        reviews
          .stream()
          .map(r -> formatter.formatReview(r) + "\n")
          .collect(Collectors.joining())
      );
    }

    System.out.println(txt);
  }

  public void printProducts(
    Predicate<Product> filter,
    Comparator<Product> sorter
  ) {
    // List<Product> productList = new ArrayList<>(products.keySet());
    // productList.sort(sorter);
    StringBuilder txt = new StringBuilder();
    // for (Product product : productList) {
    // txt.append(formatter.formatProduct(product));
    // txt.append('\n');
    // }

    products
      .keySet()
      .stream()
      .sorted(sorter)
      .filter(filter)
      .forEach(p -> txt.append(formatter.formatProduct(p) + "\n"));
    System.out.println(txt);
  }

  public Map<String, String> getDiscount() {
    return products
      .keySet()
      .stream()
      .collect(
        Collectors.groupingBy(
          p -> p.getRating(),
          Collectors.collectingAndThen(
            Collectors.summingDouble(
              product -> product.getDiscount().doubleValue()
            ),
            discount -> formatter.moneyFormat.format(discount)
          )
        )
      );
  }

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
      return MessageFormat.format(
        resources.getString("product"),
        product.getName(),
        moneyFormat.format(product.getPrice()),
        product.getRating().getStarts(),
        dateFormate.format(product.getBestBefore())
      );
    }

    private String formatReview(Review review) {
      return MessageFormat.format(
        resources.getString("review"),
        review.getRating(),
        review.getComments()
      );
    }

    private String getText(String key) {
      return resources.getString(key);
    }
  }
}
