package com.zhengxin.tank.chainofresponsibility;

import com.zhengxin.tank.GameObject;
import com.zhengxin.tank.PropertyMgr;
import com.zhengxin.tank.PropetryKeys;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/26 - 05 - 26 - 14:37
 * @Description: com.zhengxin.tank.chainofresponsibility
 * @version: 1.0
 */
public class ColliderChain implements Collider{
    private List<Collider> collides = new LinkedList<Collider>();
    public ColliderChain(){
        String[] collidesStars = PropertyMgr.getInstance().getStringProp(PropetryKeys.COLLIDERS).split(",");
        for (String collide : collidesStars) {
            try {
                collides.add((Collider)Class.forName(collide).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void collide(GameObject o1,GameObject o2){
        Iterator<Collider> it = this.collides.iterator();
       while (it.hasNext()) {
           Collider next = it.next();
           next.collide(o1,o2);
       }
    }
}
