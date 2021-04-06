# 0z1 819 Lesson 20 cloud deployment

- Java EE Server (Web Logic)
  - 有 EJB 和 Web Container
  - 有Security, Concurrency, TransactionManagement
  - Serverlet + Rest + POJO +EJB + JCA + JMS 等等
  - 做 Web Server
- Java SE (Helidon)
  - 有些東西需要需要自己寫
  - REST+ POJO
  - Host Micro service applications
  - 做電腦上的執行軟體
- JAVA MP
  - subset of other enviormnent
  - Host Micro service applications
  - Light weight application container

POJO 👉 

### Deploy EE
- server 不會 Shutdown
- Application 直接 Deploy 到 Server
- 好幾個 Application 會共用一個 JVM 和 Java EE Server
```
【 JVM 
    【 server 
        【application】【application】
    】
】
```
### Deploy SE
- 會 Package Java Archives 
- 用 JLink 包成 JImage
- Server 會用 application code 啟動
- 每個 Application 有獨立的 JVM
```
【 JImage
    【 JVM 
        【 jar
            【server】
        】
    】
】
```
***可以用 Maven Deploy （一個OpenSource 的Tool***


## Routing
```java
Handler productLookup = (ServerRequest req, ServerResponse res) -> {
    ...
}
Routing routing = routing.builder
    .get("/product{id}", productLookup)
    .post(...)
ServerConfiguration config = ServerConfiguration.builder()
    .bindAddress(InetAddress.getLocalhost())
    .port(8080).build();
WebServer web = WebServer.create(config,config);
wb.start();
```
