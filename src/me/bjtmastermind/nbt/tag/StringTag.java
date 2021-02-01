package me.bjtmastermind.nbt.tag;

public class StringTag extends Tag<String> implements Comparable<StringTag> {

	public static final byte ID = 8;
	public static final String ZERO_VALUE = "";

	public StringTag() {
		super(ZERO_VALUE);
	}

	public StringTag(String value) {
		super(value);
	}

	@Override
	public byte getID() {
		return ID;
	}

	@Override
	public String getValue() {
		return super.getValue();
	}

	@Override
	public void setValue(String value) {
		super.setValue(value);
	}
	
	@Override
	public boolean equals(Object other) {
		return super.equals(other) && getValue().equals(((StringTag) other).getValue());
	}

	@Override
	public int compareTo(StringTag o) {
		return getValue().compareTo(o.getValue());
	}

	@Override
	public StringTag clone() {
		return new StringTag(getValue());
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
