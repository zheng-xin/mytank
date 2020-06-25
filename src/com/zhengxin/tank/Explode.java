package com.zhengxin.tank;

import javax.print.attribute.standard.PrinterURI;
import java.awt.*;
import java.io.PipedReader;
import java.util.UUID;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/24 - 05 - 24 - 11:09
 * @Description: com.zhengxin.tank
 * @version: 1.0
 */
public class Explode extends GameObject{

    private int step = 0;
    public static int HIGH = ResourceMgr.explodes[0].getHeight();
    public static int WITH = ResourceMgr.explodes[0].getWidth();
    public Explode(int x, int y, UUID id){
        this.x = x;
        this.y = y;
        this.with = ResourceMgr.explodes[0].getWidth();
        this.high = ResourceMgr.explodes[0].getHeight();
        this.id = id;
    }
    public void paint(Graphics g) {
        if (!this.living) {
            GameModel.GetInstance().getObjects().remove(this.id);
            return;
        }
        g.drawImage(ResourceMgr.explodes[step ++],x,y,null);
        if (step >= ResourceMgr.explodes.length)
          die();
    }
}
