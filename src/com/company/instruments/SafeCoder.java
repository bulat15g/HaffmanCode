package com.company.instruments;

import com.company.Stat;

import java.io.IOException;

public class SafeCoder {
    int [][] matrix;
    int m=4,n=7;
    StringBuilder safeBinaryText,safeBinaryTable;

    public StringBuilder getSafeBinaryText() {
        return safeBinaryText;
    }

    public StringBuilder getSafeBinaryTable() {
        return safeBinaryTable;
    }

    public SafeCoder(byte[] byteTable, byte[] byteText) throws IOException {
        setMatrix();

        StringBuilder binaryTable= new StringBuilder();
        for (int i = 0; i < byteTable.length; i++) {
            for(int j=0;j<=7;j++) binaryTable.append (((byteTable[i] & (byte)(128 / Math.pow(2, j))) != 0)?1:0);
        }

        StringBuilder binaryText= new StringBuilder();
        for (int i = 0; i < byteText.length; i++) {
            for(int j=0;j<=7;j++) binaryText.append (((byteText[i] & (byte)(128 / Math.pow(2, j))) != 0)?1:0);
        }

        StringBuilder safeBinaryTable= new StringBuilder();
        StringBuilder safeBinaryText= new StringBuilder();

        String block;
        for (int i = 0; i < binaryTable.length()/4+(binaryTable.length()%4!=0?1:0); i++) {
            StringBuilder addition=new StringBuilder();
            block=binaryTable.substring(i*4,(i+1)*4);
            safeBinaryTable.append(block);

            int result=0;
            for (int j = m; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    result+=matrix[k][j]*(Character.getNumericValue(block.charAt(k)));
                }
                result=result%2;
                addition.append(result);
            }
            safeBinaryTable.append(addition);
        }
        for (int i = 0; i < binaryText.length()/4+(binaryText.length()%4!=0?1:0); i++) {
            StringBuilder addition=new StringBuilder();
            block=binaryText.substring(i*4,(i+1)*4);
            safeBinaryText.append(block);

            for (int j = m; j < n; j++) {
                int result=0;
                for (int k = 0; k < m; k++) {
                    result+=matrix[k][j]*(Character.getNumericValue(block.charAt(k)));
                }
                result=result%2;
                addition.append(result);
            }
            safeBinaryText.append(addition);
        }
        this.safeBinaryTable=safeBinaryTable;
        this.safeBinaryText=safeBinaryText;
        Stat.writeToLog("safe coder is changed binary text code:=");
        Stat.writeToLog(">>"+safeBinaryText);
        Stat.writeToLog("end of safeCoder");




    }

    private void setMatrix(){
        matrix=new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (i==j)matrix[i][j]=1;
                else matrix[i][j]=0;
            }
            for (int j = m; j < n; j++) {
                if ((m-i-1)==(j-m))matrix[i][j]=0;
                else matrix[i][j]=1;
            }
        }
    }

    public void shovMatrix() throws IOException {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Stat.writeToLog(matrix[i][j]+" ");
            }
            Stat.writeToLog("");
        }
    }

}
