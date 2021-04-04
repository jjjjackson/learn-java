package labs.pm.data;

// @FunctionalInterface
public interface Rateable<T> {
  public static final Rating DEFAULT_RATING = Rating.NOT_RATED;

  T applyRating(Rating rating);

  // 如果用 functionalInterface 的話只弄有 1 個 method
  // 如果有下面這行就會報錯
  // void test();

  public default T applyRating(int stars) {
    return applyRating(convert(stars));
  }

  public default Rating getRating() {
    return DEFAULT_RATING;
  }

  public static Rating convert(int stars) {
    // Rating.values() 是所有的 Enum 都有 的
    return (stars >= 0 && stars <= 5) ? Rating.values()[stars] : DEFAULT_RATING;
  }
}
