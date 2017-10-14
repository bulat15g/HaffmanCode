package com.company;

import javax.swing.*;
import java.awt.event.ActionListener;


public class CalculateTimer {
    Integer integer=0;
    Timer timer;

    CalculateTimer() {
        timer=new Timer(1000, (ActionListener) e -> integer++);
        timer.start();
    }

    void showTime(){
        System.out.println("current Time is:="+integer);
    }

}
