# 0z1 819 Lesson 8 Array and Loop

## 筆記

# Array

- Array is a `fixed-length` collection of same type (考)
- Index 是 `int` (考)
- array of primitive 的 Default 是 False 或 0 (考)
- array of Object 的 Default 是 Null
- No need to `extract element to operate (考)
  - 可以 `offers[1].get()` 的意思
  

## 宣告方法
```java
Product[] products = new Product[3]
```

```java
Product[] products;
products = new Product[]{
    new Food("Fish"),
    new Drink("Coke"),
    ...
};
```

```java
Product[] products = {
    new Food("Fish"),
    new Drink("Coke"),
    ...
};
```

#### 2D

```java
int [][] matrix = new int[3][3];
Product[][] products = {{...}, {...}};
```

## 不能改Array Size，只能 copy
```java
System.arraycopy(from_array, from_index, target, 從target的哪開始放, copy幾位);
或
Arrays.copyof(b1, 5);   👉 可以比 b1 長
```

## Arrays Class　
- -> java.util.Arrays
- 有 .fill .sort .binarySearch ...
- sort 可以 customize `.sort(name2, new LengthCompare)`

```java
public class LengthCompare implements Comparator<String> {
    public int compare(String s1, String s2) {
        if(s1.length() > s2.length()) { return 1; }
        if(s1.length() < s2.length()) { return -1; }
        return 0
    }
}

Arrays.sort(testArray, new LengthCompare());
```

# Loops

```java 
while() {...}
do{ ... } while(...) 👉 至少會被執行一次
for( ;... ;) { ... }
```

## foreach
```java
for( Product product: products) {
    ...
}
```

break 和 continue 可以用 Label 控制
```java 
testLabel:
for( ... ) {
    for( ... ) {
        if() { continue testLabel} // 👉 會 Break 這個 for 然後從 外迴圈 開始
        if() { break }             // 👉 會 Break 這個 for 然後從 外迴圈 開始
        if() { break testLabel}    // 👉 會 Break 外迴圈 結束迴圈
    }
}
```
