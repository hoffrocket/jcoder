package bin.simple.parser;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import junit.framework.TestCase;
import bin.simple.FieldParser;

public abstract class AbstractParserTest extends TestCase {


	private String _fieldName;
	private Class<? extends FieldParser> _parserClass;
	private ByteBuffer _buffer;
	private FieldParser _fieldParser;
	
	public AbstractParserTest(Class<? extends FieldParser> clazz, String fieldName) {
		_parserClass = clazz;
		_fieldName = fieldName;
	}

	public static void assertFieldEquals(Object expected, Object actual)
	{
		assertEquals("Not expected field value", expected, actual);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		_buffer = ByteBuffer.allocate(256);
		_fieldParser = createFieldParser();
	}
	
	@Override
	protected void tearDown() throws Exception {
		_buffer = null;
		_fieldParser = null;
		super.tearDown();
	}
	
	protected void writeToBuffer()
	{
		writeToBuffer(this);
	}
	
	protected void writeToBuffer(Object obj) {
		try {
			getFieldParser().writeToBuffer(obj, getBuffer());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	protected void readFromBuffer()
	{
		readFromBuffer(this);
	}
	
	protected void readFromBuffer(Object obj) {

		try {
			getFieldParser().readFromBuffer(obj, getBuffer());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	public FieldParser getFieldParser() {
		return _fieldParser;
	}
	
	public ByteBuffer getBuffer() {
		return _buffer;
	}
	
	protected FieldParser createFieldParser(){
		return createFieldParser(_parserClass, _fieldName);
	}
	
	protected FieldParser createFieldParser(Class clazz, String fieldName)
	{

		try {
			return FieldParser.constructFieldParser(clazz, getField(fieldName));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	protected Field getField(String fieldName) throws NoSuchFieldException {
		return this.getClass().getDeclaredField(fieldName);
	}
	
}
