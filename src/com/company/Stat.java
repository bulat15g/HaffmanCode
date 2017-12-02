package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Stat {

    static String toLog="";

    public static void countSize(String incomingText,byte[] byteTable, byte[] byteText,
                                 HashMap<String,Integer> statics) throws IOException {

        toLog+="real size:=1(byte)*lenght="+incomingText.length()+"\n";
        toLog+="coded Text size:="+byteText.length+"   Table size:="+byteTable.length+"\n";

        double idMemory=0;
        double chanse;
        for (String a : statics.keySet()) {
            chanse=(double)statics.get(a)/incomingText.length();
            idMemory+=chanse*(Math.log(chanse)/Math.log(2.0));
        }
        idMemory*=-(incomingText.length()/8);
        toLog+="ideal memory=:"+idMemory+"\n";
        writeToLog(toLog);

    }

    public static void clearLogFile() throws IOException{
        File file=new File("history.log");
        file.delete();
        file.createNewFile();
    }
    public static void writeToLog(String toLog) throws IOException {
        File file=new File("history.log");
        BufferedWriter writer;
        if(!file.exists()){
            if(file.createNewFile()) System.out.println("log created!");;
        }
        if(file.canWrite()){
            writer=new BufferedWriter(new FileWriter(file,true));
            writer.append(toLog);
            writer.close();
        }
    }
}
