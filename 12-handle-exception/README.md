# 0z1 819 Lesson 12 Handling Exception and Fix Bugs


# Logging 
```java
Logger.log(Level.Error/Info... , "message");
// 有 log, logb, logp, server, warning, info 等等
```

logger.setLevel(Level.Info); // 則比 Info 不重要的都不會 Log
logger.properties // 設定 Log 在哪個 File


# Exception
- Checked Exception       👉 在 Compiling 的時候會 Catch 到
- Unchecked Exception     👉 在 Runtime 的時候會 Catch 到


# Create Custom Exceptions
- 一定要 Extend Exception 或其他的 Throwable
- 可以只的 Error Message 也可以丟 包裝其他的Exception
  - 不想讓外面看到實作時

```java
public class ProductException extends Exception {
    public ProductException() {
        super();
    }
    public ProductException(String message) {
        super(message);
    }
    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

# Throw Exception 
```java
throw new Exception()
```

# Catch Exception 
一定要 Catch 或在 function 上寫 close Exception

```java
public void a() throws IOException { ... }
```

正常
```java
try {
    ...
} catch (Exception e) {
    ...
} finally {
    // 可以繼續 Try
    // 無論有沒有 Exception 都會發生
    // 即使 Catch 裡面有 return 也會到 Final ⭐⭐⭐⭐⭐
}
```

## addSuppressed()
如果 Final 裡面有 Try 上一個的Exception 會被抑制，所以用 `addSuppressed()` 來抑制 Exception 被抑制

```java
IOException readException = null;

try {
    ...
} catch ( ) {
    ...
} finally {
    try {
        ...
    } catch(IOException ex2) {
        if(readException != null){
            readException.addSuppress(ex2) // 這個可以拿到上一個 Exception 的內容
        } else {
            readException = ex;
        }

    })
}

```


## Resource auto closure
```java
try ( **這裡有 IO 的話** ) {
    ...
}catch (...) {
    ...
} // finally{} 會自動纏上
```



# Assertion
做 Testing 的東西，但非常 Basic， 高級需要用 Junit



