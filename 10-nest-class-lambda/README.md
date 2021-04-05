# 0z1 819 Lesson 10 Net Class

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

## Final Class
- Ex String Math 等等
- `Final Class` 不能被繼承，不能被 Override，執行速度比一般 Class 快
- 很少用
[https://blog.csdn.net/xv1356027897/article/details/79515712](https://blog.csdn.net/xv1356027897/article/details/79515712)



  

