package me.bjtmastermind.nbt.io;

import me.bjtmastermind.nbt.tag.Tag;

public class NamedTag {

	private String name;
	private Tag<?> tag;

	public NamedTag(String name, Tag<?> tag) {
		this.name = name;
		this.tag = tag;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTag(Tag<?> tag) {
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public Tag<?> getTag() {
		return tag;
	}
}
