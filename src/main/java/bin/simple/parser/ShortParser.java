package bin.simple.parser;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import bin.simple.FieldParser;

public class ShortParser extends FieldParser {

	public ShortParser(Field field) {
		super(field, short.class);
	}


	public void readFromBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException {
		field.setShort(object, buffer.getShort());		
	}


	public void writeToBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException {
		
		buffer.putShort(field.getShort(object));
		
	}

}
