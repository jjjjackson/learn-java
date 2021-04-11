# 0z1 819 Lesson 14 Concurrency and MultiThread

# MutliThread
- 需要 `run` method 和 `Runnable` interface
- Java 是跑 Thread，讓不同段的 Thread 跑在 不同的Processor 上
- 可以直接 Extend Thread class，但是不 Flexible
- Runnable interface 有 Concrete Method, 所以不需要 impl run

```java
public class A implements Runnable {
    public void run () { }
}
A a = new A;
new Thread(a).start();
```

# Thread Life Cycle
- 所有的 State 都得從 Runnable 出發回 runnable （出了New）
- runnable ➡️ waiting ➡️ block ❌ runnable ➡️ block
-                  ↘️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️↗️

<img src="./images/lifecycle.png" alt="lifecycle"/>

```java
t1.static()
ta.static() --> 不能進入 runnable 兩次
```

# Interrupt Thread
- `run` 是決定 `LifeCycle` 怎麼走的地方
- Runnable state 可以看到有沒有 `Interrupt signal`
- Waiting 和 TimeWaiting **一定要** Catch InterruptException

```java
Runnable r = () -> {
    Thread ct = Thread.currentThread(); 
    while(!ct.isInterrupted()) { // check interrupt signal
        // Tread actions
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            // 決定要幹嘛
            return;
        }
    }
}
Thread t = new Thread(r);
t.start();
t.interrupt(); // 會丟 interruption 給不然 sleep 都不會停
```

# Block Thread
- keyword `synchronized`

```java
public class Some {
    public synchronized void a() {}
    public static synchronized void b() {}
    public void c () {}
}
Runnable r = () {
    synchronized(s) {
        s.c;
    }
}
```

# Wait Until Notified
- Method `notify` 和 `notifyAll` 可以讓 waiting 的 thread 醒來
- ru
```java
Object obj = new Object();
Runnable r = () -> {
    try {
        synchronized(obj){
            obj.ait();
        } catch (InterruptedException ex) { ... }
    }
};

Thread t = new Thread(r);
t.start();
try {
    Thread.sleep(1000);
} catch (InterruptedException ex) {...}
synchronized (obj) {
    obj.notify(); //  給 waiting 的 thread/threads interruption
}
```

# Common thread Property
- customize name 
- priority
- Unique id
- DaemonThread / UserThread
  - DaemonThread（ 如果其他 UserThread 都結束了，DaemonThread 也會結束，JVM 也會結束
  - UserThread (如果 UserThread 還在跑則 JVM 不會 Close)
- t.join(); // 等別的 Thread 完成，為了 Order
  
# java.util.concurrent.executor
**這個地方應該是最重要的, 但可能補不考** 
- Java 有非常優秀但 Thread Pool
- 為什麼要用 Thread Pool？
  - 可以減少 Create 和 Destroy Thread 的次數
  - 避免創建過多的 Thread，消耗 Memory (控制 Memory 在 1MB )

- 有提供幾個 thread manager 的 Object 
  - Executor Service

https://www.cnblogs.com/vhua/p/5277694.html
https://blog.csdn.net/TreeShu321/article/details/90735181


# Locking Problem
<img src="./images/lock-problem.png" alt="multi-thread lock problem"/>


## Solution 
1. Thread-Safe
2. Atomic Action
3. Intrinsic Lock Automation
4. Concurrency Automation
5. Alternative Locking (Read, Write lock)

#### Write Thread-Safe Code
1. 所有的 Thread 只有自己的 Stack  
2. 在 Heap 的 Object 是 immutable 
   1. 讓 Object 都是 Immutable， 然後只用 Copy Object
3. Volatile
   1. 不會 Catch value
   2. 永遠都看 Memory 裡的值
   3. 會先改變自己 thread 裡面的值，再去 Update Memory 裡面的值

```java
public class Shared {
    public int x;
    public volatile int y; 

Shared s = new Shared();
new Thread(() -> {
    while(s.y < 1 ) {
        int x = s.x;
    }
}).start();

new Thread(() -> {
    s.x = 2; 
    s.y = 2;// 沒有 Volatile 的話，y 看起來是用同一個 Data in Heap
            // 但其實他會 Cache 一份 y 到 thread
            // 所以改的可能不是同一個 y
            // 所以用 volatile 避免這個情況
}
}).start();
```


#### Atomic Action 
- Java 的 + - * / % 都不是 Atomic的
- 要換成 AtomicBoolean， AtomicInteger， ...AtomicReference<V> 

#### Intrinsic Lock Automation
- 有些 API 會提供 Synchronized 版的 Object
  - 例如 Collections 的 Collection, List ,Set, Map

```java
List<String> list = new Array(List<>());
List<String> sList = Collections.synchronizedList(list);
Runnable r = () -> {
    String name = Thread.currentThread().getName();
    for( int i = 0 ; i < 10 ; i++ ) {
        sList.add(name + ' ' + i);
    }
}

synchronized(sList) {
    Iterator i = sList.iterator();
    while(i.hasNexT()) {
        System.out.print(i.next());
    }
}
```

#### Concurrency Automation
- `java.util.concurrent`
  
```java
List<String> list = new ArrayList<>();
List<String> copyOnWriteList = new CopyOnWriteArrayList<>(list); // 這個
Runnable r = () -> {
    String name = Thread.currentThread().getName();
    for (int i = 0; i < 10; i++) {
    copyOnWriteList.add(name + ' ' + i);
    }
};

Iterator i = copyOnWriteList.iterator();
while( i.hasNext()){
    System.out.println(i.next());
}

```

#### Alternative Locking Mechanism
- `java.util.concurrent.locks`
- 用 ReadLock 和 WriteLock 來限制
- WriteLock 的時候 Read 也會被 Pause

```java

class ProductList {
  private List<Product> menu  = new ArrayList<>();
  private ReentrantReadWriteLock rw1 = new ReentrantReadWriteLock();
  private Lock r1 = rw1.readLock();
  private Lock w1 = rw1.writeLock();

  public  Product get(String name) {
    r1.lock();
    try {
      return menu.stream().findFirst(p -> p.name == name);
    }finally {
      r1.unlock(); // 一定要 Unlock
    }
  }
  public void add(Product product){
    w1.lock();
    try { 
      return menu.add(product);
    } finally {
      w1.unlock(); // 一定要 Unlock
    }
  }
}
```


# Reference
[Gorutine 和 JAVA thread 的比較](https://www.zeng.dev/post/2019-java2go-concurrency/)
