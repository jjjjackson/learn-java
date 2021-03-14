package practice;

public class StaticPractice {

    public static void main(String[] args) {
        StaticPracticeProduct.setExpiryPeriod(4);
        var period = StaticPracticeProduct.getExpiryPeriod();
        System.out.println(period);      
    }
}
