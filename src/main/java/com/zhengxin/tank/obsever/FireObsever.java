package com.zhengxin.tank.obsever;

import com.zhengxin.tank.Bullet;
import com.zhengxin.tank.GameModel;
import com.zhengxin.tank.Tank;

import javax.xml.transform.Source;
import java.io.Serializable;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/28 - 05 - 28 - 20:27
 * @Description: com.zhengxin.tank.obsever
 * @version: 1.0
 */
public class FireObsever implements IFireListener, Serializable {
    @Override
    public void Fire(FireEvent e) {
        Tank source = e.getSource();
        source.fire2();
    }
}
