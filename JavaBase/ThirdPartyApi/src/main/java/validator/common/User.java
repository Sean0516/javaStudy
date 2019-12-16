package validator.common;

import lombok.Data;
import validator.common.group.Gender;
import validator.common.group.UpdateGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author sean
 * @date 2019/12/5  11:34
 */
@Data
public class User {
    @NotEmpty(message = "id 不能为空",groups = UpdateGroup.class)
    private String id;
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]\\d{8}$", message = "手机号格式不正确")
    private String phone;
    @Gender
    private String sex;
    @Email
    private String email;
    @Min(value = 18,message = "用户年龄小于18，未成年")
    private int age;

}
