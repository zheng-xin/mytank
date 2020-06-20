package com.zhengxin.tank;

import java.awt.*;

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
    public Bullet(int x, int y, Dir dir,Group group) {
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.group = group;
        this.rect.width = ResourceMgr.bulletL.getWidth();
        this.rect.height = ResourceMgr.bulletL.getWidth();
        this.with = ResourceMgr.bulletL.getWidth();
        this.high = ResourceMgr.bulletL.getHeight();
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
            GameModel.GetInstance().getObjects().remove(this);
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
        GameModel.GetInstance().getObjects().add(new Explode(bx, by));
    }
}
