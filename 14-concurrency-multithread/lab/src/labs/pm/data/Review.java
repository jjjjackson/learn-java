package labs.pm.data;

import java.io.Serializable;

public class Review implements Comparable<Review>, Serializable {

  private static final long serialVersionUID = 1L;
  private Rating rating;
  private String comments;

  Review(Rating rating, String comments) {
    this.rating = rating;
    this.comments = comments;
  }

  public Rating getRating() {
    return rating;
  }

  public String getComments() {
    return comments;
  }

  @Override
  public String toString() {
    return "Review ( " + "rating=" + rating + ", comments=" + comments + ")";
  }

  @Override
  public int compareTo(Review other) {
    // 只需要 >0 <0 或 ==0 不一定要 1 -1 0
    return other.rating.ordinal() - this.rating.ordinal();
  }
}
