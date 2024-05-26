package ru.mtsbank.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logging {
    public String value() default  "";
    public boolean entering() default false;
    public boolean exiting() default false;
    public String level() default  "INFO";

}
