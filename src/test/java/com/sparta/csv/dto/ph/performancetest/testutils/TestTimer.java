package com.sparta.csv.dto.ph.performancetest.testutils;

public class TestTimer {
    private static long start;
    public static void start(){ start=System.nanoTime();}
    public static float end(){return (System.nanoTime()-start)/1000000000f;}
}
