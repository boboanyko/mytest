package com.mytest.local;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StreamTest {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(null);
        int sum = list.stream().mapToInt(p -> (int) p).sum();
        System.out.println(sum);
    }
}
