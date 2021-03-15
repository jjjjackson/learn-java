# 0z1 819 Lesson 4 Class and Object

## 筆記

# UML Introduction
- Use Case              👉 User 的執行動作
- Class                 👉 
- Activity              👉 演算法 和 Transaction
- Sequence              👉 演算法 和 Transaction
- State Transaction     👉 transあ
- Deployment

## Class Diagram 
- public/private parameter
- public/private method （包含 input/output type)
- Class 之間的繼承關係 和 1對多 多對多 1對1 之類的關係


## Active / Sequence Diagram
兩個很像，都是做 Modeling Interactive 和 Activities 的

#### Active
- Flow of Operation 👉 有 Event 去 Trigger 一個什麼 Flow，然後 Trigger 的 Outcome
- Activities and Decision

#### Sequence Diagram 
- Focus on "how one method involve to another"



# Class
## Design Class 
1. package name
2. import 
3. class 的 public / private
4. class name
5. enclosed with `{` 和 `}` (考)
6. variable 和 method
   
Create Object
```
              => `new` 會 Allocate Memory 存 Object
Product p1 = new Product();
         => reference 只會 Refer 到 Object 的 Memory 
         => 所以 p2 = new Product()
         => 如果 p3 = p2
         => p3.setXXX 也會改到 p2
```

## Variable
- default 是 private 
- 大部分也用 private 去保護 Value 然後用 set/get function 設定或get
- primitive default 是 0/false
- object default 是 Null 👉 盡量先 Initial


```
public class Product {
    private LocalDate expiry = LocalDate.ofValue('...');
}
```

可以用 `var` 代替型態宣告，當他 unambiguous 
```
var value1 = "Hello" // String
可代替
String value1 = "Hello"
```
**Java 在 Compile 的時候 所有東西都是有 Type 的**

`var` 使用時機
- Local variable 且有 Initial
- For Loop 的 Index

## Method
- 定要有 Return Statement (考) `EX private 【void】getXXX`
- method 名字看起來要像動詞
- enclosed with `{` 和 `}` (考)
- 用  `this` 來當 Class 的 Variable (考) `Ex: this.name = "a" `


## Constant / final
- 使用 關鍵字 `final` 👉 `private final String name`
- 只能 Assign 一次

final 可以宣告完再加
```java
BigDecimal a = BigDecimal.ofValue('123');
final a
```

```java
public BigDecimal getTotalWithDiscount(final BigDecimal Discount) {
                                        // 確保 Function 裡面的 `Discount` 不會被動到
}
```

## Static
- `static Method/Variable` 不屬於任何 Instance （考）
- 在所有的 Instance 之間 Share  (Memory 怎麼運作的 🤔? 有  Import 就要 initial 空間？)
- 不需要Object reference
- Initial 只跑一次

```java
public class Product() {
    static double tax = 0
    static void setTax(double tax){
        this.tax = tax
        ...
    }
}
public class Main() {
    product.setTax(0.001) // 會改動所有 class 的 tax
}
```

Static 和 Final 可以混合使用 👉 成為 Global 的 Constant

**使用時機(考)**
- Main function
- 數學函式
- Factory


