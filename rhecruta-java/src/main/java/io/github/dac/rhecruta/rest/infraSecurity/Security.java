package io.github.dac.rhecruta.rest.infraSecurity;

import io.github.dac.rhecruta.rest.infraSecurity.model.NivelAcesso;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Security {

    NivelAcesso[] value() default {};
}