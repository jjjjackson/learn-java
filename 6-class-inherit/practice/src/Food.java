import java.math.BigDecimal;

public class Food extends Product {
    public BigDecimal price = BigDecimal.valueOf(20);

    @Override
    public BigDecimal getPrice() {
        // TODO Auto-generated method stub
        return this.price;
    }
}
