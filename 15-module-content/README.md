# 0z1 819 Lesson 15 Module

# Module
- Java 9 之前沒有 module
- 
- group of package
- 名字要是 Universal Unique
- 需要一個 `module-info.class`
- 權限控制
  - 可以設定 open 什麼 package
- Module 的東西會一起被 Deploy
- 用 Module Path 而不是 class path
  - 更快 👉 因為用 Module 時 compiler 會知道 method 在哪個 Module 和 Module 有沒有被Load，ClassPath 的話需要掃描 Memory 看有沒有被 Load 了
- 如果時跑 Pure Module 的話，所有的東西都必須是 Module， 可以用 jlink 打包成 image

# JPMS (Java Platform Module system)
- 從 Java 9 開始， 所有的 API 都被包成 Module

## Categories
- Java SE Modules
  - Core General-Purpose APIs
  - 都是由 `java` 開頭 `java.base`, `java.se`, `java.logging`
  - `java.sql`, `java.xml`
- JDK Modules
  - additional implementation-specific module
  - 開頭是 `jdk` 的
  - 有的 Module 會沒有 Load 所有的 Package 和 Class 
  - `jdk.httpserver`, `jdk.jconsole`, `jdk.jshell`
  - `jdk.policytool`, `jdk.httpserver`
- Others
  - java --list-modules # 可以看到 Java 所有的 Modules

## command line tool
- jshell
  - 就是 Java 的 Shell Code
- jlink
  - create jdm
- jdeps
  - dependnecy analyzer

# Module Dependencies
```java
module com.some.universal.name {
  requires java.logging;
  requires transitive org.com; // 相關的 modules 都會 load
  requires static come.foo;    // runtime 的時候才載入
  
  requires java.base // 這個是會被自動加載的，不用寫


  exports demo.s;  // 只 open demo.s 這個 package
                   // 但只看得到 public class
                   // 其他 package 裡的 class 即使是 Public 也不會被看到
  exports demo.s to some.module // 只給特定 module 看 （考）

  opens demo.a     // 只 open demo.s 這個 package
                   // 且可以看到 Private class
  opens demo.a to some.module // 只給特定 module 看 （考）
}

open module com.some.universal.name {} // 可以直接 Open 整個 module
```

# module 的 file 結構
```
- A
  - classes
    - module.info.java
    - pkgA
      - ClassA.java
  - tests
  - libraries
  - Test Libraries
- B
  - classes
    - module.info.java
    - pkgB
      - ClassB.java
  - tests
  - libraries
  - Test Libraries
```

直接在 VSCode 就可以 Export jar

```
shift + command + p
export jar
```

# Service Module
- export interfaces
- defined the interface of future service
- 需要 implement 這些接口

<img src="./images/service-module-example.png" alt="service module example"/>

# Multi-Release Module Archives
為了每個版本寫 META-INF
- demos
  - data
    - Product.java
- module-info.java
- META-INF
  - MANIFEST.MF
  - versions
    - 9
      - demos.data
        - demos
          - data
            - Product.java
        - module-info.java
    - 10
      - ...
    - 11
      - ...

# compile package

```sh 
# compile module to classes
javac --module-path paths-to-other-module -d output-folder -sourcepath path-to-code

# package module to JAR
jar --create -f path-to-jar-file --main-class pacakge.main -C path-to-code .

# compile modules
java -d out-put-folder --module-source-path list-of-code-including-module-info
java -d dist --module-source-path src $(find src -name "*.java")

# run modular application
java --module-path <path to compile module> --module <module name>/<package name>.<main class name>
java -p dist -m hello/com.greeting.Main

java -cp dist -m hello/com.greeting.Main # non-module application 時
``` 

# Legacy JAR Files
- 可以將 non-module package 用 `--module-package` 變成 `automatic module`
  - 沒有 module-info.file
  - 可能 module name 不是 Universal name
  - 可以用 MANIFEST.MF define module name


# Questions
which two modules are found in JAva?
Ans: 3,5
1. java.jshell // se
2. jdk.se.ee   // se 和 ee 是不同的Collection
3. java.base 
4. jdk.loging  // java 的
5. java.desktop
