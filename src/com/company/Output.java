package com.company;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Random;

import static com.company.Main.blockLenMinusOne;

public class Output {
    HashMap<String,String>table;
    String source;
    private byte []byteTable,byteText;

    public Output(HashMap<String, String> table, String source) {
        this.table = table;
        this.source = source;
        byteTable= tableToDecoder(table);

        if (Main.hasErrors)makeError();
        
        byteText=stringToDecoder(table,source);
    }

    public byte[] tableToDecoder(HashMap<String,String> table){
        byte[] outBytes = new byte[0];

        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ObjectOutput output=null;
        try {
            output=new ObjectOutputStream(bos);
            output.writeObject(table);
            output.flush();
            outBytes =bos.toByteArray();
            bos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return outBytes;
    }

    public byte[] stringToDecoder(HashMap<String,String> table, String source){
        StringBuilder readyString= new StringBuilder();

        for (int i = 0; i < source.length()/blockLenMinusOne; i++) {
            readyString.append(table.get(source.substring(blockLenMinusOne*i,blockLenMinusOne*(i+1))));
        }
        readyString.append(table.get(Huffman.tail));

        System.out.println("now coded bin is preparing");

        if(readyString.length()<1000)System.out.println(readyString+"\n");
        else System.out.println("bin too long");
        byte[] forRet=new byte[readyString.length()/8+(readyString.length()%8!=0?1:0)];

        for (int i = 0; i < readyString.length(); i++) {
            forRet[i/8]+=(((byte)readyString.charAt(i)=='1'?1:0)<<(7-(i%8)));
        }
        return forRet;
    }

    private void makeError() {

        Random random = new Random();

        char[] characters=source.toCharArray();

        for (int i = 0; i < source.length(); i++) {
            if(Math.abs(random.nextDouble()-0.5)<Main.criticalChanse){

                if (characters[i]=='0')characters[i]='1';
                else characters[i]='0';
            }
        }

        source=String.copyValueOf(characters);
    }

    public byte[] getByteTable() {
        return byteTable;
    }

    public byte[] getByteText() {
        return byteText;
    }

}
