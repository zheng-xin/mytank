package com.zhengxin.tank.strategy;

import com.zhengxin.tank.Bullet;
import com.zhengxin.tank.Tank;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/25 - 05 - 25 - 8:53
 * @Description: com.zhengxin.tank
 * @version: 1.0
 */
public interface FireStrategy {
    Bullet[] fire(Tank t);
}
