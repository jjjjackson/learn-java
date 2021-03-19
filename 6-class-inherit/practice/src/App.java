public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Food p1 = new Food();
        Food p2 = new Food();

        System.out.println(p2 == p1);
        System.out.println(p2.equals(p1));
    }
}
