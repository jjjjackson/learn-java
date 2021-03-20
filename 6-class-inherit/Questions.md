# Which statements are true? (Choose two)
Ans: 2, 4
1. Class Object constructor may not be explicitly invoked.
2. The Object class is the ultimate parent of any other class in Java.
3. The "extends Object" clause is implied even when an explicit extends clause is present.
4. The Object class defines common, generic operations that all other Java classes inherit and reuse.


# Which is NOT a rule of reference type casting?
Ans: 1
1. Casting is possible between objects of sibling types.
2. Casting is required when invoking a polymorphic operation.
3. No casting is required to assign a child to parent reference type.
4. Casting is required to assign a parent to child reference type.


# Which statements are true about the abstract keyword? (Choose two)
Ans: 1, 2
1. Concrete subclasses must override all abstract methods of their abstract parent.
2. The Abstract class cannot be directly instantiated.
3. Abstract classes make polymorphism impossible.
4. The Abstract class must be extended by one or more concrete subclasses.
5. The Abstract class can contain only final variables and methods.



# Assume that Food is a subclass of Product and examine the following code:
Ans: 3

```
public void order(Product product) {
	if ( /* condition */ ) {
		LocalDate bestBefore = ((Food)product).getBestBefore();
	}
}
```

Which IF condition should be used to verify the object type before casting the reference?

1. (Product instanceof food)
2. (product.equals(Food))
3. (product instanceof Food)
4. (food instanceof Product)
5. (Food instanceof product)
6. (product.instanceOf(Food))


# Which are public methods of the Object class? (Choose three)
Ans: 1, 2, 3
1. wait()
2. toString()
3. hashCode()
4. clone()


