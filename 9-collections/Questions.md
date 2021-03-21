# Examine the following code:
Ans: 2, 3

```
Map<Integer, String> items = new HashMap<>();
items.put(Integer.valueOf(1),"Tea"); 
items.put(Integer.valueOf(2),"Cake");
```

Which statements are true? (Choose two)
1. items.put(Integer.valueOf(2),"Cake"); will throw an exception.
2. items.put(Integer.valueOf(3),"Cake"); will create an additional element with a "Cake" value.
3. items.put(Integer.valueOf(1),"Coffee"); will replace "Tea" with "Coffee."
4. items.put(Integer.valueOf(3),"Tea"); will replace the integer key value for the "Tea" element.


# Which statements are NOT true about the Java Collection API? (Choose two)
Ans: 2, 3
1. All collections allow programs to store groups of objects in memory.
2. All collections provide thread-safe operations.
3. All collections are implemented internally by using arrays.
4. All collections dynamically expand as appropriate to accommodate new elements.

解說：
Vector 和 HashTable 這兩個 Legacy Class 是 Sync 的


# Which statements are true about Deque? (Choose three)
Ans: 1, 2, 5
1. Implementations of ArrayDeque will auto-expand.
2. Null values are not allowed.
3. If the deque is empty, pool and peek operations throw an exception.
4. peekFirst(T) and peekLast(T) returns and removes the element at the head or tail of the Deque.
5. offerFirst(T) and offerLast(T) insert elements at the head and the tail of the deque.


# Which statement is true about Set?
Ans: 4
1. Only one null element is allowed in the Set.
2. Add and Remove methods throw an exception when attempting to add a duplicate or remove an absent element.
3. A Set cannot be populated with a List, because a List allows duplicate elements.
4. Add and Remove methods will return false values when attempting to add a duplicate or remove an absent element.



# Examine the following code:
Ans: 1, 2

```
String[] arr = {"Tea","Cake"};
List<String> texts = Arrays.asList(arr);
```

Which statements are true? (Choose two)

1. You can add a new String to texts.
2. You can replace Tea with Coffee in texts.
3. You can replace Tea with Coffee in arr.
4. You can add a new String to arr.


