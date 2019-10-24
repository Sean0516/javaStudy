package oshi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

/**
 * @author sean
 * @date 2019/10/10  15:31
 */
public class Test {


    public static void main(String[] args) {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        GlobalMemory memory = hardware.getMemory();
        CentralProcessor processor = hardware.getProcessor();
        System.out.println(processor.getName());

        long[] systemCpuLoadTicks = processor.getSystemCpuLoadTicks();
        double systemCpuLoadBetweenTicks = processor.getSystemCpuLoadBetweenTicks(systemCpuLoadTicks);
        double v = systemCpuLoadBetweenTicks * 100;
        System.out.println(Math.rint(v));
//
//
//        System.out.println(FormatUtil.formatBytes(used));
//        System.out.println("-------------");
//        System.out.println(FormatUtil.formatBytes(memory.getTotal()));
//
//        HWDiskStore[] diskStores = hardware.getDiskStores();
//        for (HWDiskStore diskStore : diskStores) {
//            System.out.println(FormatUtil.formatBytesDecimal(diskStore.getSize()));
//        }
//
//        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
//        FileSystem fileSystem = operatingSystem.getFileSystem();
//        OSFileStore[] fileStores = fileSystem.getFileStores();
//        for (OSFileStore fileStore : fileStores) {
//            System.out.println(fileStore.getMount());
//            System.out.println(FormatUtil.formatValue(fileStore.getUsableSpace(), "Size"));
//        }
    }
}
