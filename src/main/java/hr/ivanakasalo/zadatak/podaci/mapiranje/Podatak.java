package hr.ivanakasalo.zadatak.podaci.mapiranje;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotacija polja za CsvMapiranje
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Podatak {
    String naziv() default "";
}
