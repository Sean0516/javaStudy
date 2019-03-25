package guava.optional;

import com.google.common.collect.ComparisonChain;
import common.moduel.User;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Sean on 2019/3/21
 *
 * @author Sean
 */
public class OptionalTest {
    public static void main(String[] args) {
        User user = null;

//        返回一个指定非null值的Optional ，若为空，则抛异常
//        Optional.of(user);

//        如果为非空，返回 Optional 描述的指定值，否则返回空的 Optional
        Optional<User> user1 = Optional.ofNullable(user);
//        如果值存在则方法会返回true，否则返回 false
        boolean present = user1.isPresent();
//如果存在该值，返回值， 否则返回 other
//        user1.orElse(new User("22", 22, "ss"));

//        如果存在该值，返回值， 否则触发 other，并返回 other 调用的结果
        User user2 = user1.orElseGet(() -> {
            return null;
        });
    }

}
