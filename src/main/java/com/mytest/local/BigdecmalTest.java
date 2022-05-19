package com.mytest.local;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class BigdecmalTest {

    public static void main(String[] args) {

        double dayDiff2 = 5.06135778;
        BigDecimal usedM = new BigDecimal(dayDiff2);
        //保留两位小数且四舍五入
        usedM = usedM.setScale(2, BigDecimal.ROUND_HALF_UP);

        System.out.println(String.valueOf(usedM));

        AtomicInteger test1 = new AtomicInteger(0);
        System.out.println(test1.addAndGet(1));
        System.out.println(test1.addAndGet(1));

    }
}
