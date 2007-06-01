package bin.simple;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BinaryField {
	Class<? extends FieldParser> value();
	int length() default 0;
	Class lengthType() default int.class;
}
