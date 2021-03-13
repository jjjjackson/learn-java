# 0z1 819 Lesson 2 Primitive Types

## 筆記

# Types
基礎的
- byte, short, int, long(64bit)    👉 default 0
- float, double(64bit)             👉 default 0.0
- char                             👉 default \u0000 👉 必須是 Single quotation (考題)
- boolean                          👉 default false  👉 case sensitive 所以 `False` 的話會報錯

float 後面要加 F (考題)
```
float e = 1.99E2F
float e = 1.99E2    ❌  compile 不會過
```


# Operation 
Java 有 `++` 和 `--`
`+=` `-=` `*=` `/=` `%=` 也有

Java 是 type sensitive
```
byte a = 127, b = 5； 
float c = a / b             👉 會過，但不推
float c = (float)a / b      ✅
float c = a / (float) b     ✅
float c = (float)(a / b)    👉 會過，但不推
```

## Binary Operation 
- &         👉 and 
- |         👉 or 
- ^         👉 xor 
- \<<       👉 右邊補位
- \>>       👉 右邊補位

```
byte a = 5; // 0000101 
byte b = 3; // 0000011
c = a & b;  // 0000[001] 👉 1
c = a | b;  // 0000[111] 👉 7
c = a ^ b;  // 0000[110] 👉 6
c = a << 2  // 00101[00]
c = a >> 2  // [00]00101
c = a << 2  // 00101[00]
```

### not
int a = 2;
int b = ~a; // -3 
=> 因為
```
 -3
 -2
 -1
 0
 1
 2      👉 -3
```


# Math.round
四捨五入
```
// 記得 Round 是不會幫你直接轉 Double, 即使你自己設 double
int e = 11, f = 3;
double g = Math.round(e / f); // Output 3 but Expect 4

// 取得小數點以下兩位數
double a = 1111.11;
int b = 3;
double d = Math.round(a / b * 100) / 100.0;

// 用 BigDecimal 寫 （Double 不行 🙅‍♂️）
BigDecimal doubleD = BigDecimal.valueOf(a / b);
doubleD = doubleD.setScale(2, RoundingMode.HALF_UP); // 取得小數點以下兩位數
```