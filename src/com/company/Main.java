package com.company;

import java.io.IOException;

public class Main {
    public static double criticalChanse=0.005;
    public static int blockLen=1;
    public static boolean uniCodes=false;//равномерное кодирование
    public static boolean hasErrors=true;//делаь ли ошибки
    public static boolean makeCorrections=true;

    public static void main(String[] args) throws IOException {
        Stat.clearLogFile();
        String text=Source.getFileText("abvc");
        Union union=new Union(text);
        union.decodeProcess();


//        SafeCoder coder=new SafeCoder(null,null);
//        coder.shovMatrix();
    }
}
