# 0z1 819 Lesson 9 Collections

# Collection
- 可以用 Generic
- 可以擴張 👉 Array 不行
- 可以 `Search`, `Validation(Ex Unique)`, `Sorting`
- thread-safe operation (考)
  - https://my.oschina.net/u/2308739/blog/835697
  - https://blog.csdn.net/liangking81/article/details/80790085?utm_source=blogxgwz2
- 有一些 Interfaces 👉 要知道怎麼用和刷要什麼

# Collection Interface
- 屬於 `java.util`
- interface 都是 Generic 的
  - **<E>** 的話一定是 Object，不能用 primitive
  - Iterable <T>    👉 Top-Level Interface 為了讓所有的 Collection 都可以用 ForLoop
  - Collection<E>   👉 給 `collection`，`add()`, `remove()`, `toArray()` `removeIf()` 的 Method
  - List<E>         👉 index 是 int 的 element list
  - Set<E>          👉 a set of `unique` elements
  - SortedSet<E>    👉 set + order
  - Dequeue<E>      👉 可以 Stack 也可以 Heap
  - Map<k,v>        👉 有 `unique key` 和 `list of value` 的組合 (這個很重要)
    - 在 Java 很常拿 `Object`  作為 Key
      - ex： `Map<Product, List<Integer>>`
      - 因為 Object 也是 Pointer

# Collection API Class
- ArrayList<E>
  - 最常用到
  - 就是在 Implement List
- HashSet<E>
  - implement Set
- TreeSet<E>
  - implement sortedSet
- HashMap<k,v>
  - implement Map

# Collection 的關係
Collection和 Map 沒有關係
- Iterable
  - Collection
    - List
      - ArrayList
    - Set
      - HashSet
      - SortedSet
        - TreeSet
    - Dequeue
      - ArrayDequeue
  - Map
    - HashMap

# Create List Object
- `java.util.ArrayList`
- 跟 Array 很像，但可以 `add()`
- 會自動 Extend Internal Storage
- Default 的 Capability 是 10
  - `List<Product> list = new ArrayList<>()` 👉 Capability 是 10
  - `List<Product> list = new ArrayList<>(2)` 👉 Capability 是 不知道
- Fix Sized Array 創建
  - `Arrays.asList(<T> ... )` 👉 再用 `add()` 的話會有 `java.lang.UnsupportedOperationException`
- readonly instance 創建
  - `List.of(<T>...)`


```java
Product p1 = new Food("Cake");
Product p2 = new Drink("Tea");
List<Product> menu = new ArrayList<>(2);
// Casting 到其他 Interface 時比較 Flexible

menu.add(p1);
menu.add(p2);
menu.add(2 //這個是 Index, null);
menu.remove(0); // remove by index
menu.remove(p2) // remove 最前面的 p2
menu.get(index).setName("Cake") // 不用換行
menu.add(8, p2); // 不能 Jump (java.lang.IndexOutOfBoundsException)
```


# Create Set Object
- `java.util.HashSet`
- Default 的 Capability 是 16
- Set 是用 Hash Code 分區放
  - 找 Unique 只找某個區間 👉 不然會很慢
- Set 是可以 Add null 的，要注意

```java
Product p1 = new Food("Cake");
Product p2 = new Drink("Tea");
Set<Product> menu = new HashSet<>();

menu.add(p1);
menu.add(p2);
menu.add(p2); // 不會被加進去
```

# Create a dequeue
- 如果是 Empty 會 Return `null`


```java
Product p1 = new Food("Cake");
Product p2 = new Drink("Tea");
Dequeue<Product> menu = new ArrayDequeue<>();

menu.pollFirst();       // Return null
menu.offerFirst(p1);    // 插入 Top
menu.pollFirst();       // 拿 Top
menu.peekFirst();       // 只看 Top 不拿
menu.offerLast(p2);     // 插入 Bottom
menu.pollLast();        // 拿 Bottom
menu.peekLast();        // 只看 Bottom 不拿

menu.offerFirst(null);  // 會有 Runtime Error java.lang.NullPointerException
```

```
Heap -> OfferFirst
     -> pollLast

Stack -> OfferFirst
      -> pollFirst
```



# Create Hash Map
- 操作時 Immutable Object
- 
```java
Product p1 = new Food("Cake");
Product p2 = new Drink("Tea");
Dequeue<Product> menu = new ArrayDequeue<>();

Map<Product, Integer> items = new HashMap<>();

items.put(p1, Integer.valueOf(2)); // -> {p1,2}
items.put(p1, Integer.valueOf(5)); // 會取代 2 -> {p1,5}
items.putIfAbsent(p1, Integer.valueOf(2)) // 可以 check 沒 key 再 Push

items.containsKey(p2);              // 找有沒有 key
items.containsValue(Integer.valueOf(5)); // 找有沒有 value
```

# Collections Iterate 
```java
for( Product product =  menu) {...}

//or

Iterator<Product> iter = menu.iterator();
while(iter.hasNext()) {
    var a = iter.next();
    iter.remove(); // 還可以順便從 List 中 Remove 
}
```

# Map Iterate
```java
Map<Product, Integer> items = new HashMap<>();

Set<Product> keys = items.keySet();
Collection<Integer> values = items.values(); // 

for (Product product: keys) {...}
for (Integer integer: values) {...}
```

# java.util.Collections
- 有 `s` 這個是個 Class， 提供很多的 Static methods
- 有提供 fill, sort, shuffle, binarySearch, reverse
用法會像是
```java
List<Product> menu = new ArrayList<>();
Collections.sort(menu); // 記得要先 Impl Comparable<Product>
```
[例子](https://howtodoinjava.com/java/sort/collections-sort/)


# 如果 Multiple Thread 時需要同一個 Collection 的話？
- Object `需要是 Immutable`
  - 不然 Data 順序 or Get 之類的會亂
- Instance 是否需要 Immutable 要另外考慮

### 3 個 Solution
- 快 Readonly      👉 unmodifiableSet(set) 或 unmodifiableList(list);
- 慢 Sync          👉 Collections.synchronizedMap(set); // 使用時會上鎖 另外一個 Thread 要等
- 快 Copy-on-write 👉 有 Data Collection 的風險，很耗 Memory


# Legacy Collection Class
- Vector    => ArrayList 
- hashTable => HashMap
👉 已經不用了

### 差別
Vector 和 HashTable Default 是 Sync
ArrayList 和 HashMap 是 Thread Safe


