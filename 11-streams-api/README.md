# 0z1 819 Lesson 11 Streams API

# Stream
- **immutable flow** of elements
- 有利於大量的 Dat 查找， 有點類似 SQL 的概念
- Stream 是惰性計算，他不會一次把所有的 Data 都 Load 進來
  - [Lambda 的 Memory 消耗](https://blog.csdn.net/weixin_32448581/article/details/114558893)
- 好處 
  - immutable
  - lazy evaluation
  - use lambda
- 可以用 `DoubleStream`, `IntStream`, `LongStream` 來 **Reduce Unboxing** `Integer` Class 的次數
- 可以用 `generate` 來產生數組 `IntStream.generate(() -> (int)Math.random()*10)).takeWhile...`

```java
List<Product> menu = new ArrayList<>();

// Loop
for(Product p: menu){
  if(p instanceof Food) {
    p.setName(p.name+"123");
  }
}

// Stream
menu
  .stream()
  .parallel()
  .filter(p -> p instanceof Food)
  .forEach(p -> p.setName(p.name + "123")); // 盡量不要這樣用，讓他 Immutable
```

可以
```java
IntStream
  .range(0, 10)
  .parallel()
  .forEach(i -> menu.add(new Product(String.valueOf(i) + "Cake")));
```

```java
var i = IntStream.range(0, 10).toArray(); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
System.out.println(Arrays.toString(i));
```

# Stream Operation 
- intermediate     // 不可當結尾，(有分 Stateful 和 Stateless)
  - filter, map, peek, distinct(Unique), sorted, skip, dropWhile, takeWhile 之類的
- terminal         // 可當結尾
  - forEach, count, min, max, sum, average, reduce(), collect, allMatch, anyMath(是否存在), findAny(找其中一個) ...
- short - circuit  // 遇到某條件就終止操作

# Function Purpose
- Predicate       // tests (ex filter)
- Function        // 轉 Type
- UnaryOperator   // 轉 Value
- Consumer        // 處理 Element
- Supplier        // 生成 Element
  
```java
Stream.generate(<Supplier>)
      .filter(<Predicate>)
      .peek(<Consumer>)
      .map(<Function> / <UnaryOperator>)
      .forEach(<Consumer>)
```

# Functional Interface 
- 剛剛的 Purpose 都有相對的 interface
- 在 `java.util.function`
- Predicate       Predicate<T>      // return boolean 的去 Filter
- Function        Function<T,R>     // 將 R 轉 T
- UnaryOperator   UnaryOperator<T>  // 轉 Value
- Consumer        Consumer<T>       // 處理 Element
- Supplier        Supplier<T>       // 生成 Element, defined T get() 去 produce Data

# Primitive Variant Functional Interface
有點多，但主要就是為了 Auto-unboxing

考點是對 Method Name 的敏感度

Ex: `ToIntFunction` 要知道他是 Function 然後是轉 int 用的

# Consumer 疊加
- 可以用 addThen 疊加 
- 或是用 `peek`
```java
Consumer<Product> editName = p -> p.setName(p.name + "aaaa");
Consumer<Product> editName2 = p -> p.setName(p.name + "bbbb");

menu
  .stream()
  .forEach(editName.andThen(editName2));

// 或是用 `peek`
menu
  .peek(editName) // Stream<T> peek(Consumer<? super T> action)
  .forEach(editName);
```

[Peek Reference](https://www.cnblogs.com/flydean/p/java-8-stream-peek.html)

# Map 疊加
同上，但是 Type 要對
```java
Function<Product, String> nameMapper = p -> p.getName();
UnaryOperator<String> trimMapper = n -> n.trim();
ToIntFunction<String> lengthMapper = n -> n.length();
list.string().map(nameMapper.andThen(trimMapper)).mapToInt(lengthMapper).sum();
```

# Collectors
- `Collector` 是 Interface `Collectors` 是 Class
- 可以做
  1. 計算 average, min max, count, sum
     1. Ex `DoubleSummaryStatistics`
  2. mapping 或 join
     1. `mapping`
  3. 包裝結果成 list set 或 map
     1. `toList`, `partitioningBy`, `groupingBy`

Summary 
```java
DoubleSummaryStatistics s2 = menu
      .stream()
      .collect(Collectors.summarizingDouble(p -> p.name.length()));
      // DoubleSummaryStatistics{count=4, sum=16.000000, min=3.000000, average=4.000000, max=5.000000}
```

Mapping
```java
String s2 = menu
      .stream()
      .collect(Collectors.mapping(p -> p.name, Collectors.joining(",")));
      // Cake,Bego,Tea,Asamu
```

Gathering
```java
List<Product> s2 = menu
      .stream()
      .filter(p -> p instanceof Food)
      .collect(Collectors.toList());
      //[Cake, Bego]
```

Collector 也可以用 `collectingAndThen` 將 Function apply to Collector
```java
String s2 = menu
        .stream()
        .collect(
          Collectors.collectingAndThen(
            Collectors.averagingInt(p -> p.name.length()),
            n -> String.valueOf(n) // 可以做 Formatting
          )
        );
```

`PartitioningBy` =>  把東西分成 True 和 False 兩堆 （用 `Predicate`）
```java
var s2 = menu
      .stream()
      .collect(Collectors.partitioningBy(p -> p instanceof Food));
      //{false=[Tea, Asamu], true=[Cake, Bego]}
```

`Grouping` =>  依照東西分類 (用 `Function`)，結果會是 Map
```java
var s2 = menu.stream().collect(Collectors.groupingBy(p -> p.getClass()));
//{class Drink=[Tea, Asamu], class Food=[Cake, Bego]}
```

還有 `flatMapping` `filtering` 等等的功能


# Parallel
使用時機
- 只有足夠多的 Elements
- CPU有多Core
- 每一個 Processing 需要大量 CPU resource 的時候
- **不要在需要 Sequential 操作的時候用 Shared**
- **不要在 Parallel 時往 List 加東西**

```java
List<...> prices = new ArrayList<>();
list.stream()
  .parallel()
  .peek(p->System.out.println(p)) // ❌ 因為 Console 是單一 Resource， 所以大家都得等輸出
  .map(...)
  .forEach(p -> list.add(..)) // ❌ 不要再往裡加東西
```

```java
List<BigDecimal> price = list.stream()
  .parallel()
  .map(p -> p.getPrice())
  .collect(Collectors.toList()); // ✅
```


# Predict Interface
可以讓 `Predicate` Combine `Lambda`
有 `and` `or` `negate` `not` `isEqual` 等等
```java
menu.removeIf(foodFilter.negate().or(priceFilter));
```







