package com.roden.java.代码片;

import java.text.Collator;
import java.util.Arrays;

public class 中文排序 {

    public static void main(String[] args) {
        Collator cmp = Collator.getInstance(java.util.Locale.CHINA);

        String[] arr = { "张三", "李四", "王五", "刘六" };
        Arrays.sort(arr, cmp);
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);

    }

}
