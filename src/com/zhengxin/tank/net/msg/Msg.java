package com.zhengxin.tank.net.msg;

import java.util.UUID;

/**
 * @Auther: zhengxin
 * @Date: 2020/6/22 - 06 - 22 - 20:02
 * @Description: com.zhengxin.tank.net.tank
 * @version: 1.0
 */
public abstract  class Msg {
    protected int x,y;
    protected UUID uuid;
    public abstract byte[] getBytes();
    public abstract MsgType getType();
    public abstract Msg parse(byte[] bytes);
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
