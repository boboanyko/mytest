package com.mytest.local;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxTest {

    public static void main(String[] args) {


        System.out.println("test_J5_OBD".replaceAll("(.*)[_J5]{0,1}(.*)[_OBD]{0,1}(.*)","$1$2$3"));

        // 按指定模式在字符串查找
        String line = "test_4_5_OBD_6";
        String pattern = "(.*)(_J5)?(.*)_OBD?(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
        } else {
            System.out.println("NO MATCH");
        }
    }
}
