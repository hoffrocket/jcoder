package bin.simple.parser;

import bin.simple.BinaryField;

public class FixedStringParserTest extends AbstractParserTest {

	
	@BinaryField(value = FixedStringParser.class, length=4)
	private String field;

	public FixedStringParserTest() {
		super(FixedStringParser.class, "field");
	}
	
	public void testRead() throws Exception
	{
		String expected = "TEST";
		
		getBuffer().put(expected.getBytes());
		getBuffer().flip();
		
		readFromBuffer();
		
		assertFieldEquals(expected, this.field);
	}
	
	public void testReadUnder() throws Exception
	{
		String expected = new String(new byte[]{'A','B',1,0});
		
		getBuffer().put(expected.getBytes());
		getBuffer().flip();
		
		readFromBuffer();
		
		assertFieldEquals(expected, this.field);
	}
	
	public void testWriteOver() throws Exception
	{
		
		this.field = "monkey";
		
		writeToBuffer();
		
		getBuffer().flip();
		
		byte [] buf = new byte[4];
		
		getBuffer().get(buf);
		assertFieldEquals(this.field.substring(0, 4), new String(buf));
	}
	
	public void testWrite() throws Exception
	{
		
		this.field = "TEST";
		
		writeToBuffer();
		
		getBuffer().flip();
		
		byte [] buf = new byte[4];
		
		getBuffer().get(buf);
		assertFieldEquals(this.field, new String(buf));
	}
	
	public void testWriteUnder() throws Exception
	{
		
		this.field = "AB";
		
		writeToBuffer();
		
		getBuffer().flip();
		
		byte [] buf = new byte[4];
		
		getBuffer().get(buf);
		
		byte [] expectedBuff = new byte[]{'A','B',0,0};
		
		assertFieldEquals(new String(expectedBuff), new String(buf));
	}
	
}
