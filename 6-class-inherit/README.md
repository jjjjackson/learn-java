# 0z1 819 Lesson 6 class Inherit

## 筆記

## Class
- 如果沒有 Extend 其他 Class，Default 會 Extend `Object (java.lang.Object)` （考）
  - java.lang.Object 提供 `toString` `equals` `hascode` `clone` 之類的 Basic Function
- 可以一直繼承下去
- 只能有一個 Parent (考)

```java
public class Product() {}
// 事實上是
public class Product extends Object() {}
// 繼續繼承下去
public class Food extend Product() {}

var p = new Product();
String s = p.toString(); // 這個是繼承 java.lang.Object 來的
```

## Memory 
Memory 會 Create parent Object
```java
var p = new Product();
// [ Object | Product ]
var f = new Food();
// [ Object | Product | Food ]
var d = new Drink();
// [ Object | Product | Drink ]
//   < --------------------- Method 可用 ✅
//   --------------------- > Method 不一定可以用而且不推 👎 
```

```java
Food x1 = new Food
Object x2 = (Object) x1     // ✅
Product x3 = (Product) x2   // 👎 可能會過，但是不推
```


## 確認 Type 的方法（考）
```java
if(p instanceof Food ) {
    //...
}
```

## Shadowing Station（考）
- Parent 的 Variable 是 Public，但 Child 有同名的 Variable

```java
public class Product { public discount }
public class Food extends Product { 
    public discount 
    Food() {
        super()
        this.discount  // 自己的
        super.discount // Parent 的
    }
}
```

## Constructor
- 有 Extends 但沒寫 Constructor，【而且 Parent 沒有 Param 的話】會自動加 `super`

手動
```java
public class Product { Product(String name) }
public class Food { 
    Food(String name) {
        super(name); // 必須在第一行 (考)
    }
}
```

## Override Function
- `@Override` 是可以選擇加或不加，加了可以幫助 Compile 檢查錯誤，可以避免 Typo 之類的（屬於一種 Annotation）（考）
- 不可以把 public 改 private
- 只能擴張不能收縮 private -> protect -> public 👍
- 
    
```java
@Override
public int Discount() {}
```

**！！！記得**
```java
Food f = new Food();
Product p = (Product) f;
p.getXXX() // 會用 Food 的 getXXX 而不是 Public 的
```


## Abstract

- 表示一個Concept 👉 Generic
- 為了 extend 其他的 concrete subclass
- 不能 New 出結果
- 如果是 Abstract 的方法一定要 Override (考)
- 如果是 Abstract 就不會去看 Abstract 的 Method，會直接找 concrete subclass (要試試看)

### Abstract 和 Interface 的不同
```
1.
interface的方法全都是抽象方法
abstract則有一般方法和抽象方法
抽象方法指的是沒有「實作」出來的方法


2.
interface與abstract類別都不能直接用來建立物件實體，
必須由一個標準類別來繼承它，實作其抽象方法，
然後再以此建立物件實體。


3.
interface可多重繼承interface
abstract只能單一繼承


3.
interface沒有建構子
abstract有建構子


4.
interface沒有內建的super與this變數
abstract有內建的super與this變數


5.
interface的成員變數全都是static final型式
abstract類別的成員變數比照一般類別


6.
interface內的方法只可封裝public、default
abstract內的方法只可封裝public、default、protected


7.
需要使用迂迴的方式去找出實體類別間的對應關係，
所以與抽象類別的速度相比之下較為緩慢
```

## Final Class
- Ex String Math 等等
- `Final Class` 不能被繼承，不能被 Override，執行速度比一般 Class 快
- 很少用
[https://blog.csdn.net/xv1356027897/article/details/79515712](https://blog.csdn.net/xv1356027897/article/details/79515712)


## 常被 Override 的 Method
#### `toString()` 
- 所有的 Class 都有 `toString()` 
- 一般只會 return className 
#### `equals()`
```java
p1 = new Product();
p2 = new Product();
p1 = p2 // false        👉 都是看Pointer
p2.equals(p1) // false  👉 都是看Pointer
```

所以要自己 Customize 👉 固定寫法
```java
public boolean equals(Object obj) {
    if( this == obj) {return true }
    if(!(obj instanceof Product)) { return false; }
    Product Other = (Product) obj; // 如果 是Food，Drink 之類的要轉換
    return this.id == other.id;
}
```

## HashCode
- 如果兩個 Object 一樣，需要 Return 一樣的 HashCode
- HashCode 是 Immutable 一但建立了就不能改
- HashSet, HashMap, HashTable 在 Java 是用 hashCode 實現的 （所以很重要）
- Hashcode 只是 Identity Of Object，而不是Secure HashCode

```java 
// 可以
public int hashCode() {
    return this.id
}
// or 
public int hashCode() {
    return Objects.hash(name, ...)
}
```

## Compare String
String 會拖到 `String Pool` 所以
```java
var a = "aaa";
var b = "aaa";
a == b // True
a.equals(b) // True
```

## Factory Method
- Factory 可以不 return generic 而是更細的 Object
- （這個其實很重要，因為 Java 的 Type 吃的很重，很多 Generic Type 的 create Object 都需要靠這個）

```java
public class ProductFactory { 
    public static Product createProduct(productType, ...) {
        switch(productType) {
            case Food:
                return new Food(...);
            ...
        }
    }
}
```

## Promote Method 到 Generic Class 的原則
是否需要用到
- 有需要的 Value ？
- 是否需要給 Default Value？
