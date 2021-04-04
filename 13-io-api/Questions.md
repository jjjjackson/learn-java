# Which statement is NOT true about java.nio.file classes?
Ans: 3
1. Class Path represents files and folders.
2. Class Path allows you to discover file system roots.
3. Class Path allows you to set file system object properties.
4. Class FileSystem describes available file systems and their properties.


# Assume that a file system has an empty folder /users/joe and the following code:
Ans: 3
```
Path backup = Path.of("/users/joe/backup/docs");
Files.createDirectory(backup);
```

What is the result?

1. It will create backup and docs folders.
2. It will not do anything.
3. It will throw an exception.
4. It will create the backup folder only.


# Which statement is NOT true about serialization?
Ans: 1
1. Serialization is a suitable solution for long-term data storage.
2. Data is serialized in binary form.
3. Deserialization is a process of reading objects from the stream.
4. Serialization is a process of writing objects from memory into a stream.


# Which is NOT a basis for categorizing Java I/O Streams?
Ans: 3
1. The type of data that stream can carry, for example: text or binary
2. Direction of the stream: input or output
3. Whether it is serial or parallel
4. Type of source or destination to which this stream is connected

