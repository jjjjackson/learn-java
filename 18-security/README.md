# 0z1 819 Lesson 18 Security

## DoS attacks
- 症狀
  - 一直拿 Resource 讓別的 User 拿不到 Resource
  - 有可能是佔用網路或是 file 讀取 等等
- prevent
  - limit 程式可以使用的 Resource
  - 可以儘快的 Release Resource
  - 盡量 Detect 奇怪的 Access 或是 過多的動作
  - Define Security Policy
    - 有個 java.policy 和 java.security 
    - 需要自己查
  - java.security.Permission
    - 可以 `AccessController.checkPermission` 
    - `AccessController.doPrivileged`

## Secure FileSystem and IO Operations
- Dictionary Traversal 
  - 用可能用 `../../xxxx` 去找到什麼位置
  - 用 `normalize` 和 `toRealPath`
- DoS Attack
  - Verify file size 和 Stream size
  - 檢測 IO
  - 盡快 Release Resource
  - Time out
- Deserialize
  - Deserialize un-trusted data 很危險
  - 需要先 Verify Value


## Best Practice
- tight encapsulation
  - 盡量用 Module 去保護 class 的 reflection
  - 盡量 restrict permission
- 讓 Object Immutable
- Design Class 的時候要考慮，這個東西被繼承之後是否給 Subclass 使用 或 Declare 成 Final 或 Private
  - Non Final 或 Non Private 可能被 Hacker Override
  - 用 factory method 做 Constructor 的 Validation 
  - 不要在 Constructor invoke Overridable Method
- Protect Byte Code 
  - 有些環境可以更改 Java 的 ByteCode
  - 注意 command line argus `-Xverify:none` 或 `-noverify` 這些會 Disable Byte-code verification


## Erroneous Value Guard
- Guard overflow number space
  - `java.lang.AritheticException` (不懂)
- Guard floating point
- guard null


## Protect Sensitive Data
用 `javax.crypto` 加密 Value


## SQL Injection 
- 不要用 baseStatement 
- 用 PrepareStatement
- 用 `enquoteLiteral(param)`
- 用 JPA 和 BeanValidation Api 去自動的保護 DB （ 自己看其他的 Course ）


## JavaScript Injection
- 有個 OWASP library 去 sanitize params


## Prevent XML Injection
- Problem
  - Large Entity overwhelm parsing program
  - indefinite lop
  - external URL
  - inclusion sensitive file
- Solution
  - lib OWASP `XMLConstants.FEATURE_SECURE_PROCESSING`
  - 不要用這種不安全的 constructs


## Discover Security Issue
- Test vulnerabilities

