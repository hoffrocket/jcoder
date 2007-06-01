package bin.simple.parser;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import bin.simple.FieldParser;

public class FloatParser extends FieldParser {

	public FloatParser(Field field) {
		super(field, float.class);
	}


	public void readFromBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException {
		field.setFloat(object, buffer.getFloat());		
	}


	public void writeToBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException {
		
		buffer.putFloat(field.getFloat(object));
		
	}

}
