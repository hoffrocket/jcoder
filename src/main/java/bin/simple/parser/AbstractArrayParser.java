package bin.simple.parser;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import bin.simple.BinaryField;
import bin.simple.FieldParser;
import bin.simple.InitializationException;

public abstract class AbstractArrayParser extends FieldParser {

	private Class _lengthType;
	private Class _componentType;

	public AbstractArrayParser(Field field, Class verifyType) {
		super(field, verifyType);
		BinaryField binField = getBinaryField();
		_lengthType = binField.lengthType();
		_componentType = field.getType().getComponentType();
		verifyLengthType(_lengthType);
	}

	static void verifyLengthType(Class lengthType) {

		List validTypes = Arrays.asList(int.class, byte.class, short.class, long.class);
		if (!validTypes.contains(lengthType))
		{
			throw new InitializationException("Length type not any of: " + validTypes);
		}
	}

	@Override
	protected void readFromBuffer(Field field, Object object, ByteBuffer buffer)
			throws IllegalArgumentException, IllegalAccessException {

		int length = getLength(buffer);
		
		Object array = Array.newInstance(getComponentType(), length);

		for (int i = 0; i < length; i++)
		{
			setToArrayIndexFromBuffer(array, i, buffer);
		}
		
		field.set(object, array);
	}
	
	protected abstract void setToArrayIndexFromBuffer(Object array, int index, ByteBuffer buffer) throws IllegalAccessException;
	protected abstract void getFromArrayIndexToBuffer(Object array, int index, ByteBuffer buffer);

	protected Class getComponentType() 
	{
		return _componentType;
	}


	private int getLength(ByteBuffer buffer) {
		
		if (int.class.equals(_lengthType))
		{
			return buffer.getInt();
		}
		else if (byte.class.equals(_lengthType))
		{
			return buffer.get();
		}
		else if (short.class.equals(_lengthType))
		{
			return buffer.getShort();
		}
		else if (long.class.equals(_lengthType))
		{
			return (int)buffer.getLong();
		}
		
		return 0;
	}
	
	private void writeLength(int length, ByteBuffer buffer) 
	{
		
		if (int.class.equals(_lengthType))
		{
			buffer.putInt(length);
		}
		else if (byte.class.equals(_lengthType))
		{
			buffer.put((byte)length);
		}
		else if (short.class.equals(_lengthType))
		{
			buffer.putShort((short)length);
		}
		else if (long.class.equals(_lengthType))
		{
			buffer.putLong(length);
		}
		
	}


	@Override
	protected void writeToBuffer(Field field, Object object, ByteBuffer buffer)
			throws IllegalArgumentException, IllegalAccessException {

		Object array = field.get(object);
		int length = array == null ? 0 : Array.getLength(array);
		writeLength(length, buffer);
		if (length > 0)
		{
			for (int i = 0; i < length; i++)
			{
				getFromArrayIndexToBuffer(array, i, buffer);
			}
		}
	}

}
