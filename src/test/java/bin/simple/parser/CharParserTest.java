package bin.simple.parser;

import bin.simple.BinaryField;

public class CharParserTest extends AbstractParserTest {

	
	@BinaryField(CharParser.class)
	private char field;

	public CharParserTest() {
		super(CharParser.class, "field");
	}
	
	public void testRead() throws Exception
	{
		char expected = 0x1234;
		
		getBuffer().putChar(expected);
		getBuffer().flip();
		
		readFromBuffer();
		
		assertFieldEquals(expected, this.field);
	}
	
	public void testWrite() throws Exception
	{
		
		this.field = 0x4321;
		
		writeToBuffer();
		
		getBuffer().flip();
		
		assertFieldEquals(this.field, getBuffer().getChar());
	}
	
}
