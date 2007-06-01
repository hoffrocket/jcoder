package bin.simple.parser;

import java.nio.ByteBuffer;

import bin.simple.BinaryField;

public class ObjectParserTest extends AbstractParserTest {


	TestObject objectField;
	
	public ObjectParserTest() {
		super(ObjectParser.class, "objectField");
	}

	public void testRead() throws Exception
	{
		TestObject expected = getTestObject(123);
		
		putTestObjectIntoBuffer(expected, getBuffer());
		getBuffer().flip();
		
		readFromBuffer();
		
		assertFieldEquals(expected, this.objectField);
	}
	
	public void testWrite() throws Exception
	{
		
		this.objectField = getTestObject(12323);
		
		writeToBuffer();
		
		getBuffer().flip();
		
		assertFieldEquals(this.objectField, getTestObjectFromBuffer(getBuffer()));
	}

	static TestObject getTestObject(int index)
	{
		TestObject object = new TestObject();
		object.intField = index;
		object.floatField = 3.14f;
		object.byteField = 0x1f;
		object.innerObject.intField = index - 10;
		return object;
	}
	
	static void putTestObjectIntoBuffer(TestObject obj, ByteBuffer buffer)
	{
		buffer.putInt(obj.intField);
		buffer.putFloat(obj.floatField);
		buffer.put(obj.byteField);
		buffer.putInt(obj.innerObject.intField);
	}
	
	static TestObject getTestObjectFromBuffer(ByteBuffer buffer)
	{
		TestObject obj = new TestObject();
		obj.intField = buffer.getInt();
		obj.floatField = buffer.getFloat();
		obj.byteField = buffer.get();
		obj.innerObject.intField = buffer.getInt();
		return obj;
	}
	
	static class TestObject
	{
		public TestObject() {
			innerObject = new InnerTestObject();
		}
		@BinaryField(PrimitiveParser.class)
		int intField;
		
		@BinaryField(PrimitiveParser.class)
		float floatField;
		
		@BinaryField(PrimitiveParser.class)
		byte byteField;
		
		@BinaryField(ObjectParser.class)
		InnerTestObject innerObject;
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null || ! ( obj instanceof TestObject)) return false;
			
			TestObject other = (TestObject)obj;
			return this.intField == other.intField && 
			this.byteField == other.byteField && 
			this.floatField == other.floatField &&
			innerObject.equals(other.innerObject);
		}
		
		@Override
		public String toString() {
			return getClass().getName() + ": " + this.intField;
		}
	}
	
	static class InnerTestObject
	{
		@BinaryField(PrimitiveParser.class)
		int intField;
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null || ! ( obj instanceof InnerTestObject)) return false;
			
			InnerTestObject other = (InnerTestObject)obj;
			return this.intField == other.intField;
		}
	}
}
