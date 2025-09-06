package com.demoblaze.framework.utils;
import com.demoblaze.framework.constants.FrameworkConstants;
import java.io.InputStream;
import java.util.Properties;

public final class Config {
    private static final Properties P = new Properties();

    static {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream(FrameworkConstants.CONFIG_FILE)) {
            if (in == null) {
                throw new RuntimeException("Could not find " + FrameworkConstants.CONFIG_FILE + " on classpath");
            }
            P.load(in);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config properties", e);
        }
    }

    private Config() {}

    public static String get(String key) {
        return P.getProperty(key, "").trim();
    }
}