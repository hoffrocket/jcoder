package bin.simple.parser;

import bin.simple.BinaryField;

public class ByteParserTest extends AbstractParserTest {

	
	@BinaryField(ByteParser.class)
	private byte byteField;

	public ByteParserTest() {
		super(ByteParser.class, "byteField");
	}
	
	public void testRead() throws Exception
	{
		byte expected = 0x37;
		
		getBuffer().put(expected);
		getBuffer().flip();
		
		readFromBuffer();
		
		assertFieldEquals(expected, this.byteField);
	}
	
	public void testWrite() throws Exception
	{
		
		this.byteField = 0x25;
		
		writeToBuffer();
		
		getBuffer().flip();
		
		assertFieldEquals(this.byteField, getBuffer().get());
	}
	
}
