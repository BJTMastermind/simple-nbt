package me.bjtmastermind.nbt.io;

import me.bjtmastermind.nbt.tag.Tag;
import java.io.IOException;

public interface NBTInput {

	NamedTag readTag(int maxDepth) throws IOException;

	Tag<?> readRawTag(int maxDepth) throws IOException;
}
