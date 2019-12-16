package hutool.io;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sean
 * @date 2019/12/16  16:03
 */
public class IoTest {
    public static void main(String[] args) throws FileNotFoundException {
//        IoUtil.read(new FileInputStream(""),"UTF-8");
//        IoUtil.copy();
//        IoUtil.write();
//        IoUtil.getReader();
//        IoUtil.readBytes();
//        ArrayList<String> strings = IoUtil.readLines(new FileInputStream(""), Charset.defaultCharset(), Lists.newArrayList());
//        IoUtil.toStream();
//        FileUtil.appendLines();
//        FileUtil.appendString();
//        FileUtil.convertCharset();
//        FileUtil.copy();
//        String type = FileTypeUtil.getTypeByPath("");
        WatchMonitor watchMonitor=WatchMonitor.create(new File("D:\\test.txt"),WatchMonitor.ENTRY_MODIFY);
        watchMonitor.setWatcher(new SimpleWatcher() {
            @Override
            public void onModify(WatchEvent<?> watchEvent, Path path) {
                Object context = watchEvent.context();
                String string = context.toString();

                System.out.println("file " + path.toString() +string+"发生了修改");
            }

        });
        watchMonitor.setMaxDepth(1);
        watchMonitor.start();

    }
}
