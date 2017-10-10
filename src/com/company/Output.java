package com.company;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Random;

public class Output {
    HashMap<Character,String>table;
    String source;
    private byte []byteTable,byteText;

    public Output(HashMap<Character, String> table, String source) {
        this.table = table;
        this.source = source;
        byteTable= tableToDecoder(table);
        byteText=stringToDecoder(table,source);
    }

    public byte[] tableToDecoder(HashMap<Character,String> table){
        byte[] outBytes = new byte[0];
        String sequince;

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

    public byte[] stringToDecoder(HashMap<Character,String> table, String source){
        StringBuilder readyString= new StringBuilder();

        for (int i = 0; i < source.length(); i++) {
            readyString.append(table.get(source.charAt(i)));
        }

        System.out.println("now coded bin is preparing");
        System.out.println(readyString+"\n");

        byte[] forRet=new byte[readyString.length()/8+(readyString.length()%8!=0?1:0)];

        for (int i = 0; i < readyString.length(); i++) {
            forRet[i/8]+=(((byte)readyString.charAt(i)=='1'?1:0)<<(7-(i%8)));
        }
        return forRet;
    }

    public void makeError(){
        Random random=new Random();
        for (int i = 0; i < byteText.length; i++) {
            byteText[i]+=(random.nextBoolean()?1:-1)*(random.nextInt()%(random.nextInt()%5));
        }
    }

    public byte[] getByteTable() {
        return byteTable;
    }

    public byte[] getByteText() {
        return byteText;
    }

}
