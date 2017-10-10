package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Source {
    static File file;

    //get text from some file
    public static String getFileText(String filename){
        file=new File(filename);Scanner in = null;
        if (file.exists())
            try { in=new Scanner(file.getAbsoluteFile());}
            catch (FileNotFoundException e) {e.printStackTrace();}

        System.out.println(file.getAbsolutePath());
        String toReturn="";

        while (in.hasNext()){
            toReturn+=in.nextLine();
        }

        return toReturn;
    }

    //не смотреть
    public static void writeToFile(String filename) throws IOException {
        FileWriter fileWriter=new FileWriter(filename);
        for (int i = 0; i < 885; i++) {
            fileWriter.append("1");
        }
        for (int i = 0; i < 33; i++) {
            fileWriter.append("2");
        }
        for (int i = 0; i < 1977; i++) {
            fileWriter.append("3");
        }
        for (int i = 0; i < 848; i++) {
            fileWriter.append("4");
        }
        for (int i = 0; i < 592; i++) {
            fileWriter.append("5");
        }
        for (int i = 0; i < 2030; i++) {
            fileWriter.append("6");
        }
        for (int i = 0; i < 812; i++) {
            fileWriter.append("7");
        }
        for (int i = 0; i < 515; i++) {
            fileWriter.append("8");
        }
        for (int i = 0; i < 1740; i++) {
            fileWriter.append("9");
        }
        for (int i = 0; i < 566; i++) {
            fileWriter.append("-");
        }
        fileWriter.close();

    }


}
