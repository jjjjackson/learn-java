# 0z1 819 Lesson 19 advanced generic

## Generic 在 Java 怎麼運作的
舊版的
```java
public class Some<T> {}
// 會被 Compile 成 Object, 因為所有的都係都是 Object
public class Some {  
    public Object apply Object(t) {... }
}

```java
Some<String> s = new Some<>();
String v = s.apply("");
// 會被 Compile 成
Some<String> s = new Some<>();
String v = (String) s.apply(""); // 把 Object 轉回原來的 Type
```

如果是 interface 會 Compile 出 Bridge Function
```java
public class Some implements App<String> {
     public String apply(String s) {
         return s;
     }
}
// 會被 compile 成
public class Some implements App<String >{
    public String apply(final String s) {
        return s;
    }
    /** bridge*/
    public Object apply(final Object o) { // 變成 Object 為了Compatible
        return this.apply((String) o);
    }
}
```



