package ru.job4j.io;

import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte num1 = 1;
        short num2 = 2;
        int num3 = 3;
        long num4 = 4L;
        float num5 = 5.0F;
        double num6 = 6.0D;
        boolean b7 = true;
        char ch8 = 'P';
        LOG.debug("Args num1 = {}, num2 = {}, num3 = {}, num4 = {}, num5 = {}, num6 = {}, b7 = {}, ch8 = {} ",num1 , num2, num3, num4, num5, num6, b7, ch8);
    }
}