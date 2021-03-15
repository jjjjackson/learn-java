

# When breaking out of loops and skipping loop cycles, ____ skips the current loop and ____ terminates the current loop.
Ans: 4
1. return, break
2. break, continue
3. continue, return
4. continue, break


# Which statements are true about an array? (Choose four)
Ans: 1,2,3,5
1. An array of primitive values is filled with 0 values (false values if it is of boolean type).
2. Is an object itself
3. It is a fixed-length collection of elements of the same type indexed by int.
4. You must "extract" the array element from the array before you operate on it.
5. After an array is created, its length cannot be changed.


# Examine the following code:
Ans: 2
```
String[] names = {"Mary","Jane","Ann","Tom"}; 
Arrays.sort(names);
int x = Arrays.binarySearch(names,"Ann");
System.out.println(x);
```

What is the value of x?
1. 4
2. 0
3. 2
4. 3
5. 1


# Examine the following code:
Ans: 3
```
public class Compare implements Comparator<String>{
  public int compare(String s1, String s2) {
    return s2.length() - s1.length();
  }
}

String[] names = {"Mary","Jane","Elizabeth","Jo"};
Arrays.sort(names, new Compare());
for (String name: names) {
  System.out.println(name);
}
```

What will be the output?
1. Jo Mary Jane Elizabeth
2. Mary Jane Elizabeth Jo
3. Elizabeth Mary Jane Jo
4. Elizabeth Jane Mary Jo
5. Jo Jane Mary Elizabeth


# When processing an array in a loop, use array ____ to determine the boundary for the termination condition:
Ans: 5
1. length()
2. size
3. capacity
4. size()
5. length
