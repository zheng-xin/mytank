package com.zhengxin.tank;

import com.zhengxin.tank.chainofresponsibility.BulletAndTankCollide;
import com.zhengxin.tank.chainofresponsibility.Collider;
import com.zhengxin.tank.chainofresponsibility.ColliderChain;
import com.zhengxin.tank.chainofresponsibility.TankAndTankCollide;
import com.zhengxin.tank.obsever.FireObsever;
import com.zhengxin.tank.obsever.IFireListener;
import sun.security.jca.GetInstance;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/26 - 05 - 26 - 7:05
 * @Description: 门面模式 myframe
 * @version: 1.0
 */
public class GameModel {

    private Tank mainTank = new Tank(300, 400, Dir.UP, this, false, Group.GOOD);
    private List<GameObject> objects = new ArrayList<GameObject>();
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
        IFireListener listener = new FireObsever();
        this.mainTank.addFireEvent(listener);
        //初始换敌方坦克
        Tank tk = null;
        for (int i = 0; i < tankCount; i++) {
            tk = new Tank(i * 80 + 200, 200, Dir.DOWN, this, true, Group.BAD);
            tk.addFireEvent(listener);
            this.objects.add(tk);
        }
        Wall wall = new Wall(200,600,50,50);
        this.objects.add(wall);
    }
    public Tank getMainTank(){
        return mainTank;
    }

    public List<GameObject> getObjects(){
        return this.objects;
    }
    public void paint(Graphics g) {

        this.getMainTank().paint(g);

        for (int i = 0; i < this.objects.size(); i++) {
            this.objects.get(i).paint(g);
        }
        for (int i = 0; i < this.objects.size();i++) {
            for (int j = i+1;j<this.objects.size();j++) {
                GameObject o1 = this.objects.get(i);
                GameObject o2 = this.objects.get(j);
                colliderChain.collide(o1,o2);
            }
        }
    }
    public void save()  {
        File file = new File("T:/tank.data");
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(mainTank);
            objectOutputStream.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public void load(){
        File file = new File("T:/tank.data");
        ObjectInputStream inputStream =null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
            mainTank = (Tank) inputStream.readObject();
            objects = (List<GameObject>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
