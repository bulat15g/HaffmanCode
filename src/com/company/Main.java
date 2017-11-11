package com.company;

import java.io.IOException;

public class Main {
    static double criticalChanse=0.005;
    static int blockLenMinusOne=2;
    static boolean uniCodes=false;
    static boolean hasErrors=false;

    public static void main(String[] args) throws IOException {

        String text=Source.getFileText("abvc");
        Union union=new Union(text);
        union.decodeProcess();

    }
}
