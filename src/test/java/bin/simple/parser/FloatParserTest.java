package bin.simple.parser;

import bin.simple.BinaryField;

public class FloatParserTest extends AbstractParserTest {

	
	@BinaryField(FloatParser.class)
	private float field;

	public FloatParserTest() {
		super(FloatParser.class, "field");
	}
	
	public void testRead() throws Exception
	{
		float expected = 2.718f;
		
		getBuffer().putFloat(expected);
		getBuffer().flip();
		
		readFromBuffer();
		
		assertFieldEquals(expected, this.field);
	}
	
	public void testWrite() throws Exception
	{
		
		this.field = 1.618f;
		
		writeToBuffer();
		
		getBuffer().flip();
		
		assertFieldEquals(this.field, getBuffer().getFloat());
	}
	
}
