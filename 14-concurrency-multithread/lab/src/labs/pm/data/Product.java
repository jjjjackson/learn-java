package labs.pm.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Product implements Rateable<Product>, Serializable {

  private static final long serialVersionUID = 1L;
  private static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);
  private int id;
  private String name;
  private BigDecimal price;
  private Rating rating;

  Product() {
    this(0, "no name", BigDecimal.ZERO);
  }

  Product(int id, String name, BigDecimal price, Rating rating) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.rating = rating;
  }

  Product(int id, String name, BigDecimal price) {
    this(id, name, price, Rating.NOT_RATED);
  }

  @Override
  public int hashCode() {
    return this.id;
  }

  @Override
  public String toString() {
    return (
      getClass().getName() +
      ", " +
      id +
      ", " +
      name +
      ", " +
      price +
      ", " +
      rating
    );
  }

  @Override
  public boolean equals(Object obj) {
    // optimise 下面的寫法
    if (this == obj) {
      return true;
    }

    if (obj instanceof Product) {
      final Product other = (Product) obj;
      return this.id == other.id && Objects.equals(this.name, other.name);
    }
    return false;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getDiscount() {
    return DISCOUNT_RATE;
  }

  @Override
  public Rating getRating() {
    return rating;
  }

  // 不用再定義一次
  // public abstract Product applyRating(Rating newRating);

  public LocalDate getBestBefore() {
    return LocalDate.now();
  }
}
