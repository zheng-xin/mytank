package com.zhengxin.tank.strategy;

import com.zhengxin.tank.Bullet;
import com.zhengxin.tank.GameModel;
import com.zhengxin.tank.Tank;

import java.io.Serializable;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/25 - 05 - 25 - 8:54
 * @Description: com.zhengxin.tank
 * @version: 1.0
 */
public class DefaultFireStrategy implements FireStrategy, Serializable {
    @Override
    public void fire(Tank t) {
        int bx = t.getX() + Tank.WITH / 2 - Bullet.WITH / 2;
        int by = t.getY() + Tank.HIGH / 2 - Bullet.HIGH / 2;
        GameModel.GetInstance().getObjects().add(new Bullet(bx, by, t.getDir(), t.getGroup()));
    }

    //    private DefaultFireStrategy(){}
    public DefaultFireStrategy() {
    }

    private static volatile DefaultFireStrategy defaultFireStrategy = null;

    public static DefaultFireStrategy getInstance() {
        if (defaultFireStrategy == null) {
            synchronized (DefaultFireStrategy.class) {
                if (defaultFireStrategy == null) {
                    defaultFireStrategy = new DefaultFireStrategy();
                }
            }
        }
        return defaultFireStrategy;
    }
}
