package bin.simple.parser;

import junitx.framework.ArrayAssert;
import bin.simple.BinaryField;

import com.sun.mirror.type.PrimitiveType.Kind;

public class PrimitiveArrayParserTest extends AbstractParserTest {

	@BinaryField(PrimitiveArrayParser.class)
	private int [] integerArray;
	
	public PrimitiveArrayParserTest() {
		super(PrimitiveArrayParser.class, "integerArray");
	}


	public void testGetFromArrayIndexToBufferByte()
	{
		byte [] array = {0x1B};
		PrimitiveArrayParser.getFromArrayIndexToBuffer(Kind.BYTE, array, 0, getBuffer());
		getBuffer().flip();
		assertEquals(array[0], getBuffer().get());
	}

	public void testGetFromArrayIndexToBufferShort()
	{
		short [] array = {19};
		PrimitiveArrayParser.getFromArrayIndexToBuffer(Kind.SHORT, array, 0, getBuffer());
		getBuffer().flip();
		assertEquals(array[0], getBuffer().getShort());
	}
	
	public void testGetFromArrayIndexToBufferChar()
	{
		char [] array = {0x1234};
		PrimitiveArrayParser.getFromArrayIndexToBuffer(Kind.CHAR, array, 0, getBuffer());
		getBuffer().flip();
		assertEquals(array[0], getBuffer().getChar());
	}
	
	public void testGetFromArrayIndexToBufferInt()
	{
		int [] array = {12323};
		PrimitiveArrayParser.getFromArrayIndexToBuffer(Kind.INT, array, 0, getBuffer());
		getBuffer().flip();
		assertEquals(array[0], getBuffer().getInt());
	}
	
	public void testGetFromArrayIndexToBufferLong()
	{
		long [] array = {234234242342344L};
		PrimitiveArrayParser.getFromArrayIndexToBuffer(Kind.LONG, array, 0, getBuffer());
		getBuffer().flip();
		assertEquals(array[0], getBuffer().getLong());
	}
	
	public void testGetFromArrayIndexToBufferFloat()
	{
		float [] array = {3.14159f};
		PrimitiveArrayParser.getFromArrayIndexToBuffer(Kind.FLOAT, array, 0, getBuffer());
		getBuffer().flip();
		assertEquals(array[0], getBuffer().getFloat());
	}
	
	public void testGetFromArrayIndexToBufferDouble()
	{
		double [] array = {6.02e23};
		PrimitiveArrayParser.getFromArrayIndexToBuffer(Kind.DOUBLE, array, 0, getBuffer());
		getBuffer().flip();
		assertEquals(array[0], getBuffer().getDouble());
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


	public void testSetToArrayIndexFromBufferByte()
	{
		byte expectedByte = (byte) 0x3a;
		byte [] array = new byte[1];
		getBuffer().put(expectedByte);
		getBuffer().flip();
		PrimitiveArrayParser.setToArrayIndexFromBuffer(Kind.BYTE, array, 0, getBuffer());
		assertEquals(expectedByte, array[0]);
	}

	public void testSetToArrayIndexFromBufferShort()
	{
		short expected = 1234;
		short [] array = new short[1];
		getBuffer().putShort(expected);
		getBuffer().flip();
		PrimitiveArrayParser.setToArrayIndexFromBuffer(Kind.SHORT, array, 0, getBuffer());
		assertEquals(expected, array[0]);
	}
	
	public void testSetToArrayIndexFromBufferChar()
	{
		char expected = 0x1234;
		char [] array = new char[1];
		getBuffer().putChar(expected);
		getBuffer().flip();
		PrimitiveArrayParser.setToArrayIndexFromBuffer(Kind.CHAR, array, 0, getBuffer());
		assertEquals(expected, array[0]);
	}
	
	public void testSetToArrayIndexFromBufferInt()
	{
		int expected = 1234;
		int [] array = new int[1];
		getBuffer().putInt(expected);
		getBuffer().flip();
		PrimitiveArrayParser.setToArrayIndexFromBuffer(Kind.INT, array, 0, getBuffer());
		assertEquals(expected, array[0]);
	}
	
	public void testSetToArrayIndexFromBufferLong()
	{
		long expected = 1234;
		long [] array = new long[1];
		getBuffer().putLong(expected);
		getBuffer().flip();
		PrimitiveArrayParser.setToArrayIndexFromBuffer(Kind.LONG, array, 0, getBuffer());
		assertEquals(expected, array[0]);
	}
	
	public void testSetToArrayIndexFromBufferFloat()
	{
		float expected = 1234;
		float [] array = new float[1];
		getBuffer().putFloat(expected);
		getBuffer().flip();
		PrimitiveArrayParser.setToArrayIndexFromBuffer(Kind.FLOAT, array, 0, getBuffer());
		assertEquals(expected, array[0]);
	}
	
	public void testSetToArrayIndexFromBufferdouble()
	{
		double expected = 1234;
		double [] array = new double[1];
		getBuffer().putDouble(expected);
		getBuffer().flip();
		PrimitiveArrayParser.setToArrayIndexFromBuffer(Kind.DOUBLE, array, 0, getBuffer());
		assertEquals(expected, array[0]);
	}
}
