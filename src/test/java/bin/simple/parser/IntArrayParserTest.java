package bin.simple.parser;

import junitx.framework.ArrayAssert;
import bin.simple.BinaryField;

public class IntArrayParserTest extends AbstractParserTest {

	@BinaryField(value = IntArrayParser.class)
	int [] integerArray;
	
	public IntArrayParserTest() {
		super(IntArrayParser.class, "integerArray");
	}

	public void testReadFromBuffer() throws Exception
	{
		getBuffer().putInt(3);
		getBuffer().putInt(100);
		getBuffer().putInt(101);
		getBuffer().putInt(102);
		getBuffer().flip();
		
		int [] expected = {100,101,102};
		
		readFromBuffer();
		
		ArrayAssert.assertEquals(expected, integerArray);
	}
	
	public void testWriteToBuffer() throws Exception
	{

		
		integerArray = new int[]{200,201,202};
		
		writeToBuffer();
		getBuffer().flip();
		
		assertEquals(3, getBuffer().getInt());
		assertEquals(200, getBuffer().getInt());
		assertEquals(201, getBuffer().getInt());
		assertEquals(202, getBuffer().getInt());
	}

}
