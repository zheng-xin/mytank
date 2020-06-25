package com.zhengxin.tank;

import com.zhengxin.tank.net.msg.BulletMsg;

import java.awt.*;
import java.util.UUID;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/23 - 05 - 23 - 18:46
 * @Description: 子弹
 * @version: 1.0
 */
public class Bullet extends GameObject{
    private static final int SPEED = PropertyMgr.getInstance().getIntProp(PropetryKeys.BULLETSPEED);
    private Dir dir;
    public static int HIGH = ResourceMgr.bulletL.getHeight();
    public static int WITH = ResourceMgr.bulletL.getWidth();
    public Bullet(int x, int y, Dir dir,Group group,UUID id) {
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.group = group;
        this.rect.width = ResourceMgr.bulletL.getWidth();
        this.rect.height = ResourceMgr.bulletL.getWidth();
        this.with = ResourceMgr.bulletL.getWidth();
        this.high = ResourceMgr.bulletL.getHeight();
        this.id = id;
    }
    public Bullet(BulletMsg msg) {
        this.dir = msg.getDir();
        this.x = msg.getX();
        this.y = msg.getY();
        this.group = msg.getGroup();
        this.rect.width = ResourceMgr.bulletL.getWidth();
        this.rect.height = ResourceMgr.bulletL.getWidth();
        this.with = ResourceMgr.bulletL.getWidth();
        this.high = ResourceMgr.bulletL.getHeight();
        this.id = msg.getUuid();
    }

    public void paint(Graphics g) {
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
                LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;

        }
        moving();
    }


    private void moving() {
        if (!living) {
            GameModel.GetInstance().getObjects().remove(this.id);
            return;
        }
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
        if (x < 0 || y < 0 || x > MyFrame.WIDTH || y > MyFrame.HIGH) {
            living = false;
        }

    }
    public void explode() {
        int bx = this.x + this.with / 2 - Explode.WITH / 2;
        int by = this.y + this.high / 2 - Explode.HIGH / 2;
        UUID uuid = UUID.randomUUID();
        GameModel.GetInstance().getObjects().put(uuid,new Explode(bx, by,uuid));
    }
    public void change(BulletMsg msg){
        GameObject bullet = GameModel.GetInstance().getObjects().get(this.id);
        if (bullet == null) {
            Bullet bullet1 = new Bullet(this.x, this.y, this.dir, this.group,this.id);
            GameModel.GetInstance().getObjects().put(this.id,bullet1);
        } else {
            Bullet b = (Bullet) bullet;
            b.x = msg.getX();
            b.y = msg.getY();
            b.dir = msg.getDir();
        }
    }
    public BulletMsg getMsg(){
        BulletMsg msg = new BulletMsg();
        msg.setDir(this.dir);
        msg.setX(this.x);
        msg.setY(this.y);
        msg.setGroup(this.group);
        msg.setUuid(this.id);
        return msg;
    }
}
