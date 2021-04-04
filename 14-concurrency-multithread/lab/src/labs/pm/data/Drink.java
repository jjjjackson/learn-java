package labs.pm.data;

import java.math.BigDecimal;

public class Drink extends Product {

  private static final long serialVersionUID = 1L;

  Drink(int id, String name, BigDecimal price, Rating rating) {
    super(id, name, price, rating);
  }

  public Drink applyRating(Rating newRating) {
    return new Drink(getId(), getName(), getPrice(), newRating);
  }
}
