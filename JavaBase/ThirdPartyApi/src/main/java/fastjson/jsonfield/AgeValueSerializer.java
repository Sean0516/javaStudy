package fastjson.jsonfield;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import org.omg.CORBA.Object;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by Sean on 2019/3/13
 *
 * @author Sean
 */
public class AgeValueSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, java.lang.Object o, java.lang.Object o1, Type type, int i) throws IOException {
        Integer age= (Integer) o;
        String str=age +" 岁";
        jsonSerializer.write(str);
    }
}
