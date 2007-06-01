package bin.simple.parser;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import bin.simple.FieldParser;

public class DoubleParser extends FieldParser {

	public DoubleParser(Field field) {
		super(field, double.class);
	}


	
	public void readFromBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException {
		field.setDouble(object, buffer.getDouble());		
	}


	public void writeToBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException {
		
		buffer.putDouble(field.getDouble(object));
		
	}

}
