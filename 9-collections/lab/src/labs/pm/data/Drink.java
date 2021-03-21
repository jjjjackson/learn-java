package labs.pm.data;

import java.math.BigDecimal;

public class Drink extends Product {

    Drink(int id, String name, BigDecimal price, Rating rating) {
        super(id, name, price, rating);
    }

    // public int getId() {
    // return id;
    // }

    // public String getName() {
    // return name;
    // }

    // // public void setName(final String name) {
    // // this.name = name;
    // // }

    // public BigDecimal getPrice() {
    // return price;
    // }

    // // public void setPrice(final BigDecimal price) {
    // // this.price = price;
    // // }

    // public BigDecimal getDiscount() {
    // return price.multiply(DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP);
    // }

    // public String getRate() {
    // return rating.getStarts();
    // }

    public Drink applyRating(Rating newRating) {
        return new Drink(getId(), getName(), getPrice(), newRating);
    }
}
