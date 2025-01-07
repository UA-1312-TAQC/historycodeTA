package com.historycode;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestValueProvider {
    Properties properties;

    public TestValueProvider() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException err) {
            System.out.println(err.getMessage());
            System.out.println("Use system env");
        }
    }

    public String getBaseUIUrl(){
        return  properties != null ? properties.getProperty("base.ui.url") : System.getenv("BASE_UI_URL");
    }
    public int getImplicitlyWait(){
        return  properties != null ? Integer.parseInt(properties.getProperty("implicitlyWait")) : Integer.parseInt(System.getenv("IMPLICITLY_WAIT"));
    }
    public String getAdminEmail(){
        return  properties != null ? properties.getProperty("admin.email") : System.getenv("ADMIN_EMAIL");
    }
    public String getAdminPass(){
        return  properties != null ? properties.getProperty("admin.pass") : System.getenv("ADMIN_PASS");
    }
}
