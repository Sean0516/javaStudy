package validator.common.group;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author sean
 * @date 2019/12/13  15:22
 */

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
@Documented
public @interface Gender {

    String message() default "性别只能为男或者女";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
