# 16 - Annotations

## 什麼事 Annotation
- From Metadata
- 提供 Programme 的資訊
  - SOURCE 
  - CLASS
  - RUNTIME
- Different type of target：ANNOTATION_TYPE，FIELD...等等
- Annotation 會幫助你 Validate Design
  - Ex @override 就能幫助你看看是否已經有 Implement 什麼，沒有就報錯
- 

## 動態 discover Annotation
```java
Shop.class.getAnnotations()
Class annotationType = Shop.class.getAnnotations()[0].getAnnotationType()
```

## @Document
method 是不會直接被 Generate Annotation 的
除非在上面加 @Documented
(直接在網路上找 Example 比較快)
[https://qiita.com/opengl-8080/items/1cc996d9e8bb5c811567](https://qiita.com/opengl-8080/items/1cc996d9e8bb5c811567)


# Deprecated Annotation
- 給一個警告
- 例如說下個版本會被 Removed，就會用這個
  - `＠Deprecated(since="11", forRemoval = true)`


## SuppressWarnings
ignore warning
例如 提供的 method 的 return Type 不正確，需要在自己的 Code 裡面修正
```java
@SuppressWarnings({"unchecked", "deprecation"})
```

## SafeVaragrs
想要 Restrict Type 的時候
