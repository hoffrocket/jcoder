package bin.simple.parser;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import bin.simple.FieldParser;

public class ByteParser extends FieldParser {

	public ByteParser(Field field) {
		super(field, byte.class);
	}


	public void readFromBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException {
		field.setByte(object, buffer.get());		
	}


	public void writeToBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException {
		
		buffer.put(field.getByte(object));
		
	}

}
