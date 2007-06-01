package bin.simple.parser;

import bin.simple.BinaryField;

public class LongParserTest extends AbstractParserTest {

	
	@BinaryField(LongParser.class)
	private long longField;

	public LongParserTest() {
		super(LongParser.class, "longField");
	}
	
	public void testRead() throws Exception
	{
		long expectedLong = 2305843008139952128L;
		
		getBuffer().putLong(expectedLong);
		getBuffer().flip();
		
		readFromBuffer();
		
		assertFieldEquals(expectedLong, this.longField);
	}
	
	public void testWrite() throws Exception
	{
		
		this.longField = 9576890767L;
		
		writeToBuffer();
		
		getBuffer().flip();
		
		assertFieldEquals(this.longField, getBuffer().getLong());
	}
	
}
