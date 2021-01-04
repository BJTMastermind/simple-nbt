# Simple NBT
#### A java implementation of the [NBT protocol](http://minecraft.gamepedia.com/NBT_format) for Minecraft Java Edition with support for Minecraft (Bedrock Edition).
---
### Specification
According to the [specification](https://minecraft.gamepedia.com/NBT_format), there are currently 13 different types of tags:

| Tag class    | Superclass | ID | Payload |
| ---------    | ---------- | -- | ----------- |
| [EndTag](src/main/java/net/querz/nbt/EndTag.java)             | [Tag](src/main/java/net/querz/nbt/Tag.java)               | 0  | None |
| [ByteTag](src/main/java/net/querz/nbt/ByteTag.java)           | [NumberTag](src/main/java/net/querz/nbt/NumberTag.java)   | 1  | 1 byte / 8 bits, signed |
| [ShortTag](src/main/java/net/querz/nbt/ShortTag.java)         | [NumberTag](src/main/java/net/querz/nbt/NumberTag.java)   | 2  | 2 bytes / 16 bits, signed, big endian |
| [IntTag](src/main/java/net/querz/nbt/IntTag.java)             | [NumberTag](src/main/java/net/querz/nbt/NumberTag.java)   | 3  | 4 bytes / 32 bits, signed, big endian |
| [LongTag](src/main/java/net/querz/nbt/LongTag.java)           | [NumberTag](src/main/java/net/querz/nbt/NumberTag.java)   | 4  | 8 bytes / 64 bits, signed, big endian |
| [FloatTag](src/main/java/net/querz/nbt/FloatTag.java)         | [NumberTag](src/main/java/net/querz/nbt/NumberTag.java)   | 5  | 4 bytes / 32 bits, signed, big endian, IEEE 754-2008, binary32 |
| [DoubleTag](src/main/java/net/querz/nbt/DoubleTag.java)       | [NumberTag](src/main/java/net/querz/nbt/NumberTag.java)   | 6  | 8 bytes / 64 bits, signed, big endian, IEEE 754-2008, binary64 |
| [ByteArrayTag](src/main/java/net/querz/nbt/ByteArrayTag.java) | [ArrayTag](src/main/java/net/querz/nbt/ArrayTag.java)     | 7  | `IntTag` payload *size*, then *size* `ByteTag` payloads |
| [StringTag](src/main/java/net/querz/nbt/StringTag.java)       | [Tag](src/main/java/net/querz/nbt/Tag.java)               | 8  | `ShortTag` payload *length*, then a UTF-8 string with size *length* |
| [ListTag](src/main/java/net/querz/nbt/ListTag.java)           | [Tag](src/main/java/net/querz/nbt/Tag.java)               | 9  | `ByteTag` payload *tagId*, then `IntTag` payload *size*, then *size* tags' payloads, all of type *tagId* |
| [CompoundTag](src/main/java/net/querz/nbt/CompoundTag.java)   | [Tag](src/main/java/net/querz/nbt/Tag.java)               | 10 | Fully formed tags, followed by an `EndTag` |
| [IntArrayTag](src/main/java/net/querz/nbt/IntArrayTag.java)   | [ArrayTag](src/main/java/net/querz/nbt/ArrayTag.java)     | 11 | `IntTag` payload *size*, then *size* `IntTag` payloads |
| [LongArrayTag](src/main/java/net/querz/nbt/LongArrayTag.java) | [ArrayTag](src/main/java/net/querz/nbt/ArrayTag.java)     | 12 | `IntTag` payload *size*, then *size* `LongTag` payloads |

* The `EndTag` is only used to mark the end of a `CompoundTag` in its serialized state or an empty `ListTag`.

* The maximum depth of the NBT structure is 512. If the depth exceeds this restriction during serialization, deserialization or String conversion, a `MaxDepthReachedException` is thrown. This usually happens when a circular reference exists in the NBT structure. The NBT specification does not allow circular references, as there is no tag to represent this.

### To Do
- [x] Adjust how ListTag works
- [x] Support starting a nbt file with a ListTag
- [x] Add Support for Reading/Writing Bedrock Edition NBT files
- [ ] If possible add Support to name CompoundTag's and ListTag's when created

### Example usage:
The following code snippet shows how to create a `CompoundTag`:
```java
CompoundTag ct = new CompoundTag();

ct.put("byte", (byte) 1);
ct.put("double", 1.234);
ct.put("string", "stringValue");
```
An example how to use a `ListTag`:
```java
ListTag<FloatTag> fl = new ListTag<FloatTag>();

fl.add(5.678f);
```

#### Nesting
All methods serializing instances or deserializing data track the nesting levels to prevent circular references or malicious data which could, when deserialized, result in thousands of instances causing a denial of service.

These methods have a parameter for the maximum nesting depth they are allowed to traverse. A value of `0` means that only the object itself, but no nested object may be processed.

If an instance is nested further than allowed, a [MaxDepthReachedException](src/main/java/net/querz/nbt/MaxDepthReachedException.java) will be thrown. A negative maximum depth will cause an `IllegalArgumentException`.

Some methods do not provide a parameter to specify the maximum depth, but instead use `Tag.DEFAULT_MAX_DEPTH` (`512`) which is also the maximum used in Minecraft.

---
### Utility
There are several utility methods to make your life easier if you use this library.
#### NBTUtil
`NBTUtil.write()` lets you write a Tag into a gzip compressed or uncompressed file in one line (not counting exception handling). Files are gzip compressed by default.

Example usage:
```java
/*Big Endian (default):*/ NBTUtil.write(namedTag, "filename.dat");
/*Little Endian:*/ NBTUtil.writeLE(namedTag, "filename.dat");
```
`NBTUtil.read()` reads any file containing NBT data. No worry about compression, it will automatically uncompress gzip compressed files.

Example usage:
```java
/*Big Endian (default):*/ NamedTag namedTag = NBTUtil.read("filename.dat");
/*Little Endian:*/ NamedTag namedTag = NBTUtil.readLE("filename.dat");
```
#### Playing Minecraft?
Each tag can be converted into an NBT String (SNBT) used in Minecraft commands.

Example usage:
```java
CompoundTag c = new CompoundTag();
c.put("blah", (byte) 5);
c.put("foo", "bar");
ListTag<StringTag> s = new ListTag<StringTag>();
s.add("test");
c.add("list", s);
System.out.println(SNBTUtil.toSNBT(c)); 
// Output: {blah:5b,foo:"bar",list:[test,text]}
```
