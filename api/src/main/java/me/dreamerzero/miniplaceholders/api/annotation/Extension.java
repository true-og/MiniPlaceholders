package me.dreamerzero.miniplaceholders.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.dreamerzero.miniplaceholders.api.enums.Platform;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Extension {
    String name();

    Platform[] platform();

    String version() default "";
}
