package com.zhengxin.tank.chainofresponsibility;

import com.zhengxin.tank.GameObject;
import com.zhengxin.tank.Tank;

import java.awt.*;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/26 - 05 - 26 - 13:55
 * @Description: com.zhengxin.tank.chainofresponsibility
 * @version: 1.0
 */
public class TankAndTankCollide implements Collider{
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            Rectangle rect1 = t1.getRectangle();
            Rectangle rect2 = t2.getRectangle();
            if (rect1.intersects(rect2)) {
                if (t1.getGroup() == t2.getGroup()) {
                    t1.returnLastPosion();
                    t2.returnLastPosion();
                }

            }

        } else {
            return;
        }
    }
}
