package me.bjtmastermind.nbt.tag;

import java.lang.reflect.Array;

/**
 * ArrayTag is an abstract representation of any NBT array tag.
 * For implementations see {@link ByteArrayTag}, {@link IntArrayTag}, {@link LongArrayTag}.
 * @param <T> The array type.
 * */
public abstract class ArrayTag<T> extends Tag<T> {

	public ArrayTag(T value) {
		super(value);
		if (!value.getClass().isArray()) {
			throw new UnsupportedOperationException("type of array tag must be an array");
		}
	}

	public int length() {
		return Array.getLength(getValue());
	}

	@Override
	public T getValue() {
		return super.getValue();
	}

	@Override
	public void setValue(T value) {
		super.setValue(value);
	}

	@Override
	public byte valueToByte(int index) {
		String s = this.valueToString(index);
		return Byte.valueOf(s);
	}
	
	@Override
	public short valueToShort(int index) {
		String s = this.valueToString(index);
		return Short.valueOf(s);
	}
	
	@Override
	public int valueToInt(int index) {
		String s = this.valueToString(index);
		return Integer.valueOf(s);
	}
	
	@Override
	public long valueToLong(int index) {
		String s = this.valueToString(index);
		return Long.valueOf(s);
	}
	
	@Override
	public float valueToFloat(int index) {
		String s = this.valueToString(index);
		return Float.valueOf(s);
	}
	
	@Override
	public double valueToDouble(int index) {
		String s = this.valueToString(index);
		return Double.valueOf(s);
	}
	
	@Override
	public byte[] valueToByteArray() {
		return (byte[]) this.getValue();
	}
	
	@Override
	public String valueToString(int maxDepth) {
		return getValue().toString();
	}
	
	@Override
	public int[] valueToIntArray() {
		return (int[]) this.getValue();
	}
	
	@Override
	public long[] valueToLongArray() {
		return (long[]) this.getValue();
	}

	protected String arrayToString(String prefix, String suffix) {
		StringBuilder sb = new StringBuilder("[").append(prefix).append("".equals(prefix) ? "" : ";");
		for (int i = 0; i < length(); i++) {
			sb.append(i == 0 ? "" : ",").append(Array.get(getValue(), i)).append(suffix);
		}
		sb.append("]");
		return sb.toString();
	}
}
