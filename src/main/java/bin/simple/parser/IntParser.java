package bin.simple.parser;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import bin.simple.FieldParser;

public class IntParser extends FieldParser {

	public IntParser(Field field) {
		super(field, int.class);
	}


	public void readFromBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException {
		field.setInt(object, buffer.getInt());		
	}


	public void writeToBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException {
		
		buffer.putInt(field.getInt(object));
		
	}

}
