package bin.simple.parser;

import bin.simple.BinaryField;

public class DoubleParserTest extends AbstractParserTest {

	
	@BinaryField(DoubleParser.class)
	private double field;

	public DoubleParserTest() {
		super(DoubleParser.class, "field");
	}
	
	public void testRead() throws Exception
	{
		double expected = 6.02e23;
		
		getBuffer().putDouble(expected);
		getBuffer().flip();
		
		readFromBuffer();
		
		assertFieldEquals(expected, this.field);
	}
	
	public void testWrite() throws Exception
	{
		
		this.field = 6.67e-11;
		
		writeToBuffer();
		
		getBuffer().flip();
		
		assertFieldEquals(this.field, getBuffer().getDouble());
	}
	
}
