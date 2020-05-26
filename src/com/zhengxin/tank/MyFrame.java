package com.zhengxin.tank;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MyFrame extends Frame {
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = PropertyMgr.getInstance().getIntProp(PropetryKeys.GAMEWIDTH);
    public static final int  HIGH = PropertyMgr.getInstance().getIntProp(PropetryKeys.GAMEHIGH);

    public MyFrame() {
        init();
    }

    private void init() {
        this.setSize(WIDTH, HIGH);
        this.setResizable(false);
        this.setTitle("tank war");
        this.setVisible(true);
        this.setBackground(Color.black);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new MyListener());
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(WIDTH, HIGH);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, WIDTH, HIGH);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
       GameModel.GetInstance().paint(g);
    }

    class MyListener extends KeyAdapter {
        boolean bl = false;
        boolean br = false;
        boolean bu = false;
        boolean bd = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bl = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = true;
                    break;
                case KeyEvent.VK_UP:
                    bu = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = true;
                default:
                    break;
            }
            setMainTankDir();
            if (bl || br || bu || bd)
                GameModel.GetInstance().getMainTank().setIsMoving(true);
//            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bl = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = false;
                    break;
                case KeyEvent.VK_UP:
                    bu = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    GameModel.GetInstance().getMainTank().fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
            if (!bl && !br && !bu && !bd)
                GameModel.GetInstance().getMainTank().setIsMoving(false);
        }

        private void setMainTankDir() {
            if (bl) GameModel.GetInstance().getMainTank().setDir(Dir.LEFT);
            if (br) GameModel.GetInstance().getMainTank().setDir(Dir.RIGHT);
            if (bu) GameModel.GetInstance().getMainTank().setDir(Dir.UP);
            if (bd) GameModel.GetInstance().getMainTank().setDir(Dir.DOWN);
        }

    }


}
