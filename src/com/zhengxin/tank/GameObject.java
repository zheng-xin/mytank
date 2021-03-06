package com.zhengxin.tank;

import com.sun.org.apache.regexp.internal.RE;

import java.awt.*;
import java.util.UUID;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/26 - 05 - 26 - 11:08
 * @Description: com.zhengxin.tank
 * @version: 1.0
 */
public abstract class GameObject {
     protected int x;
     protected int y;
     protected int with;
     protected int high;
     Group group = Group.BAD;
     boolean living = true;
    protected UUID id;
     final Rectangle rect = new Rectangle();
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWith(){return with;};
    public int getHigh(){return high;}
    public Group getGroup(){
        return group;
    }
    public boolean isLiving(){
        return this.living;
    }
    public abstract  void paint(Graphics g);
    public Rectangle getRectangle() {
        this.rect.x = x;
        this.rect.y = y;
        return this.rect;
    }
    public void die() {
        this.living = false;
    }
    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
