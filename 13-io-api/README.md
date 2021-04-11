# 0z1 819 Lesson 13 I/O

# read write to files
有好幾個 Read Write 的 API　`FileInputStream` `PipeInputStream` （直接看 Website 比較快）

存 Binary 型態的時候，不要當成 LongStorage 不然 Class 改了就 G 了

# Serializable Object
- serialize 會 deep copy 東西除非 `transient variable`
  - `Ex private transient String aaa = ....`
- `BigDecimal` 之類的都有 Serializable Function
  - 如果想 Serialize Class
  - `public class Product implement Serializable`
- Serialization 可以把 Data 寫在系統外
  - **所以 Sensitive Data 需要 Masking
- 如果設 writeObject 和 readObject 為 Private 依然可以被 Serializable Access
  - 不懂？
- Serialization 可以設定 Version (一定要是 Long)
  - `private static final long serialVersionUID = 1L;`
  - 不然舊的 Object 的 Data 可能會丟失

# File System 
- java.nio.file (舊的是 java.io.file)
  - `fs = fileSystem.getDefault()`
  - `fs.getSeparator()` => 可以看是什麼OS 自動看是 "\" or "/"
- `Files.createSymbolicLink` => 類似 `ln -s`
- Files.list
- Files.walk
- Files.isSameFile
  - 比較是否指向同一個 File
  - `c://Document/..../xxx.java` and `../../../xxx.java`
- Files.getAttributeView(path,....).setGroup(...)
- Files.Create, Files.Move, Files.Copy

###### Reference
- [Files.walk](https://geek-docs.com/java/java-tutorial/fileswalk.html)

# HTTP Resource
在 `java.net.http` 的 package 裡面

還有更高階的 API 這裡不講 
