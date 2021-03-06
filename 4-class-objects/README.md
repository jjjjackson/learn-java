# 0z1 819 Lesson 4 Class and Object

## ç­è¨

# UML Introduction
- Use Case              ð User çå·è¡åä½
- Class                 ð 
- Activity              ð æ¼ç®æ³ å Transaction
- Sequence              ð æ¼ç®æ³ å Transaction
- State Transaction     ð transã
- Deployment

## Class Diagram 
- public/private parameter
- public/private method ï¼åå« input/output type)
- Class ä¹éçç¹¼æ¿éä¿ å 1å°å¤ å¤å°å¤ 1å°1 ä¹é¡çéä¿


## Active / Sequence Diagram
å©åå¾åï¼é½æ¯å Modeling Interactive å Activities ç

#### Active
- Flow of Operation ð æ Event å» Trigger ä¸åä»éº¼ Flowï¼ç¶å¾ Trigger ç Outcome
- Activities and Decision

#### Sequence Diagram 
- Focus on "how one method involve to another"



# Class
## Design Class 
1. package name
2. import 
3. class ç public / private
4. class name
5. enclosed with `{` å `}` (è)
6. variable å method
   
Create Object
```
              => `new` æ Allocate Memory å­ Object
Product p1 = new Product();
         => reference åªæ Refer å° Object ç Memory 
         => æä»¥ p2 = new Product()
         => å¦æ p3 = p2
         => p3.setXXX ä¹ææ¹å° p2
```

## Variable
- default æ¯ private 
- å¤§é¨åä¹ç¨ private å»ä¿è­· Value ç¶å¾ç¨ set/get function è¨­å®æget
- primitive default æ¯ 0/false
- object default æ¯ Null ð ç¡éå Initial


```
public class Product {
    private LocalDate expiry = LocalDate.ofValue('...');
}
```

å¯ä»¥ç¨ `var` ä»£æ¿åæå®£åï¼ç¶ä» unambiguous 
```
var value1 = "Hello" // String
å¯ä»£æ¿
String value1 = "Hello"
```
**Java å¨ Compile çæå æææ±è¥¿é½æ¯æ Type ç**

`var` ä½¿ç¨ææ©
- Local variable ä¸æ Initial
- For Loop ç Index

## Method
- å®è¦æ Return Statement (è) `EX private ãvoidãgetXXX`
- method åå­çèµ·ä¾è¦ååè©
- enclosed with `{` å `}` (è)
- ç¨  `this` ä¾ç¶ Class ç Variable (è) `Ex: this.name = "a" `


## Constant / final
- ä½¿ç¨ ééµå­ `final` ð `private final String name`
- åªè½ Assign ä¸æ¬¡

final å¯ä»¥å®£åå®åå 
```java
BigDecimal a = BigDecimal.ofValue('123');
final a
```

```java
public BigDecimal getTotalWithDiscount(final BigDecimal Discount) {
                                        // ç¢ºä¿ Function è£¡é¢ç `Discount` ä¸æè¢«åå°
}
```

## Static
- `static Method/Variable` ä¸å±¬æ¼ä»»ä½ Instance ï¼èï¼
- å¨ææç Instance ä¹é Share  (Memory æéº¼éä½ç ð¤? æ  Import å°±è¦ initial ç©ºéï¼)
- ä¸éè¦Object reference
- Initial åªè·ä¸æ¬¡

```java
public class Product() {
    static double tax = 0
    static void setTax(double tax){
        this.tax = tax
        ...
    }
}
public class Main() {
    product.setTax(0.001) // ææ¹åææ class ç tax
}
```

Static å Final å¯ä»¥æ··åä½¿ç¨ ð æçº Global ç Constant

**ä½¿ç¨ææ©(è)**
- Main function
- æ¸å­¸å½å¼
- Factory


