package ru.pariy.webmodule.utils;

import ru.pariy.webmodule.Bootstrap;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
    private static Properties properties;

    public static String getPropertiesPath() throws IOException {
        return Bootstrap.class.getClassLoader().getResource("config.properties").getPath();
    }

    public static int getPropertiesPort(String rootPath) throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(rootPath));
        String portValue = properties.getProperty("http.port");
        return Integer.parseInt(portValue);
    }

    public static String getPropertiesDestAddr(String rootPath) throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(rootPath));
        return properties.getProperty("tcp.dest.addr");
    }

    public static int getPropertiesDestPort(String rootPath) throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(rootPath));
        String destPort = properties.getProperty("tcp.dest.port");
        return Integer.parseInt(destPort);
    }
}
