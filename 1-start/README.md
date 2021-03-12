# 0z1 819 Lesson 1 Introduction to Java

## 筆記

#### 版本
Java 有
- Java ME
- Java Card
- Java MP    👉 for Micro-Services
- Java EE 

Java 8, 9, 10, 11, 12  的版本區別
https://zhuanlan.zhihu.com/p/78708862

#### Compile
javac compile `*.java`  →　`*.class` BinaryCode for JVM   →　用 java 執行

##### 指令 👨‍💻
compile 🔧
```
-cp -classpath 其他Compile過的東西在哪
-d Compile完存哪
javac -cp all/classes -d all/classes ./the/class/to/compile.java
```

run 🚀
```
java package.name.class param1 "param 2"
```

#### Reserved Words
Goto 和 Constant 已經不能用了，但還在 Reserved Words 裡
https://www.thoughtco.com/reserved-words-in-java-2034200

#### Naming
Java 的 package name + class name 必須要是 Globally Unique
所以很多公司都是以 DomainName 作為 Package Title
但是命名規則還是要看公司
https://blog.csdn.net/kongjiea/article/details/45365753


**Java is case sensitive**

命名範例
- package   →　com.orcal.domain
- class     →　ElectronicPiano
- variable  →　electronicPiano  ❗有的語言會習慣加 `_` 表示 Private 但是不要做這種事
- constant  →　MIN_SIZE
- method    →　playCSharp

**考題**
```
Q: 哪種命名方法Compile不會過
_SystemValue        👉 會過只是不推薦
MyNewValue
✅ 4ScoreAnd8Years
$_exceptionValue    👉 會過只是不推薦
object 
```

#### 縮排不會影響 Compile 只是 Readability
考點
`.java`  只是 Plain Text

#### import 的方法

1. 不 import  直接用 `animal.Dog = myDog;`
2. `import animal.Dog` 然後 `Dog = myDog;`
3. `import animal.*` 然後 `Dog = myDog;`

但不管怎麼樣 compile 後都會變成 `animal.Dog = myDog;` 
所以如果 import 沒有用到的 package，對 Compile 沒有什麼影響

#### Class 的 Params 和 Function Default 是 Private

#### Main Function
Main 是個 EntryPoint
因為 Java 所以的東西都是 Class
Main 能讓 Class 都是 Executable

Void Main(String[] args) => Params 只有 Args

#### 註解

```
// 註解

/*
* 註解
*/
```
