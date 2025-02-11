package config;

import java.io.*;
import java.util.Properties;

public class configReader {

    private Properties properties = new Properties();

    public static String path = "src/main/resources/config.properties";
    public static String browser = getConfigValue("browser");
    public static String url = getConfigValue("url");


    public static String getConfigValue(String key) {
        return getValue(path, key);
    }

    public static String getValue(String path, String key) {
        FileReader reader;
        Properties prop = null;
        try {
            reader = new FileReader(path);
            prop = new Properties();
            prop.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }


}
