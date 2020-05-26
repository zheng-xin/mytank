package com.zhengxin.tank.chainofresponsibility;

import com.zhengxin.tank.Bullet;
import com.zhengxin.tank.GameObject;
import com.zhengxin.tank.Wall;

import java.awt.*;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/26 - 05 - 26 - 19:06
 * @Description: com.zhengxin.tank.chainofresponsibility
 * @version: 1.0
 */
public class BulletAndWallCollide implements Collider{
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet b = (Bullet) o1;
            Wall w = (Wall) o2;
            dealBulletAndWallCollide(b,w);
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            Bullet b = (Bullet) o2;
            Wall w = (Wall) o1;
            dealBulletAndWallCollide(b,w);
        } else {
            return;
        }
    }
    private void dealBulletAndWallCollide(Bullet b,Wall w) {
        Rectangle rect1 = b.getRectangle();
        Rectangle rect2 = w.getRectangle();
        if (rect1.intersects(rect2)) {
            b.die();
            b.explode();
        }
    }
}
