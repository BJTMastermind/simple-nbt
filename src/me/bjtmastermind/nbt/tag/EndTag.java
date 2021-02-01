package me.bjtmastermind.nbt.tag;

public final class EndTag extends Tag<Void> {

	public static final byte ID = 0;
	public static final EndTag INSTANCE = new EndTag();

	private EndTag() {
		super(null);
	}

	@Override
	public byte getID() {
		return ID;
	}

	@Override
	protected Void checkValue(Void value) {
		return value;
	}

	@Override
	public String valueToString(int maxDepth) {
		return "\"end\"";
	}

	@Override
	public EndTag clone() {
		return INSTANCE;
	}

	@Override
	public byte valueToByte(int maxDepth) {
		return 0;
	}

	@Override
	public short valueToShort(int maxDepth) {
		return 0;
	}

	@Override
	public int valueToInt(int maxDepth) {
		return 0;
	}

	@Override
	public long valueToLong(int maxDepth) {
		return 0;
	}

	@Override
	public float valueToFloat(int maxDepth) {
		return 0;
	}

	@Override
	public double valueToDouble(int maxDepth) {
		return 0;
	}
}
