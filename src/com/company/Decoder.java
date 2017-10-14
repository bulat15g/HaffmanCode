package com.company;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Decoder {
    private byte []byteTable,byteText;
    private HashMap<Character,String> table;
    private String message="";

    void decodeTable() {
        ByteArrayInputStream bis = new ByteArrayInputStream(byteTable);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            table = (HashMap<Character, String>) in.readObject();
            in.close();
        }
        catch (IOException | ClassNotFoundException ignored){}

        for (Character a:table.keySet()) {
            System.out.print(a+"-->"+table.get(a)+"|| ||");
        }
        System.out.println("on Decoder");
    }

    private void decodeString(StringBuilder input){
        int iterationOnTheEnd=0;
        while(input.length()!=0){//пока еще есть код
            for (char a:table.keySet()) {//для всей таблицы
                String tableA=table.get(a);

                boolean match=true;
                boolean allow=tableA.length()<=input.length();
                for (int i = 0; i < tableA.length(); i++) {//проверка-первых Н букв соотвествия
                    if (allow&&(tableA.charAt(i)!=input.charAt(i)))
                    {match= false;break;}
                }
                if(match&&allow){
//                    System.out.println("before delete"+binaryText+"    a:="+tableA);
//                    System.out.println("after delete"+binaryText);
                    message+=a;
                    for (int i = 0; i < tableA.length(); i++)input.deleteCharAt(0);
                    break;
                }
            }
            if(input.length()<7){
                iterationOnTheEnd++;
                if (iterationOnTheEnd>10)break;
            }

        }
        System.out.println("\n"+message);

        if(iterationOnTheEnd>10)System.out.println("\n сycle breaked!!");

    }

    private StringBuilder decodeToString(){
        StringBuilder binaryText= new StringBuilder();
        for (int i = 0; i < byteText.length; i++) {
            for(int j=0;j<=7;j++) binaryText.append (((byteText[i] & (byte)(128 / Math.pow(2, j))) != 0)?1:0);
        }
//
//        byte [] pos = new byte[]{(byte)0x80, 0x40, 0x20, 0x10, 0x8, 0x4, 0x2, 0x1};
//
//        for(int i = 0; i < byteText.length; i++){
//            for(int j = i * 8, k = 0; k < 8; j++, k++){
//                binaryText.append(((byteText[i] & pos[k]) != 0)?1:0);
//            }
//        }

        System.out.println("\n"+binaryText);
        return binaryText;
    }

    void finalStep(){
        decodeString(decodeToString());
    }

    Decoder(byte[] byteTable, byte[] byteText) {
        this.byteTable = byteTable;
        this.byteText = byteText;
    }

}


