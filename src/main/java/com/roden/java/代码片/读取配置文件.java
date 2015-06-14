package com.roden.java.代码片;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class 读取配置文件 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Properties p = new Properties();
        p.load(new FileInputStream(new File("src/main/java/log4j.properties")));
        System.out.println(p.getProperty("log4j.rootLogger"));
        
        ResourceBundle rb1 = ResourceBundle.getBundle("log4j",Locale.getDefault(Locale.Category.FORMAT));
        System.out.println(MessageFormat.format(rb1.getString("log4j.appender.f.File"),"roden",new Date()));
        

        ResourceBundle rb2 = PropertyResourceBundle.getBundle("message",Locale.getDefault(Locale.Category.FORMAT));
        System.out.println(MessageFormat.format(rb2.getString("msg"),"roden",DateFormat.getDateInstance(DateFormat.LONG,Locale.CHINA).format(new Date())));
        
        ResourceBundle rb3 = PropertyResourceBundle.getBundle("message",Locale.US);
        System.out.println(MessageFormat.format(rb3.getString("msg"),"roden",DateFormat.getDateInstance(DateFormat.LONG,Locale.US).format(new Date())));

    }

}
