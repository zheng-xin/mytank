package com.zhengxin.tank.decorator;

import com.zhengxin.tank.GameObject;

import java.awt.*;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/27 - 05 - 27 - 7:55
 * @Description: com.zhengxin.tank.decorator
 * @version: 1.0
 */
public class TailDecorator extends GoDecorator{
    public TailDecorator(GameObject go) {
        super(go);
    }
    @Override
    public void paint(Graphics g) {
        this.x = go.getX();
        this.y = go.getY();
        super.paint(g);
        Color c = g.getColor();
        g.setColor(Color.BLUE);
        g.drawLine(go.getX(),go.getY(),getWith(),getHigh());
        g.setColor(c);
    }
}
