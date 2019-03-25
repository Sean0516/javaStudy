package guava.array.immutablearray;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.List;

/**
 * Created by Sean on 2019/3/22
 *
 * @author Sean
 */
public class ImmutableArrayTest {
    public static final ImmutableSet<String> array = ImmutableSet.of("123", "232", "32", "3223");
    private static String[] str = {"ss", "sean", "test"};
    public static final ImmutableSet<String> array1 = ImmutableSet.copyOf(str);

    public static void main(String[] args) {
        ImmutableMap<String, String> stringStringImmutableMap = ImmutableMap.of();
        ImmutableList<String> strings = array.asList();
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
