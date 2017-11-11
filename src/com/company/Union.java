package com.company;

import java.io.IOException;

public class Union {
    String textToDecode="";
    static CalculateTimer timer=new CalculateTimer();

    Union(String textToDecode) {
        this.textToDecode = textToDecode;
    }

    void decodeProcess() throws IOException {
        Huffman huffman =new Huffman(textToDecode);

        Output output=new Output(huffman.getTable(), huffman.getMainText());

        
        Decoder decoder=new Decoder(output.getByteTable(),output.getByteText());
        timer.showTime();
        Stat.countSize(textToDecode,output.getByteTable(),output.getByteText(), huffman.getStatics());
        timer.timer.stop();

    }
}
