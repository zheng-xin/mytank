package com.zhengxin.tank.obsever;

import com.zhengxin.tank.Bullet;
import com.zhengxin.tank.Tank;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/28 - 05 - 28 - 20:23
 * @Description: com.zhengxin.tank.obsever
 * @version: 1.0
 */
public class FireEvent {
    private Tank source;
    public FireEvent(Tank t) {
        this.source =t;
    }
    public Tank getSource(){
        return this.source;
    }
}
