package com.zhengxin.tank.abstractfactory;

import com.zhengxin.tank.Dir;
import com.zhengxin.tank.Group;
import com.zhengxin.tank.MyFrame;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/25 - 05 - 25 - 17:12
 * @Description: com.zhengxin.tank.abstractfactory
 * @version: 1.0
 */
public abstract class GameFactory {

    abstract BaseTank createTank(int x, int y, Dir dir, Group group, MyFrame myFrame);

    abstract BaseBullet createExplode(int x,int y,MyFrame myFrame);

    abstract BaseExplode createBullet(int x,int y,MyFrame myFrame);
}
