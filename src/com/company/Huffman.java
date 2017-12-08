package com.company;

import com.company.instruments.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import static com.company.Main.blockLen;


public class Huffman {
    private String fromsource;
    private HashMap <String,Integer> statics =new HashMap<>();
    private ArrayList<Node> tree=new ArrayList<>();
    private HashMap<String,String> table=new HashMap<>();
    public static String tail;

    //get data and fill tree,and table
    Huffman(String data) throws IOException {
        fromsource=data;
        fromsource=fromsource.toLowerCase();
        countStatictics();
        if(Main.uniCodes){
            for (String a : statics.keySet()) {
                statics.put(a,1);
            }
        }
        createAndCompBinaryTree();

        while (tree.size()>1){
            tree.sort(Comparator.comparingInt(o -> o.cost));
            unionObjects();
        }

        treeToBin(tree.get(0),"","~");


        if(fromsource.length()<1000)Stat.writeToLog("word:=  "+fromsource+"\n");
        else Stat.writeToLog("incomint text too long");
        for (String a:table.keySet()) {
            Stat.writeToLog(a+"-->"+table.get(a)+"|| ||");
        }
        Stat.writeToLog("on coder");
        Stat.writeToLog("\n.\n");
    }

    private void createAndCompBinaryTree(){
        for (String c:statics.keySet()) {
            tree.add(new Node(c,statics.get(c)));
        }
        tree.sort(Comparator.comparingInt(o -> o.cost));
    }

    private void unionObjects(){
        if(tree.size()>1){
            tree.add(new Node("~",tree.get(0).cost+tree.get(1).cost,tree.get(0),tree.get(1)));
            tree.remove(1);tree.remove(0);
        }
    }

    private void treeToBin(Node local,String way,String a){
        if (local.getLeft()==null&&local.getRight()==null)return;

        if (local.getLeft()!=null){
            if (local.getLeft().Letter != "~") table.put(local.getLeft().Letter, way + "0");
            else treeToBin(local.getLeft(),way+"0","~");
        }

        if (local.getRight()!=null){
            if (local.getRight().Letter != "~") table.put(local.getRight().Letter, way + "1");
            else treeToBin(local.getRight(),way+"1","~");
        }
    }

    private void countStatictics(){
        for (int i = 0; i < fromsource.length()/ blockLen; i++) {
            String Ai=fromsource.substring(blockLen *i, blockLen *(i+1));
            if(statics.containsKey(Ai)){
                statics.put(Ai,statics.get(Ai)+1);
            }
            else{
                statics.put(Ai,1);
            }
        }

        tail=fromsource.substring(
                (fromsource.length()/ blockLen)* blockLen,fromsource.length())+"__tail__";

        //tail addition
        if(statics.containsKey(tail)){
            statics.put(tail,statics.get(tail)+1);
        }
        else{
            statics.put(tail,1);
        }

    }

    public void showStat() throws IOException {
        for (String a:statics.keySet()){
            Stat.writeToLog(a+"   "+statics.get(a)+" || ");
        }
        Stat.writeToLog("\n.\n");
    }

    public void showTree() throws IOException {
        for (int i = 0; i < tree.size(); i++) {
            Stat.writeToLog(tree.get(i).Letter+"  "+tree.get(i).cost+" ||");
        }
        Stat.writeToLog("\n.\n");
    }

    HashMap<String, String> getTable() {
        return table;
    }

    String getMainText() {
        return fromsource;
    }

    HashMap<String, Integer> getStatics() {
        return statics;
    }
}