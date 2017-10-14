package com.company;

import java.util.HashMap;

public class Stat {

    public static void countSize(String incomingText,byte[] byteTable, byte[] byteText,
                                 HashMap<Character,Integer> statics){

        System.out.println("real size:=1(byte)*lenght="+incomingText.length());
        System.out.println("coded Text size:="+byteText.length+"   Table size:="+byteTable.length);

        double idMemory=0;
        double chanse;
        for (char a : statics.keySet()) {
            chanse=(double)statics.get(a)/incomingText.length();
            idMemory+=chanse*(Math.log(chanse)/Math.log(2.0));
        }
        idMemory*=-(incomingText.length()/8);
        System.out.println("ideal memory=:"+idMemory);
    }
}
