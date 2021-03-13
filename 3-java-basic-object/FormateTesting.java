import java.math.*;
import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class FormateTesting {
    public static void main(String[] args) {
        // String Formatting
        Locale locale = new Locale("jp", "JP");
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat percentageFormat = NumberFormat.getPercentInstance(locale);
    
        // Message formatting
        ResourceBundle messageTemplate = ResourceBundle.getBundle("messages", locale);
        String offerPattern = messageTemplate.getString("offer");
        String dateFormat = messageTemplate.getString("dateFormat");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat, locale);

        BigDecimal salary = BigDecimal.valueOf(12000000);
        BigDecimal bonusRate = BigDecimal.valueOf(0.2);
        BigDecimal total = salary.multiply(bonusRate.add(BigDecimal.valueOf(1)));
        ZonedDateTime today = ZonedDateTime.now(zoneId);

        String salaryText = currencyFormat.format(salary);
        String bonusPercentageString = percentageFormat.format(bonusRate);
        String totalString = currencyFormat.format(total.setScale(2,RoundingMode.HALF_DOWN));
        String offerDate = dateTimeFormatter.format(today);

        String message = MessageFormat.format(offerPattern, 
                                            salaryText, 
                                            bonusPercentageString, 
                                            totalString, 
                                            offerDate);
        
        System.out.println(message);
    }   
}