package bin.simple.parser;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import com.sun.mirror.type.PrimitiveType;

public class IntArrayParser extends AbstractArrayParser {

	public IntArrayParser(Field field) {
		super(field, int[].class);
	}

	@Override
	protected void getFromArrayIndexToBuffer(Object array, int index, ByteBuffer buffer) {
		buffer.putInt(Array.getInt(array, index));
		
	}

	@Override
	protected void setToArrayIndexFromBuffer(Object array, int index, ByteBuffer buffer) {
		Array.setInt(array, index, buffer.getInt());
	}
	
}
