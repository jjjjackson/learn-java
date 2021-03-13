# Which is not a Java primitive type?
Ans: 1
1. String  ➡️ 他是 Class
2. long
3. boolean
4. short
5. float


# Which is the correct way to declare and initialize a variable?
Ans: 2
1. age = 42;            ➡️ 沒有宣告
2. boolean x = 4>5;      
3. int age;             ➡️ 沒有 Initial value // 但其實 compile 的過
4. char midInit = "D";  ➡️ Char 是 Single Quotation


# Examine the following code:
Ans: 3

```
int x = 1, y = 1, z = 0; 
if (x == y | x < ++y) { 
    // 因為 True | True => 是 Or 的 Operator
    // 所以可以進
	z = x+y; 
}
else{
	z = 1;
}
System.out.println(z);
```

What would be printed?
1. 2
2. 1
3. 3


# Why would you use the ternary operator ?: instead of writing an if/else construct?
Ans: 3
1. When the boolean expression is not to be evaluated
2. None of the options listed
3. If you only need to assign a value based on a condition
4. If you run out of room
5. To change the value returned


# What is the correct evaluation of this expression: 8*8/2+2-3*2?
Ans: 4
1. 30
2. 26
3. 10
4. 28
