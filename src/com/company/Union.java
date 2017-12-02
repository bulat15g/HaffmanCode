package com.company;

import com.company.instruments.*;

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

        SafeCoder safeCoder=new SafeCoder(output.getByteTable(),output.getByteText());

        SafeDecoder safeDecoder=new SafeDecoder(safeCoder.getSafeBinaryTable(),safeCoder.getSafeBinaryText());

        
        Decoder decoder=new Decoder(output.getByteTable(),safeDecoder.getByteText());
        timer.showTime();
        Stat.countSize(textToDecode,output.getByteTable(),output.getByteText(), huffman.getStatics());
        timer.timer.stop();

    }
}
