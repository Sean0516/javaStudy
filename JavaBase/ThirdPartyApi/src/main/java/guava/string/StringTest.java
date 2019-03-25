package guava.string;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import common.moduel.UserBo;

import java.util.List;

/**
 * Created by Sean on 2019/3/22
 *
 * @author Sean
 */
public class StringTest {
    public static void main(String[] args) {
        Joiner joiner = Joiner.on(",").skipNulls();
        String[] str = {"232", "test", null, "334"};
        String join = joiner.join(str);
        UserBo[] userBos = {new UserBo("sean", "nan"), new UserBo("teest", "nv"), null};
        Joiner joiner1 = Joiner.on(",").useForNull(new UserBo("mytest", "nv").toString());
        String join1 = joiner1.join(userBos);
        List<String> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(",,ss,ss,2w,sd,,");
        System.out.println(CharMatcher.digit().replaceFrom("mima is 12345621", "*"));


    }
}
