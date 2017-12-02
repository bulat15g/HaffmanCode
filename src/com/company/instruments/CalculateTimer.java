package com.company.instruments;

import com.company.Stat;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;


public class CalculateTimer {
    Integer integer=0;
    public Timer timer;

    public CalculateTimer() {
        timer=new Timer(100, (ActionListener) e -> integer++);
        timer.start();
    }

    public void showTime() throws IOException {
        Stat.writeToLog("current Time is:="+integer+"100 ms");
    }

}
