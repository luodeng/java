package com.roden.java.util.regex;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 一：抓取网页中的Email地址

利用正则表达式匹配网页中的文本

[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+

将网页内容分割提取
 */
public class EmailSpider {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\emailSpider.html"));
            String line = "";
            while((line=br.readLine()) != null) {
                parse(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parse(String line) {
        Pattern p = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");
        Matcher m = p.matcher(line);
        while(m.find()) {
            System.out.println(m.group());
        }
    }

}