package me.bjtmastermind.nbt.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.GZIPInputStream;

import me.bjtmastermind.nbt.tag.Tag;

public final class NBTUtil {

	private NBTUtil() {}

	public static void write(Tag<?> tag, File file, boolean compressed) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(file)) {
			new NBTSerializer(compressed).toStream(new NamedTag(null, tag), fos);
		}
	}
	
	public static void write(Tag<?> tag, String file) throws IOException {
		write(tag, new File(file), true);
	}
	
	public static void writeLE(Tag<?> tag, String file) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(file)) {
			new NBTSerializer(false, true).toStream(new NamedTag(null, tag), fos);
		}
	}

	public static NamedTag read(String file, boolean compressed) throws IOException {
		try (FileInputStream fis = new FileInputStream(file)) {
			return new NBTDeserializer(compressed).fromStream(fis);
		}
	}
	
	public static NamedTag read(String file) throws IOException {
		return read(file, true);
	}

	public static NamedTag readLE(String file) throws IOException {
		try (FileInputStream fis = new FileInputStream(file)) {
			return new NBTDeserializer(false, true).fromStream(detectDecompression(fis));
		}
	}

	private static InputStream detectDecompression(InputStream is) throws IOException {
		PushbackInputStream pbis = new PushbackInputStream(is, 2);
		int signature = (pbis.read() & 0xFF) + (pbis.read() << 8);
		pbis.unread(signature >> 8);
		pbis.unread(signature & 0xFF);
		if (signature == GZIPInputStream.GZIP_MAGIC) {
			return new GZIPInputStream(pbis);
		}
		return pbis;
	}
}
