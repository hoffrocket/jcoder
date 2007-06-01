package bin.simple.parser;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import bin.simple.FieldParser;
import bin.simple.InitializationException;

public class PrimitiveParser extends FieldParser {
	
	static final String COULD_NOT_FIND_FIELD_PARSER_FOR_FIELD_TYPE = "Could not find field parser for field type ";
	static final String FIELD_IS_NOT_A_PRIMITIVE_TYPE = "Field is not a primitive type";
	private final FieldParser _fieldParser;
	
	public PrimitiveParser(Field field) {
		super(field);
		_fieldParser = getPrimitiveFieldParser(field, getClassParserMap());
	}
	
	
	protected Map<Class, Class<? extends FieldParser>> getClassParserMap()
	{
		return standardClassToParserMap();	
	}

	public void readFromBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException 
	{
		_fieldParser.readFromBuffer(object, buffer);
	}
	
	public void writeToBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException 
	{
		_fieldParser.writeToBuffer(object, buffer);
	}

	public static FieldParser getPrimitiveFieldParser(Field field)
	{
		return getPrimitiveFieldParser(field,standardClassToParserMap());
	}
	
	public static FieldParser getPrimitiveFieldParser(Field field, Map<Class, Class<? extends FieldParser>> classParserMap) {

		if (!field.getType().isPrimitive())
			throw new InitializationException(FIELD_IS_NOT_A_PRIMITIVE_TYPE);
		
		Class<? extends FieldParser> fieldParserClass = classParserMap.get(field.getType());
		
		if (fieldParserClass == null)
			throw new InitializationException(COULD_NOT_FIND_FIELD_PARSER_FOR_FIELD_TYPE + field.getType().getName());
		
	
		return FieldParser.constructFieldParser(fieldParserClass, field);

	}


	public static Map<Class, Class<? extends FieldParser>> standardClassToParserMap() {
		Map<Class, Class<? extends FieldParser>> classParserMap = new HashMap<Class, Class<? extends FieldParser>>();
		classParserMap.put(byte.class, ByteParser.class);
		classParserMap.put(short.class, ShortParser.class);
		classParserMap.put(int.class, IntParser.class);
		classParserMap.put(long.class, LongParser.class);
		classParserMap.put(float.class, FloatParser.class);
		classParserMap.put(double.class, DoubleParser.class);
		classParserMap.put(char.class, CharParser.class);
		return classParserMap;
	}


}
