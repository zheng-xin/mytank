package com.zhengxin.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyFrame my = new MyFrame();
        while (true) {
            my.repaint();
            Thread.sleep(25);
        }
    }
}
