package util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Repeatable(CJCommands.class)
public @interface CJCommand {
	String name();
	String prompt();
	String rel();
	String method();
	String[] precondition();
	CJson[] data();
}