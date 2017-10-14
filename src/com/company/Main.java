package com.company;

import java.io.IOException;

public class Main {
    static double criticalChanse=0.15;
    static int blockLenMinusOne=2;
    static boolean hasErrors=false;
    static CalculateTimer timer=new CalculateTimer();

    public static void main(String[] args) throws IOException {

        String text=Source.getFileText("abvc");
        Huffman huffman =new Huffman(text);


        Output output=new Output(huffman.getTable(), huffman.getMainText());
        Decoder decoder=new Decoder(output.getByteTable(),output.getByteText());
        timer.showTime();
        Stat.countSize(text,output.getByteTable(),output.getByteText(), huffman.getStatics());


    }

}
