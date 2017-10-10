package com.company;

import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;

public class Haffman {
    private String fromsource;
    private HashMap <Character,Integer> statics =new HashMap<>();
    private ArrayList<Node> tree=new ArrayList<>();
    private HashMap<Character,String> table=new HashMap<>();

    //get data and fill tree,and table
    Haffman(String data){
        fromsource=data;
        countStatictics();
        createAndCompBinaryTree();

        while (tree.size()>1){
            tree.sort(Comparator.comparingInt(o -> o.cost));
            unionObjects();
        }

        treeToBin(tree.get(0),"",'~');

        System.out.println("word:=  "+fromsource+"\n");
        for (Character a:table.keySet()) {
            System.out.print(a+"-->"+table.get(a)+"|| ||");
        }
        System.out.println("on coder");
        System.out.println("");
    }

    private void createAndCompBinaryTree(){
        for (Character c:statics.keySet()) {
            tree.add(new Node(c,statics.get(c)));
        }
        tree.sort(Comparator.comparingInt(o -> o.cost));
    }

    private void unionObjects(){
        if(tree.size()>1){
            tree.add(new Node('~',tree.get(0).cost+tree.get(1).cost,tree.get(0),tree.get(1)));
            tree.remove(1);tree.remove(0);
        }
    }

    private void treeToBin(Node local,String way,Character a){
        if (local.getLeft()==null&&local.getRight()==null)return;

        if (local.getLeft()!=null){
            if (local.getLeft().Letter != '~') table.put(local.getLeft().Letter, way + "0");
            else treeToBin(local.getLeft(),way+"0",'~');
        }

        if (local.getRight()!=null){
            if (local.getRight().Letter != '~') table.put(local.getRight().Letter, way + "1");
            else treeToBin(local.getRight(),way+"1",'~');
        }
    }

    private void countStatictics(){
        for (int i = 0; i < fromsource.length(); i++) {
            char Ai=fromsource.charAt(i);
            if(statics.containsKey(Ai)){
                statics.put(Ai,statics.get(Ai)+1);
            }
            else{
                statics.put(Ai,1);
            }
        }
    }

    public void showStat(){
        for (char a:statics.keySet()){
            System.out.print(a+"   "+statics.get(a)+" || ");
        }
        System.out.println();
    }

    public void showTree(){
        for (int i = 0; i < tree.size(); i++) {
            System.out.print(tree.get(i).Letter+"  "+tree.get(i).cost+" ||");
        }
        System.out.println();
    }

    public HashMap<Character, String> getTable() {
        return table;
    }

    public String getMainText() {
        return fromsource;
    }

    public long getSizeOfText(){
        return fromsource.length();
    }
}