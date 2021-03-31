package labs.pm.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Product implements Rateable<Product> {
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
        ;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public String toString() {
        return getClass().getName() + ", " + id + ", " + name + ", " + price + ", " + rating;
    }

    @Override
    public boolean equals(Object obj) {
        // optimise 下面的寫法
        if (this == obj) {
            return true;
        }

        // if (obj != null && getClass() == obj.getClass()) {
        // 如果是上面這行，Food 和 Drink 的 getClass() == obj.getClass() 會不一樣
        // 也就是 Sub Class 會因此不能 equals
        if (obj instanceof Product) {
            final Product other = (Product) obj;
            return this.id == other.id && Objects.equals(this.name, other.name);
            // 用 Objects.equals 是為了避免兩個都是 null 的情況，不然他們還是會 Return true
        }
        return false;

        // if (this == obj) {
        // return true;
        // }
        // if (obj == null) {
        // return false; // 這個其實是要看你下一步是什麼
        // // 這裡因為下一步需要看 getClass 所以需要確定他不是 null
        // }
        // if (getClass() != obj.getClass()) {
        // return false;
        // }

        // final Product other = (Product) obj;

        // if (this.id != other.id) {
        // return false;
        // }

        // if (!Objects.equals(this.name, other.name)) {
        // return false;
        // }

        // return true;
    }

    public int getId() {
        return id;
    };

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
