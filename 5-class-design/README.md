# 0z1 819 Lesson 5 Improved Class Design

## 筆記
# Modeling


# Overload Method
- 兩個 `Method` 在同一個 Class **有不同** 的 `Parameter`
  - 不同 Type 或 不同數字的 Parameter  
- 一定要有 `identical（完全一樣的）` Return Type

```java
public class Product  {
  public void setPrice(double price){}
  public void setPrice(BigDecimal price) {}
  public void setPrice (BigDecimal price, BigDecimal discount) {}
}
```

# Variable Number of Argument / vararg / 多參數
可以在無限多個 Inputs

```java
public int count(double... values) { //values 會是一個 Array
  values[0] ...
}
```

# Constructor
- 會自動加載 Class 裡面，Even 不宣告
- 不需要 `void` 這個 Keyword（考）
- `Method` 的名字要和 `Class Name` 一樣

```java
public class Product{
  public Product(){}
  public Product(String name) {...} // 可以 Overload
}
```

## Reuse Constructor

```java
public class Product {
  Product(String name, int age ) {
    this(name) // => reuse Product(String name)
    // 一定要在第一行 
    this.age = age
  }
  Product(String name) {
    this.name = name
  }
  // 如果沒有另外 define `Product()` 的話
  // 則不能用 new Product()
}
```

記得不要寫成 Cycle
```java
Product(a,b){
  this(a);
}

Product(a){
  this(a, default_b);
}
```

** Public ➡️ Package 外可看 ** 
** Protected ➡️ Package 外不可看 ** 

# Immutable
- 讓 Object 儘量 Immutable，不然 Java Pass Class 時是 Pass Reference 不是 Value
- private value
- 在 Constructor Initial Value
- 不要有 Set function
- 很多 Class都是 Immutable 如果想要改 Value 就 Copy
- String Time 和 Wrapper function 都是
- 因為 Thread safe， 如果不同的 Thread 都 Modify 同一個 Object Data 會有被改的可能
- 也可以全宣告成 final

```java
public class Box { 
  private static int maxId = 0
  private final int id

  // Instant initializer
  { id = ++max } // 這個會比 Constructor 還要早執行
  
  Box(String name ){
    ...
  }
}
```

# Enum
- 是個 Special type of class（考）
- fix set of instances（考）
- 直接就是 public static 和 final

```java 
public enum Condition {
  HOT, WARM, COLD;
}
```

## 用法
- 跟一般 class 一樣 `private Condition condition;`
- A marker of switch statement (考)

```java 
switch(condition) {
  case: Condition.COLD;
  ...
}
```

Enum 可 define variable 和 methods
```java
public enum Rating {
    NOT_RATED("000"),  
    ONE_STAR("001"),
    TWO_STAR("011"), 
    THREE_STAR("111"),
    
    private String stars; // 定義 Variable

    private Rating(String stars) {
        this.stars = stars;
    }

    public String getStarts() {
        return stars; //定義 Method
    }
}
```

# Java Memory 
Java 有兩種 Memory context： Stack 和 Heap

- stack
  - 存 Local variable 不管是 in method 還是 global
  - 只存 Primitive 和 Object Reference （考）
- heap
  - shared memory area, 可以被不同的 thread 或 different method access


# Parameter Passing
- Pass object  只會傳 Reference
  - 所以對丟進 method 的東西做 p.setxxx() 的話，會改到原本的值
- Pass primitive 丟的是 copy

詳情 Video 5.3

# Java memory clean up 

```java
var p1 = new Product()
p1 = null // 不會 Destroy Object from heap
```

### Destroy 的時機
- 有 Garbage Collection 的機制，會看有沒有 Pointer 指向ta
- 但沒有 `被指向` 也不會立即 Remove (耗CPU)
