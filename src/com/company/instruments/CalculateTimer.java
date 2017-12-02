package com.company.instruments;

import javax.swing.*;
import java.awt.event.ActionListener;


public class CalculateTimer {
    Integer integer=0;
    public Timer timer;

    public CalculateTimer() {
        timer=new Timer(100, (ActionListener) e -> integer++);
        timer.start();
    }

    public void showTime(){
        System.out.println("current Time is:="+integer+"100 ms");
    }

}
