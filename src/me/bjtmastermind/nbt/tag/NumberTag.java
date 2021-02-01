package me.bjtmastermind.nbt.tag;

public abstract class NumberTag<T extends Number & Comparable<T>> extends Tag<T> {

	public NumberTag(T value) {
		super(value);
	}

	public byte asByte() {
		return getValue().byteValue();
	}

	public short asShort() {
		return getValue().shortValue();
	}

	public int asInt() {
		return getValue().intValue();
	}

	public long asLong() {
		return getValue().longValue();
	}

	public float asFloat() {
		return getValue().floatValue();
	}

	public double asDouble() {
		return getValue().doubleValue();
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
	public String valueToString(int maxDepth) {
		return getValue().toString();
	}
}
