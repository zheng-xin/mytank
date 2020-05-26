package com.zhengxin.tank.chainofresponsibility;

import com.zhengxin.tank.GameObject;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/26 - 05 - 26 - 12:34
 * @Description: com.zhengxin.tank.chainofresponsibility
 * @version: 1.0
 */
public interface Collider {
    void collide(GameObject o1,GameObject o2);
}
