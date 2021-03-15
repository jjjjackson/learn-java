# 0z1 819 Lesson 3 Text Date Time Numeric and Localization

## 筆記

# String
- String represent a sequence of char ➡️ 考
- String 不是 Primitive ➡️ 考
- Double Quotation ➡️ 考 
- String 是 Immutable ➡️ 考 
- 所有的 Method 都會 Return New String ➡️ trim, concat, toLowerCase 都是 ➡️ 考

Ex: 
```
String a = "Hello"
```

JVM 會把每個 String 丟到 String Pool 裡面
所以 `var a = "Hello"` 和 `var b = "Hello"` Memory 位置是一樣的
`var d = "He", f = "llo";` 然後  `var g = d+f`  Memory 位置是一樣的

## IndexOf
```
var a = "HelloWorld"
a.indexOf('o')          👉 4 
a.indexOf('o',5)        👉 6
a.indexOf('a')          👉 -1
```


# StringBuilder
- Mutable               👉 在需要減少 StringObject 的時候使用 Ex 一連串的 String Operate
- 可以 Append insert delete reverse

BTW StringBuilder.capability 的 allocation 很神奇，可研究一下


# Wrapper for Primitive
就是 int 之類的 class 包裝
byte ➡️ Byte, int ➡️ `Integer` (考)
還有一個 BigDecimal

```java
var e = Integer.valueOf(123) // int 或 string
```

可以 `auto-unboxing` 和 `auto-boxing`
```java
List<Double> ld = new ArrayList<>();
ld.add(3.1416);
double pi = ld.get(0);
```

# LocalDatTime
- 有 LocalDate LocalTime LocalDateTime
- 有 getXXX ofXXX plusXXX minusXXX 的 method
- 都是 Timestamp
- String ➡️ Timestamp 需要 Formatter 或 Instance.parse


# Instance
Instance.now()  👉 現在時間


# Duration Period
- Duration      👉 LocalDate
- Period        👉 LocalDateTime / LocalTime / Instance


# ZoneDateTime
[時區代號](https://qiita.com/niwasawa/items/7ac1ea4c05c15e4b46fc)
```
ZoneId tokyoTimeZone = ZoneId.of("Asia/Tokyo");
ZoneDateTime tokyoTime = ZoneDateTime.of('...', tokyoTimeZone); 配合著用
```

```
ZonedDateTime dtUTC = ZonedDateTime.parse("2019-03-10T02:30:00Z");
ZoneId pacific = ZoneId.of("US/Pacific");
System.out.println(dtUTC.withZoneSameInstant(pacific)); 
System.out.println(dtUTC.withZoneSameLocal(pacific)); 👉 會 Ignore 夏日節約時間
```
```
2019-03-09T18:30-08:00[US/Pacific]
2019-03-10T03:30-07:00[US/Pacific]
```

# Locale
### Formate Number
```
Locale uk = new Local("en","GB","EURO"... ) →　這個有點深，要另外看
var currencyFormatter = NumberFormat.getCurrencyInstance(uk); => formatter 不是只有給 Object 有些 Primitive 也可以用

var priceString = currencyFormatter.format(price); => €1.75
BigDecimal newPrice = (BigDecimal) currencyFormatter.parse("€1.75");
```

### Formate Time
```
String dateFormat = "EE', 'd' , MMMM YYYY, 'HH:mm z";
DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat, locale);
dateTimeFormatter.format(時間); // 記得入如果 dateFormat 有 時間的話就要用 LocalDateTime 如果有時區要ZoneDateTime
```
