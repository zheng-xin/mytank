package com.zhengxin.tank.chainofresponsibility;

import com.zhengxin.tank.Bullet;
import com.zhengxin.tank.GameObject;
import com.zhengxin.tank.Tank;

import java.awt.*;
import java.lang.annotation.ElementType;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/26 - 05 - 26 - 13:23
 * @Description: com.zhengxin.tank.chainofresponsibility
 * @version: 1.0
 */
public class BulletAndTankCollide implements Collider{
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
               Tank t = (Tank) o2;
               Bullet b = (Bullet) o1;
                if (t.getGroup() == b.getGroup())
                    return;
            dealTankAndBulletCollid(t,b);
        } else if (o1 instanceof Tank && o2 instanceof Bullet){
            Tank t = (Tank) o1;
            Bullet b = (Bullet) o2;
            if (t.getGroup() == b.getGroup())
                return;
            dealTankAndBulletCollid(t,b);
        } else {
            return;
        }
    }
    private void dealTankAndBulletCollid(Tank t,Bullet b){
        Rectangle rect1 = t.getRectangle();
        Rectangle rect2 = b.getRectangle();
        if (rect1.intersects(rect2)) {
            t.die();
            b.die();
            t.explode();
        }
    }
}
