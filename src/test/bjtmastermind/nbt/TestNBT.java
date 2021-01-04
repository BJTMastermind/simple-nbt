package test.bjtmastermind.nbt;

import java.io.IOException;

import me.bjtmastermind.nbt.io.NBTUtil;
import me.bjtmastermind.nbt.io.SNBTUtil;
import me.bjtmastermind.nbt.tag.CompoundTag;
import me.bjtmastermind.nbt.tag.ListTag;

public class TestNBT {

	public static void main(String[] args) throws IOException {
		CompoundTag tag = new CompoundTag();
		tag.put("ByteTest", (byte) 5);
		tag.put("ShortTest", (short) 3);
		tag.put("IntTest", 7);
		tag.put("LongTest", 5.32185);
		tag.put("FloatTest", 2.7f);
		tag.put("DoubleTest", 8.2);
		tag.put("ByteArrayTest", new byte[] {2, 4, 8, 1, 7});
		tag.put("StringTest", "This is a String");
		tag.put("IntArrayTest", new int[] {3, 7, 4, 2, 5});
		tag.put("LongArrayTest", new long[] {(long) 2.43253, (long) 1.42462, (long) 7.15673, (long) 9.567584, (long) 3.151566});
		ListTag<CompoundTag> list = new ListTag<CompoundTag>();
		CompoundTag listt = new CompoundTag();
		listt.put("ByteTest", (byte) 5);
		listt.put("ShortTest", (short) 3);
		listt.put("IntTest", 7);
		listt.put("LongTest", 5.32185);
		listt.put("FloatTest", 2.7f);
		listt.put("DoubleTest", 8.2);
		listt.put("ByteArrayTest", new byte[] {2, 4, 8, 1, 7});
		listt.put("StringTest", "This is a String");
		listt.put("IntArrayTest", new int[] {3, 7, 4, 2, 5});
		listt.put("LongArrayTest", new long[] {(long) 2.43253, (long) 1.42462, (long) 7.15673, (long) 9.567584, (long) 3.151566});
		list.add(listt);
		tag.put("ListTest", list);
		CompoundTag tag2 = new CompoundTag();
		tag2.put("ByteTest", (byte) 5);
		tag2.put("ShortTest", (short) 3);
		tag2.put("IntTest", 7);
		tag2.put("LongTest", 5.32185);
		tag2.put("FloatTest", 2.7f);
		tag2.put("DoubleTest", 8.2);
		tag2.put("ByteArrayTest", new byte[] {2, 4, 8, 1, 7});
		tag2.put("StringTest", "This is a String");
		tag2.put("IntArrayTest", new int[] {3, 7, 4, 2, 5});
		tag2.put("LongArrayTest", new long[] {(long) 2.43253, (long) 1.42462, (long) 7.15673, (long) 9.567584, (long) 3.151566});
		tag.put("CompoundTest", tag2);
		NBTUtil.write(tag, "/home/bjtmastermind/Desktop/test.dat");
		System.out.println(tag + "\n");
		System.out.println(SNBTUtil.toSNBT(tag));
	}
}
