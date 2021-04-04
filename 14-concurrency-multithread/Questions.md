# Which are valid mechanisms to implement threads? (Choose two)
Ans: 1, 4 (???)
1. Instantiate a Runnable object.
2. Create a class to extend Runnable.
3. Create a class that implements Thread.
4. Schedule a thread to run.


# When you catch the InterruptedException, into which state does it put the thread?
Ans: 2 (???)
1. NEW
2. RUNNABLE
3. TERMINATED
4. INTERRUPTED


# What is the state of two threads waiting on each other in a synchronized block called?
Ans: 1
1. Deadlock
2. ReadWriteLock
3. Starvation
4. Livelock


# Which are valid thread state transitions? (Choose two)
Ans: 1, 2 (所有的 Status 都需要從 Runable 開始，除了 New)
1. NEW to RUNNABLE
2. RUNNABLE to BLOCKED
3. BLOCKED to TERMINATED
4. WAITING to TERMINATED
5. TERMINATED to RUNNABLE


# Which statement is true about threads?
Ans: 2
1. A high-priority thread will complete its task earlier than a low-priority thread.
2. The order in which threads terminate is indeterminate.
3. You can always force a thread to terminate.
4. Two concurrent threads can enter the same synchronized block simultaneously.

