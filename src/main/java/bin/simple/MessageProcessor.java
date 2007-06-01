package bin.simple;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import bin.simple.parser.ByteParser;

public class MessageProcessor<T> {

	private final Class<T> _objectClass;
	private final List<FieldParser> _binaryFields;
	
	public MessageProcessor(Class<T> objectClass)
	{
		_objectClass = objectClass;
		_binaryFields = getBinaryFieldsFromClass(_objectClass);
	}
	
	static <T> List<FieldParser> getBinaryFieldsFromClass(Class<T> objectClass) 
	{
		List<FieldParser> fieldList = new ArrayList<FieldParser>();
		for (Field field : objectClass.getDeclaredFields())
		{
			BinaryField binField = field.getAnnotation(BinaryField.class);
			if (binField != null)
			{
				FieldParser fieldParser = null;
				try {
					fieldParser = FieldParser.constructFieldParser(binField.value(), field);
				} catch (Exception e) {
					
					throw new InitializationException("Could not create parsing strategy for " + objectClass.getName(), e);
				} 
				
				fieldList.add(fieldParser);
			}
		}
		return fieldList;
	}

	private void processObjectWithBuffer(T object, ByteBuffer buffer, boolean isRead) throws MessageParseException
	{
		for (FieldParser fieldParser : _binaryFields)
		{
			try {
				if (isRead)
					fieldParser.readFromBuffer(object, buffer);
				else
					fieldParser.writeToBuffer(object, buffer);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (Throwable e) {
				throw new MessageParseException("Error caught processing field " + fieldParser.getFieldName() +
						" in class " + _objectClass.getName() + " for instance " + String.valueOf(object), e);
			}
		}
	}
	
	public void readFromBuffer(T object, ByteBuffer buffer) throws MessageParseException
	{
		processObjectWithBuffer(object, buffer, true);
	}
	
	public void writeToBuffer(T object, ByteBuffer buffer) throws MessageParseException
	{
		processObjectWithBuffer(object, buffer, false);
	}
}
