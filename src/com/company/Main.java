package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String text=Source.getFileText("abvc");
        Haffman haffman=new Haffman(text);

        Output output=new Output(haffman.getTable(),haffman.getMainText());
        Decoder decoder=new Decoder(output.getByteTable(),output.getByteText());
        decoder.decodeTable();
        decoder.finalStep();

        Stat.countSize(text,output.getByteTable(),output.getByteText(),haffman.getStatics());

    }


}
