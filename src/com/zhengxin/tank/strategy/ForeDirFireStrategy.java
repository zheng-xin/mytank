package com.zhengxin.tank.strategy;

import com.zhengxin.tank.Bullet;
import com.zhengxin.tank.Dir;
import com.zhengxin.tank.GameModel;
import com.zhengxin.tank.Tank;
import com.zhengxin.tank.decorator.RectDecorator;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/25 - 05 - 25 - 9:20
 * @Description: com.zhengxin.tank
 * @version: 1.0
 */
public class ForeDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bx = t.getX() + Tank.WITH/ 2 - Bullet.WITH / 2;
        int by = t.getY() + Tank.HIGH / 2 - Bullet.HIGH / 2;
        Dir[] values = Dir.values();
        for (int i = 0;i<values.length;i++) {
            GameModel.GetInstance().getObjects().add(new RectDecorator(new Bullet(bx, by, values[i], t.getGroup())));
        }
    }
    private static volatile ForeDirFireStrategy foreDirFireStrategy ;
//    private ForeDirFireStrategy(){}
    public ForeDirFireStrategy(){}
    public static ForeDirFireStrategy getInstance() {
        if (foreDirFireStrategy == null) {
            synchronized (ForeDirFireStrategy.class) {
                if (foreDirFireStrategy == null)
                    foreDirFireStrategy = new ForeDirFireStrategy();
            }
        }
        return foreDirFireStrategy;
    }
}
