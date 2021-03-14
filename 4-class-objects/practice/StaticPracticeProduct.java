package practice;

import java.time.Period;

public class StaticPracticeProduct {
    private String name = "test name";
    // private Period period = Period.ofDays(2);

    private static Period defaultExpiryPeriod; // 宣告
    static { // Initial
        defaultExpiryPeriod = Period.ofDays(3);
    }

    public static void setExpiryPeriod(int day) {
        defaultExpiryPeriod = Period.ofDays(day);
        // this.period = Period.ofDays(day); 👉 Ide 都不會給過的
    }
    
    public static Period getExpiryPeriod() {
        return defaultExpiryPeriod;
    }
    
    public String getName() {
        return this.name;
    }
}
