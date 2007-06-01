package bin.simple;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import bin.simple.parser.AbstractParserTest;

public class FieldParserTest extends AbstractParserTest {

	private static final String DOUBLE_FIELD = "doubleField";

	private static final String INTEGER_FIELD = "integerField";

	@BinaryField(FieldParserTestParser.class)
	Double doubleField;
	
	@BinaryField(FieldParserTestParser.class)
	Integer integerField;
	
	public FieldParserTest() {
		super(FieldParserTestParser.class, DOUBLE_FIELD);
	}
	
	
	public void testCreateParserWithBadFieldType() throws NoSuchFieldException
	{
		try {
			new FieldParserTestParser(getField(INTEGER_FIELD));
			fail("expected exception");
		} catch (InitializationException e) {
			assertEquals(FieldParser.getVerifyTypeMessage(Double.class, INTEGER_FIELD, Integer.class),e.getMessage());
		}
	}
	
	public void testGetFieldName() throws NoSuchFieldException
	{
		assertEquals(DOUBLE_FIELD, new FieldParserTestParser(getField(DOUBLE_FIELD)).getFieldName());
	}
	
	public void testConstructFieldParser()
	{
		FieldParser fieldParser = getFieldParser();
		assertEquals(FieldParserTestParser.class, fieldParser.getClass());
	}
	
	private static class FieldParserTestParser extends FieldParser
	{

		public FieldParserTestParser(Field field) {
			super(field,Double.class);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void readFromBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException {
			
			
			
		}

		@Override
		protected void writeToBuffer(Field field, Object object, ByteBuffer buffer) throws IllegalArgumentException, IllegalAccessException {
			// TODO Auto-generated method stub
			
		}
		
	}
}
