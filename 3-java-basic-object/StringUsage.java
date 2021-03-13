public class StringUsage {
    public static void main(String[] args) {
        String a = "Hello";
        String b = "Hello";

        System.out.println(Integer.toHexString(a.hashCode()));
        System.out.println(Integer.toHexString(b.hashCode()));

        String c = "He";
        String d = "llo";
        String e = c.concat(d);
        System.out.println(Integer.toHexString(e.hashCode()));
    }   
}