package com.zhengxin.tank.strategy;

import com.zhengxin.tank.Bullet;
import com.zhengxin.tank.Dir;
import com.zhengxin.tank.GameModel;
import com.zhengxin.tank.Tank;
import com.zhengxin.tank.decorator.RectDecorator;
import com.zhengxin.tank.decorator.TailDecorator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/25 - 05 - 25 - 9:20
 * @Description: com.zhengxin.tank
 * @version: 1.0
 */
public class ForeDirFireStrategy implements FireStrategy {
    @Override
    public Bullet[] fire(Tank t) {
        int bx = t.getX() + Tank.WITH/ 2 - Bullet.WITH / 2;
        int by = t.getY() + Tank.HIGH / 2 - Bullet.HIGH / 2;
        Dir[] values = Dir.values();
        List<Bullet> bullets = new ArrayList<Bullet>();
        for (int i = 0;i<values.length;i++) {
//           装饰模式 GameModel.GetInstance().getObjects().add(new TailDecorator(new RectDecorator(new Bullet(bx, by, values[i], t.getGroup()))));
            UUID uuid = UUID.randomUUID();
            Bullet bullet = new Bullet(bx, by, values[i], t.getGroup(), uuid);
            GameModel.GetInstance().getObjects().put(uuid,bullet);
            bullets.add(bullet);
        }
        return bullets.toArray(new Bullet[]{});
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
