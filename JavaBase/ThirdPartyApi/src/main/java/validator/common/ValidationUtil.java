package validator.common;

import org.hibernate.validator.HibernateValidator;
import validator.common.group.UpdateGroup;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * @author sean
 * @date 2019/12/5  11:39
 */
public class ValidationUtil {
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();

    public static<T> void validate(T o){
        Set<ConstraintViolation<T>> validate = validator.validate(o, UpdateGroup.class, Default.class);
        if (validate.size()>0){
            for (ConstraintViolation<T> tConstraintViolation : validate) {
                System.out.println(tConstraintViolation.getMessage());
                System.out.println(tConstraintViolation.getPropertyPath());

            }
        }else {
            System.out.println("obj is ok");
        }
    }

    public static void main(String[] args) {
        User user=new User();
        user.setAge(11);
        user.setEmail("sdsd");
        validate(user);

    }

}
