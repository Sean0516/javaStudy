package validator.common.group;

import com.google.common.collect.ImmutableSet;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author sean
 * @date 2019/12/13  15:23
 */
public class GenderValidator implements ConstraintValidator<Gender, String> {
    private final ImmutableSet<String> immutableSet = ImmutableSet.of("男", "女");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return immutableSet.contains(value);

    }
}
