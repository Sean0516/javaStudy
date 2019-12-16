package apache.configuration2;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.ConfigurationBuilder;
import org.apache.commons.configuration2.builder.ConfigurationBuilderEvent;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.event.EventListener;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

/**
 * @author sean
 * @date 2019/12/13  16:41
 */
public class ConfigUtil {
    private Configurations configurations=new Configurations();
    public   void propertiesRead(String fileName) throws ConfigurationException {
//        创建 builder
        FileBasedConfigurationBuilder<PropertiesConfiguration> builder = configurations.propertiesBuilder(fileName);
        PropertiesConfiguration properties = builder.getConfiguration();
//       获取值
        String value = properties.getString("key", "default");
//        设置值
        properties.setProperty("key","value");
//        保存值
        builder.save();

    }
    public void  xmlRead(String xmlName) throws ConfigurationException {

        FileBasedConfigurationBuilder<XMLConfiguration> xmlConfigurationFileBasedConfigurationBuilder = configurations.xmlBuilder(xmlName);
        xmlConfigurationFileBasedConfigurationBuilder.setAutoSave(true);
        XMLConfiguration xml =xmlConfigurationFileBasedConfigurationBuilder.getConfiguration();
        xml.getString("root.element","default");
        xml.getString("root.element[name]","default");
        xml.setProperty("key","value");
        xml.addProperty("key","value");
        xmlConfigurationFileBasedConfigurationBuilder.save();

    }
    public void reloadFile(String filePath){
        Parameters params = new Parameters();
        ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration> reloadingFileBasedConfigurationBuilder=new ReloadingFileBasedConfigurationBuilder<>(PropertiesConfiguration.class).configure(params.fileBased().setFile(new File(filePath)));
        reloadingFileBasedConfigurationBuilder.addEventListener(ConfigurationBuilderEvent.CONFIGURATION_REQUEST, new EventListener<ConfigurationBuilderEvent>() {
            @Override
            public void onEvent(ConfigurationBuilderEvent configurationBuilderEvent) {
                ConfigurationBuilder<PropertiesConfiguration> source = (ConfigurationBuilder<PropertiesConfiguration>) configurationBuilderEvent.getSource();
                try {
                    PropertiesConfiguration configuration = source.getConfiguration();
                    reloadingFileBasedConfigurationBuilder.getReloadingController().checkForReloading(null);
                } catch (ConfigurationException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
