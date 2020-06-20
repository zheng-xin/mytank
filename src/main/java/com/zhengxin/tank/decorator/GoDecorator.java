package com.zhengxin.tank.decorator;

import com.zhengxin.tank.GameObject;
import sun.plugin2.message.GetAppletMessage;
import sun.security.jca.GetInstance;

import java.awt.*;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/26 - 05 - 26 - 20:55
 * @Description: com.zhengxin.tank.decorator
 * @version: 1.0 装饰器模式  通过聚合实现
 */
public class GoDecorator extends GameObject {
    GameObject go = null;
    public GoDecorator(GameObject go) {
        this.go = go;
    }
    @Override
    public void paint(Graphics g) {
        go.paint(g);
    }
}
