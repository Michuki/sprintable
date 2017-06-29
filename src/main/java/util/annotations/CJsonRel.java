package util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CJsonRel {
	String name();
	String prompt();
	String rel();
	Access access(); 
}
