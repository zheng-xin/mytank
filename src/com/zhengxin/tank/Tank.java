package com.zhengxin.tank;

import com.zhengxin.tank.obsever.FireEvent;
import com.zhengxin.tank.obsever.IFireListener;
import com.zhengxin.tank.strategy.FireStrategy;

import javax.swing.event.TreeWillExpandListener;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/23 - 05 - 23 - 17:00
 * @Description: ̹坦克
 * @version: 1.0
 */
public class Tank extends GameObject{
    private Dir dir = Dir.DOWN;
    private static final int SPEED =PropertyMgr.getInstance().getIntProp(PropetryKeys.TANKSPEED);
    public static int HIGH = ResourceMgr.goodTankD.getHeight();
    public static int WITH = ResourceMgr.goodTankD.getWidth();
    private boolean isMoving = true;
    private static final String goodFireStrategyString = PropertyMgr.getInstance().getStringProp(PropetryKeys.GOODFS);
    private static final String badFireStrategyString = PropertyMgr.getInstance().getStringProp(PropetryKeys.BADFS);
    private Random random = new Random();
    private FireStrategy fireStrategy;//开火策略
    int prevX,prevY;
    //模拟观察者模式
    private List<IFireListener> fireListeners = new LinkedList<IFireListener>();


    public Tank(int x, int y, Dir dir, GameModel gm, boolean isMoving, Group group) {
        super.x = x;
        this.y = y;
        this.dir = dir;
        this.isMoving = isMoving;
        this.group = group;
        this.rect.width =  ResourceMgr.goodTankD.getWidth();
        this.rect.height = ResourceMgr.goodTankD.getHeight();
        this.with = ResourceMgr.goodTankD.getWidth();
        this.high = ResourceMgr.goodTankD.getHeight();
        initFireStrategy(group);
    }
    private void initFireStrategy(Group g)  {
        String className = g == Group.GOOD ? goodFireStrategyString : badFireStrategyString;
        try {
            fireStrategy = (FireStrategy) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void paint(Graphics g) {
        if (!this.living) {
            GameModel.GetInstance().getObjects().remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            default:
                g.drawImage(ResourceMgr.goodTankR, x, y, null);
                break;
        }
        if (this.group == Group.BAD && random.nextInt(100) > 95) fire();
        if (this.group == Group.BAD && random.nextInt(100) > 95) this.dir = Dir.values()[random.nextInt(4)];
        boundsCheck();
        moving();
    }

    private void boundsCheck() {
        if (this.x < 10) this.x = 10;
        if (this.y < 35) this.y = 35;
        if (this.x > MyFrame.WIDTH - this.with - 20) this.x = MyFrame.WIDTH - this.with - 20;
        if (this.y > MyFrame.HIGH - this.high - 20) this.y = MyFrame.HIGH - this.high - 20;
    }

    public void fire() {

        //fireStrategy.fire(this);
        Iterator<IFireListener> it = this.fireListeners.iterator();
        FireEvent event = new FireEvent(this);
        while (it.hasNext()) {
            IFireListener fireListener = it.next();
            fireListener.Fire(event);
        }
        //模拟观察者模式  实际没必要这么写
    }
    public void fire2(){
        this.fireStrategy.fire(this);
    }

    public Group getGroup() {
        return this.group;
    }

    private void moving() {
        this.prevX = x;
        this.prevY = y;
        if (!isMoving) return;
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }



    public Dir getDir (){
        return this.dir;
    }

    public void explode() {
        int bx = this.x + this.with / 2 - Explode.WITH / 2;
        int by = this.y + this.high / 2 - Explode.HIGH / 2;
        GameModel.GetInstance().getObjects().add(new Explode(bx, by));
    }
    public void returnLastPosion(){
        this.x = prevX;
        this.y = prevY;
    }
    public void addFireEvent(IFireListener listener){
        this.fireListeners.add(listener);
    }

}
