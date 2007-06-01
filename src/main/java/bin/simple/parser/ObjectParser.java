package bin.simple.parser;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import bin.simple.FieldParser;
import bin.simple.MessageParseException;
import bin.simple.MessageProcessor;

public class ObjectParser extends FieldParser {

	private MessageProcessor _messageProcessor;

	public ObjectParser(Field field) {
		super(field);
		_messageProcessor = new MessageProcessor(field.getType());
	}

	@Override
	protected void readFromBuffer(Field field, Object object, ByteBuffer buffer)
			throws IllegalArgumentException, IllegalAccessException {
		Object newInstance;
		try {
			newInstance = field.getType().newInstance();
		} catch (InstantiationException e) {
			throw new MessageParseException("Could not create new instance of " + field.getType(), e);
		}
		_messageProcessor.readFromBuffer(newInstance, buffer);
		field.set(object, newInstance);
	}

	@Override
	protected void writeToBuffer(Field field, Object object, ByteBuffer buffer)
			throws IllegalArgumentException, IllegalAccessException {
		Object fieldValue = field.get(object);
		_messageProcessor.writeToBuffer(fieldValue, buffer);
	}

}
