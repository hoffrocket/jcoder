package bin.simple.parser;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import bin.simple.FieldParser;

public class LongParser extends FieldParser {

	public LongParser(Field field) {
		super(field, long.class);
	}


	public void readFromBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException {
		field.setLong(object, buffer.getLong());		
	}


	public void writeToBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException {
		
		buffer.putLong(field.getLong(object));
		
	}

}
