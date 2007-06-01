package bin.simple.parser;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import bin.simple.BinaryField;
import bin.simple.FieldParser;

public class FixedStringParser extends FieldParser {

	private final int _length;
	
	public FixedStringParser(Field field) {
		super(field, String.class);
		_length = field.getAnnotation(BinaryField.class).length();
	}

	public void readFromBuffer(Field field, Object object, ByteBuffer buffer)
			throws IllegalArgumentException, IllegalAccessException {
		
		byte [] strBuf = new byte[_length];
		buffer.get(strBuf);

		field.set(object, new String(strBuf));
	}

	public void writeToBuffer(Field field, Object object, ByteBuffer buffer)
			throws IllegalArgumentException, IllegalAccessException {
		
		String str = (String) field.get(object);
		
		int putLen = Math.min(_length, str.length());
		buffer.put(str.getBytes(), 0, putLen);

		if (putLen < _length)
		{
			zeroPadBuffer(buffer, _length - putLen);
		}
	}

	

}
