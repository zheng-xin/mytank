package com.zhengxin.tank;

import com.zhengxin.tank.net.client.Client;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        MyFrame my = new MyFrame();
        new Thread(()->{
            while (true) {
                my.repaint();
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();



        Client.INSTANCE.connect();
    }
}
