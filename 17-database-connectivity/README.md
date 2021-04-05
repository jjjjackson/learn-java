# 0z1 819 Lesson 17 Database Connectivity
## Java Database Connectivity (JDBC)
- Provided by JDBC API
- 不是給特定的 Database
- JDBC 有 API，會把 Code 轉好再給 Database
- 所以還要選什麼 Driver 和什麼 Connection
- Application Logic -> JPA --> JDBC API -> JDBC Driver -> Database

```java
// jdbc:<provider>:<driver type>:<Connection details>
jdbc:oracle:thin:@localhost:3356:database_name // 之類的
```

### API Structure 
- Driver Manager  👉  Manage Connection 的 Object
  - 可以 Create Statement
    - Statement
      - Connection
      - PrepareStatement
- Close DB 很重要
  - 如果手動 Close 的話，順序很重要
    1. Result
    2. Statement
    3. Database
  - 不然不會有 Exception 但會佔有 Cache 和 Memory 
    - 因為Database可能不會清掉 Result 的 Cache

```java
String url = "jdbc:derby:localhost:1527:productDB";
String user = "pm"
String password = "welcome1";

// Create Connection 
Connection connection = DriverManager.getConnection(url, username, password);

// Basic Statement
Statement statement = connection.createStatement();
String productQuery = "select name from product where price > "+price;
boolean isQuery = statement.execute(productQuery) // executeUpdate, executeQuery ... 有好幾種方式
if (isQuery){
	ResultSet results = statement.getResultSet();
} else {
	...
}

// Prepare Statement
// 這個地方用了？而不是Param
String productQuery = "select name from product where price > ?"; 
PreparedStatement findProduct = connection.prepareStatement(productQuery);
findProduct.setObject(1, price, Types.NUMERIC) // 或是 findProduct.setBigDecimal(1,price);
Result results = findProduct.execute(productQuery)


// Callable Statement
String functionCall = "? = { call some_function(?)}"; // 可以 call 一些 function
CallableStatement some = connection.prepareCall(functionCall);
Result results = findProduct.execute(productQuery)

// Query Result
ResultSet results = findProduct.executeQuery();
while(results.next()) { ... } // 用 Results.next
```

**然而 Base Statement 的方法是不被推薦的, 有 SQL Injection 的可能，可以用 Prepare Statement**


## Transaction
- 必須要 Close connection，不然 DB 會以為要 Commit
- 這個要自己唸。。。
```java
try(Connection connection = DriverManager.getConnection(...)) {
    ...
} catch () {
    connection.rollback();
}
```

## Metadata
- DB 資訊 和 Column info
```java
DatabaseMetaData dbMeta = connection.getMetaData()
String  dbProductName = dbMeta.getDatabaseProductName()

var rsMeta = resultSet.getMetaData()
for (int i = 1 ; i <= rsMetaData.getColumnCount() l i++ ) {
    var name =rsMeta.getColumnName(i);
}
```

## ResultSet
不懂
- ResetSetType
  - set traversal
  - 
- ResultSetConcurrency




