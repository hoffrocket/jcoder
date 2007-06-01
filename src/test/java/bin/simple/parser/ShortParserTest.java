package bin.simple.parser;

import bin.simple.BinaryField;

public class ShortParserTest extends AbstractParserTest {

	
	@BinaryField(ShortParser.class)
	private short field;

	public ShortParserTest() {
		super(ShortParser.class, "field");
	}
	
	public void testRead() throws Exception
	{
		short expected = 1024;
		
		getBuffer().putShort(expected);
		getBuffer().flip();
		
		readFromBuffer();
		
		assertFieldEquals(expected, this.field);
	}
	
	public void testWrite() throws Exception
	{
		
		this.field = 256;
		
		writeToBuffer();
		
		getBuffer().flip();
		
		assertFieldEquals(this.field, getBuffer().getShort());
	}
	
}
