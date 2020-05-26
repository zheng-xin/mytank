package com.zhengxin.tank;

import com.zhengxin.tank.chainofresponsibility.Collider;

import java.awt.*;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/26 - 05 - 26 - 18:39
 * @Description: com.zhengxin.tank
 * @version: 1.0
 */
public class Wall extends GameObject{
    public Wall(int x,int y,int w,int h){
        this.x = x;
        this.y = y;
        this.with = w;
        this.high = h;
        this.rect.x = x;
        this.rect.y = y;
        this.rect.width = w;
        this.rect.height = h;
    }
    @Override
    public void paint(Graphics g) {
        Color c =g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(this.x,this.y,this.with,this.high);
        g.setColor(c);
    }
}
