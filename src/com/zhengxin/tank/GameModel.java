package com.zhengxin.tank;

import com.zhengxin.tank.chainofresponsibility.ColliderChain;
import com.zhengxin.tank.obsever.IFireListener;
import com.zhengxin.tank.obsever.SendMsgObsever;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/26 - 05 - 26 - 7:05
 * @Description: 门面模式 myframe
 * @version: 1.0
 */
public class GameModel {


    private Map<UUID,GameObject> objects = new HashMap<UUID, GameObject>();
    private UUID maintankId;
    private final int tankCount = PropertyMgr.getInstance().getIntProp(PropetryKeys.INITTANKCOUNT);
    ColliderChain colliderChain = new ColliderChain();
    private static volatile GameModel gameModel = null;
    private GameModel(){
        init();
    }
    public static GameModel GetInstance(){
        if (gameModel == null) {
            synchronized (GameModel.class) {
                if (gameModel == null)
                    gameModel = new GameModel();
            }
        }
        return gameModel;
    }
    private void  init (){
        IFireListener listener = new SendMsgObsever();
        Random random = new Random();
        this.maintankId = UUID.randomUUID();
        Tank mainTank = new Tank(random.nextInt(300), random.nextInt(400), Dir.UP, false, Group.values()[random.nextInt(2)],maintankId);
        mainTank.addFireEvent(listener);
        this.objects.put(this.maintankId, mainTank);
        //初始换敌方坦克
//        Tank tk = null;
//        for (int i = 0; i < tankCount; i++) {
//            tk = new Tank(i * 80 + 200, 200, Dir.DOWN, this, true, Group.BAD);
//            tk.addFireEvent(listener);
//            this.objects.add(tk);
//        }
        Wall wall = new Wall(200,600,50,50);
        this.objects.put(wall.id,wall);
    }
    public Tank getMainTank(){
        return (Tank) this.objects.get(this.maintankId);
    }

    public Map<UUID,GameObject> getObjects(){
        return this.objects;
    }
    public void paint(Graphics g) {
        UUID[] keys = this.objects.keySet().toArray(new UUID[]{});
        for (int i = 0; i < keys.length; i++) {
            this.objects.get(keys[i]).paint(g);
        }

        for (int i = 0; i < keys.length;i++) {
            for (int j = i+1;j<keys.length;j++) {
                GameObject o1 = this.objects.get(keys[i]);
                GameObject o2 = this.objects.get(keys[j]);
                colliderChain.collide(o1,o2);
            }

        }
    }
}
