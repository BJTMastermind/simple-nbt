package de.querz.nbt;

import java.io.IOException;

public class IntTag extends Tag {
	private int value;
	
	protected IntTag() {
		this(0);
	}
	
	public IntTag(int value) {
		this("", value);
	}
	
	public IntTag(String name, int value) {
		super(TagType.INT, name);
		setValue(value);
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	protected void serialize(NBTOutputStream nbtOut) throws IOException {
		nbtOut.dos.writeInt(value);
	}

	@Override
	protected IntTag deserialize(NBTInputStream nbtIn) throws IOException {
		value = nbtIn.dis.readInt();
		return this;
	}

	@Override
	public String toTagString() {
		return NBTUtil.checkColon(this) + value;
	}
	
	@Override
	public String toString() {
		return "<int:" + getName() + ":" + value + ">";
	}
	
	@Override
	public IntTag clone() {
		return new IntTag(getName(), value);
	}
}
