package hutool.str;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HtmlUtil;

import java.io.File;
import java.nio.charset.Charset;

/**
 * @author sean
 * @date 2019/12/16  16:45
 */
public class StrTest {
    public static void main(String[] args) {
//        StrUtil.addPrefixIfNot();
//        StrUtil.addSuffixIfNot();
//        StrUtil.removePrefix();
//        StrUtil.removeSuffix();
//        StrUtil.sub();
//        StrUtil.bytes()
        String format = StrUtil.format("String {} to server {}", "hello", "sean");
        System.out.println(format);
//        StrUtil.str(new byte[], Charset.defaultCharset());
        byte[] bytes = DigestUtil.sha256(new File("D:\\test.txt"));

    }
}
