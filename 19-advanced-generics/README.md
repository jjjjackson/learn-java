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


## Hierarchy
Array 沒有 Generic
```java
Product[] product = new Food[10];
product[0] = new Drink(); // 這個會有 Run Time Error: java.lang.ArrayStoreException
// 但是 compiler 不會告訴你
```
☝ 這個叫 `variant`

但 collection 是 `invariant` 所以他會告訴你
```java
List<Product> products = new ArrayList<Food>(); // Compiler 會報錯
```

但不會 Check Raw Type
```java
List<Food> foods = new ArrayList<Food>();
List values = foods; // 這個時候 Type 就丟失了
```


## wildcard generics
`<?>` unknown type

ex: `List<?> listOfUnknownType` 👈 這個會是個 `readonly` list

或 `List<? extends ParentType>`

### Lower Bound Wildcard
`List<? super Type>` 會變成 Writable 👉 **contra-variant**


## Summary 
- Normal Generic 是 `invariant` 👉 read-write generic
- <? super LowerBoundType> 是 `contravariant` 👉 writable generic
- <? extends UpperBoundType> 是 `covariant` 👉 read-only generic
- 但盡量 Avoid Raw type



