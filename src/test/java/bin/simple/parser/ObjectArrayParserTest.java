package bin.simple.parser;

import junitx.framework.ArrayAssert;
import bin.simple.BinaryField;
import bin.simple.parser.ObjectParserTest.TestObject;

public class ObjectArrayParserTest extends AbstractParserTest {

	@BinaryField(ObjectArrayParser.class)
	TestObject [] objectArray;
	
	public ObjectArrayParserTest() {
		super(ObjectArrayParser.class, "objectArray");
	}
	
	public void testRead()
	{
		TestObject [] expected = new TestObject[5];
		
		getBuffer().putInt(expected.length);
		for (int i = 0; i < expected.length; i++)
		{
			expected[i] = ObjectParserTest.getTestObject(100 + i);
			ObjectParserTest.putTestObjectIntoBuffer(expected[i], getBuffer());
		}
		
		getBuffer().flip();
		
		readFromBuffer();
		
		ArrayAssert.assertEquals(expected, this.objectArray);
		
	}

	public void testWrite()
	{
		objectArray = new TestObject[5];
		
		for (int i = 0; i < objectArray.length; i++)
		{
			objectArray[i] = ObjectParserTest.getTestObject(100 + i);
		}
		
		writeToBuffer();
		getBuffer().flip();
		
		TestObject [] actualArray = new TestObject[getBuffer().getInt()];
		
		for (int i = 0; i < actualArray.length; i++)
		{
			actualArray[i] = ObjectParserTest.getTestObjectFromBuffer(getBuffer());
		}
		
		ArrayAssert.assertEquals(objectArray, actualArray);
	}
	

}
