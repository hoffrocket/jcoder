package bin.simple.parser;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import bin.simple.MessageParseException;
import bin.simple.MessageProcessor;

public class ObjectArrayParser extends AbstractArrayParser {

	private MessageProcessor _messageProcessor;

	public ObjectArrayParser(Field field) {
		super(field, null);
		_messageProcessor = new MessageProcessor(getComponentType());
	}

	@Override
	protected void getFromArrayIndexToBuffer(Object array, int index,
			ByteBuffer buffer) {
		
		_messageProcessor.writeToBuffer(Array.get(array, index), buffer);
	}

	@Override
	protected void setToArrayIndexFromBuffer(Object array, int index,
			ByteBuffer buffer) throws IllegalAccessException {
		try {
			Object value = getComponentType().newInstance();
			_messageProcessor.readFromBuffer(value, buffer);
			Array.set(array, index, value);
		} catch (InstantiationException e) {
			throw new MessageParseException("Could not create new instance of " + getComponentType(), e);
		} 
	}

}
