package com.zhengxin.tank.chainofresponsibility;

import com.zhengxin.tank.GameObject;
import com.zhengxin.tank.Tank;
import com.zhengxin.tank.Wall;

import java.awt.*;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/26 - 05 - 26 - 18:52
 * @Description: com.zhengxin.tank.chainofresponsibility
 * @version: 1.0
 */
public class TankAndWallCollede implements Collider{
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;
            dealTankAndWallCollide(t,w);

        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            Tank t = (Tank) o2;
            Wall w = (Wall) o1;
            dealTankAndWallCollide(t,w);
        } else {
            return;
        }
    }
    private void dealTankAndWallCollide(Tank t,Wall w){
        Rectangle rect1 = t.getRectangle();
        Rectangle rect2 = w.getRectangle();
        if (rect1.intersects(rect2)) {
            t.returnLastPosion();
        }
    }
}
