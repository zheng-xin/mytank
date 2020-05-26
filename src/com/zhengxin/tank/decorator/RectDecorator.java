package com.zhengxin.tank.decorator;

import com.zhengxin.tank.GameObject;

import java.awt.*;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/26 - 05 - 26 - 21:02
 * @Description: com.zhengxin.tank.decorator
 * @version: 1.0
 */
public class RectDecorator extends GoDecorator{
    public RectDecorator(GameObject go) {
        super(go);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Color c = g.getColor();
        g.setColor(Color.yellow);
        g.drawRect(go.getX(),go.getY(),go.getWith(),go.getHigh());
        g.setColor(c);
    }

}
