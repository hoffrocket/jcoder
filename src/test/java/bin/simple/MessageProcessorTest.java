package bin.simple;

import java.nio.ByteBuffer;
import java.util.List;

import junit.framework.TestCase;
import bin.simple.parser.IntParser;
import bin.simple.parser.PrimitiveParser;

public class MessageProcessorTest extends TestCase {

	@BinaryField(IntParser.class)
	int intField;
	
	@BinaryField(PrimitiveParser.class)
	double doubleField;
	
	MessageProcessor<MessageProcessorTest> messageProcessor;
	
	@Override
	protected void setUp() throws Exception {
		
		super.setUp();
		
		messageProcessor =  new MessageProcessor<MessageProcessorTest>(MessageProcessorTest.class);
	}
	
	@Override
	protected void tearDown() throws Exception {
		messageProcessor = null;
		super.tearDown();
	}
	
	public void testGraphCreation()
	{
		List<FieldParser> fields = MessageProcessor.getBinaryFieldsFromClass(MessageProcessorTest.class);
		
		//order is important
		assertEquals("expecting 2 parsers", 2, fields.size());
		
		FieldParser intParser = fields.get(0);
		assertEquals("first should be IntParser", IntParser.class, intParser.getClass());
		assertEquals("first should be doubleField", "intField", intParser.getFieldName());
		
		FieldParser primitiveParser = fields.get(1);
		assertEquals("second should be DoubleParser", PrimitiveParser.class, primitiveParser.getClass());
		assertEquals("second should be doubleField", "doubleField", primitiveParser.getFieldName());
		
	}
	
	public void testReadFromBuffer() throws MessageParseException
	{
		int expectedInt = 10;
		double expectedDouble = 6.02e23;
		ByteBuffer buffer = ByteBuffer.allocate(12);
		buffer.putInt(expectedInt);
		buffer.putDouble(expectedDouble);
		buffer.flip();
		messageProcessor.readFromBuffer(this, buffer);
		
		assertEquals(expectedInt, intField);
		assertEquals(expectedDouble, doubleField);
	}
	
	public void testWriteToBuffer() throws MessageParseException
	{
		intField = 10;
		doubleField = 6.02e23;
		ByteBuffer buffer = ByteBuffer.allocate(12);
		
		messageProcessor.writeToBuffer(this, buffer);
		buffer.flip();
		
		assertEquals(intField, buffer.getInt());
		assertEquals(doubleField, buffer.getDouble());
	}
	
}
