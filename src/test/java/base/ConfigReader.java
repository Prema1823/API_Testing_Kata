package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static FileInputStream inputfile;
    private static Properties props=null;
    private static final Logger LOG =  LogManager.getLogger(ConfigReader.class);

    public static String getproperty(final String key) {

        try {
            inputfile = new FileInputStream("src/test/java/base/Global.properties");
            props = new Properties();
            props.load(inputfile);
        } catch (FileNotFoundException e) {
            LOG.error("Unable to load properties File ", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            inputfile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props.getProperty(key);
    }
}
