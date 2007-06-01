package bin.simple.parser;

import bin.simple.BinaryField;
import bin.simple.FieldParser;
import bin.simple.InitializationException;

public class PrimitiveParserTest extends AbstractParserTest {

	
	@BinaryField(PrimitiveParser.class)
	private long longField;
	
	private int intField;
	private byte byteField;
	private short shortField;
	private char charField;
	private float floatField;
	private double doubleField;
	
	private String stringField;
	private boolean booleanField;
	
	public PrimitiveParserTest() {
		super(PrimitiveParser.class, "longField");
	}
	
	public void testGetPrimitiveParser() throws NoSuchFieldException
	{
		assertFieldParserMatchesType("intField", IntParser.class);
		assertFieldParserMatchesType("byteField", ByteParser.class);
		assertFieldParserMatchesType("shortField", ShortParser.class);
		assertFieldParserMatchesType("charField", CharParser.class);
		assertFieldParserMatchesType("floatField", FloatParser.class);
		assertFieldParserMatchesType("doubleField", DoubleParser.class);
		assertFieldParserMatchesType("longField", LongParser.class);
	}
	
	public void testGetPrimitiveParserNotPrimitive() throws NoSuchFieldException 
	{
		try {
			PrimitiveParser.getPrimitiveFieldParser(getField("stringField"));
			fail("expecting exception");
		} catch (InitializationException e) {
			assertEquals(PrimitiveParser.FIELD_IS_NOT_A_PRIMITIVE_TYPE, e.getMessage());
		}
	}
	
	public void testGetPrimitiveParserNotParsed() throws NoSuchFieldException 
	{
		try {
			PrimitiveParser.getPrimitiveFieldParser(getField("booleanField"));
			fail("expecting exception");
		} catch (InitializationException e) {
			assertEquals(PrimitiveParser.COULD_NOT_FIND_FIELD_PARSER_FOR_FIELD_TYPE + boolean.class.getName(), e.getMessage());
		}
	}
	
	private void assertFieldParserMatchesType(String fieldName, Class<? extends FieldParser> parserClass) throws NoSuchFieldException {

		
			FieldParser parser = PrimitiveParser.getPrimitiveFieldParser(getField(fieldName));
			
			assertEquals("Not expected parser class", parserClass, parser.getClass());

	}

	public void testRead() throws Exception
	{
		long expectedLong = 23908432948222343L;
		
		getBuffer().putLong(expectedLong);
		getBuffer().flip();
		
		readFromBuffer();
		
		assertFieldEquals(expectedLong, this.longField);
	}
	
	public void testWrite() throws Exception
	{
		
		this.longField = 23908432948222343L;
		
		writeToBuffer();
		
		getBuffer().flip();
		
		assertFieldEquals(this.longField, getBuffer().getLong());
	}
	
}
