package common.moduel;

import lombok.Data;

/**
 * Created by Sean on 2019/3/22
 *
 * @author Sean
 */
@Data
public class UserBo {
    private String name;
    private String sex;

    public UserBo(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }
}
