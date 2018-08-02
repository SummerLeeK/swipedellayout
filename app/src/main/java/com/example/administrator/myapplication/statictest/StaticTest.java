package com.example.administrator.myapplication.statictest;

/**
 * Created by Administrator on 2018/6/27 0027.
 */

public class StaticTest {


    public static final StaticTest instance = new StaticTest();


    private int a = 0;

    public int getA() {
        return a;
    }

    public static StaticTest getInstance() {

        return instance;
    }


    public void add() {
        a += 10;
    }

}
