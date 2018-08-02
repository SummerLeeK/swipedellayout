package com.fudaoculture.lee.lib;

import java.util.BitSet;
import java.util.Hashtable;

public class Al {

    public static void main(String[] args) {
        int i = 166;
        byte b = 127;

        byte c = (byte) i;

        System.out.println(c);
        System.out.println(Runtime.getRuntime().freeMemory());

        System.out.println(Runtime.getRuntime().availableProcessors());

        System.out.println(Runtime.getRuntime().maxMemory());

        System.out.println(Runtime.getRuntime().totalMemory());
    }
}