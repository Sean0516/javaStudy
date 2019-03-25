package common.moduel;

import com.alibaba.fastjson.JSON;

/**
 * Created by Sean on 2019/3/22
 *
 * @author Sean
 */
public class UserBo {
    private String name;
    private String sex;

    public UserBo(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
