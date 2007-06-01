package bin.simple.parser;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import com.sun.mirror.type.PrimitiveType;
import com.sun.mirror.type.PrimitiveType.Kind;

public class PrimitiveArrayParser extends AbstractArrayParser {


	private Kind _primType;

	public PrimitiveArrayParser(Field field) {
		super(field, null);
		_primType = PrimitiveType.Kind.valueOf(getComponentType().getName().toUpperCase());
	}

	@Override
	protected void getFromArrayIndexToBuffer(Object array, int index, ByteBuffer buffer) {

		getFromArrayIndexToBuffer(_primType, array, index, buffer);
	}

	static void getFromArrayIndexToBuffer(Kind primType, Object array, int index, ByteBuffer buffer) {
		switch(primType)
		{
			case BYTE:
				buffer.put(Array.getByte(array, index));
				break;
			case SHORT:
				buffer.putShort(Array.getShort(array, index));
				break;
			case CHAR:
				buffer.putChar(Array.getChar(array, index));
				break;
			case INT:
				buffer.putInt(Array.getInt(array, index));
				break;
			case LONG:
				buffer.putLong(Array.getLong(array, index));
				break;
			case FLOAT:
				buffer.putFloat(Array.getFloat(array, index));
				break;
			case DOUBLE:
				buffer.putDouble(Array.getDouble(array, index));
				break;
			default:
				break;
		}
	}
	
	static void setToArrayIndexFromBuffer(Kind primType, Object array, int index, ByteBuffer buffer) {
		switch(primType)
		{
			case BYTE:
				Array.setByte(array, index, buffer.get());
				break;
			case SHORT:
				Array.setShort(array, index, buffer.getShort());
				break;
			case CHAR:
				Array.setChar(array, index, buffer.getChar());
				break;
			case INT:
				Array.setInt(array, index, buffer.getInt());
				break;
			case LONG:
				Array.setLong(array, index, buffer.getLong());
				break;
			case FLOAT:
				Array.setFloat(array, index, buffer.getFloat());
				break;
			case DOUBLE:
				Array.setDouble(array, index, buffer.getDouble());
				break;
			default:
				break;
		}
	}

	@Override
	protected void setToArrayIndexFromBuffer(Object array, int index, ByteBuffer buffer) {
		setToArrayIndexFromBuffer(_primType, array, index, buffer);
		
	}

}
