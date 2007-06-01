package bin.simple;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

public abstract class FieldParser
{

	public static final byte ZERO = (byte)0;
	private final Field _field;
	
	public FieldParser(Field field) 
	{
		this(field, null);
	}
	
	public FieldParser(Field field, Class verifyType) 
	{
		_field = field;
		
		if (verifyType != null && !verifyType.equals(_field.getType()))
		{
			throw new InitializationException(getVerifyTypeMessage(verifyType, _field.getName(), _field.getType()));
		}
	}

	static String getVerifyTypeMessage(Class verifyType, String fieldName, Class fieldType) {
		return "Field " + fieldName + " should be of type: " + verifyType.getName()  + " but is of type " + fieldType.getName();
	}

	public String getFieldName() {
		return _field.getName();
	}
	
	protected BinaryField getBinaryField()
	{
		return _field.getAnnotation(BinaryField.class);
	}
	
	public void readFromBuffer(Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException
	{
		readFromBuffer(_field, object, buffer);
	}
	
	public void writeToBuffer(Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException
	{
		writeToBuffer(_field, object, buffer);
	}

	protected abstract void readFromBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException;
	protected abstract void writeToBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException;
	
	public static void zeroPadBuffer(ByteBuffer buffer, int numBytes) 
	{
		for (int i = 0; i < numBytes; i++)
		{
			buffer.put(ZERO);
		}
		
	}
	
	public static FieldParser constructFieldParser(Class<? extends FieldParser> clazz, Field field)
	{
		try {
			field.setAccessible(true);
			Constructor constructor = clazz.getConstructor(Field.class);
			return (FieldParser) constructor.newInstance(field);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	

}
