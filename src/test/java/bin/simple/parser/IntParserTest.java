package bin.simple.parser;

import bin.simple.BinaryField;

public class IntParserTest extends AbstractParserTest {

	
	@BinaryField(IntParser.class)
	private int field;

	public IntParserTest() {
		super(IntParser.class, "field");
	}
	
	public void testRead() throws Exception
	{
		int expected = 1500450271;
		
		
		getBuffer().putInt(expected);
		getBuffer().flip();
		
		readFromBuffer();
		
		assertFieldEquals(expected, this.field);
	}
	
	public void testWrite() throws Exception
	{
		
		this.field = 234234234;
		
		writeToBuffer();
		
		getBuffer().flip();
		
		assertFieldEquals(this.field, getBuffer().getInt());
	}
	
}
