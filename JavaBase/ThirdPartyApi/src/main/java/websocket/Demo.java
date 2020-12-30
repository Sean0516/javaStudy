package websocket;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;

/**
 * @Description Demo
 * @Author Sean
 * @Date 2020/11/17 11:43
 * @Version 1.0
 */
public class Demo {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws ParseException {
        String str="F:\\media\\000\\20201117\\000".replaceAll("\\\\", "/");
        System.out.println(str);

    }

}
