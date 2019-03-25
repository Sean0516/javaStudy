package guava.array.newArray;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Sean on 2019/3/22
 *
 * @author Sean
 */
public class MultimapTest {
    public static void main(String[] args) {
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
        String[] a = {"hello", "test", "ssss"};
        multimap.putAll("test", Arrays.asList(a));
        String[] a1={"master","123","345"};
        multimap.putAll("name",Arrays.asList(a1));
        System.out.println(multimap.get("name").size());
        List<String> list = multimap.get("332");
        System.out.println(list);
//        List<String> list1 = multimap.removeAll("name");
        multimap.replaceValues("test",Arrays.asList(a1));
        System.out.println(multimap);
        Collection<String> values = multimap.values();
        System.out.println(values);
    }
}
