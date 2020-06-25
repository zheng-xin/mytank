package com.zhengxin.tank.net.msg;

import com.zhengxin.tank.*;

import java.io.*;
import java.util.UUID;

/**
 * @Auther: zhengxin
 * @Date: 2020/6/24 - 06 - 24 - 19:42
 * @Description: com.zhengxin.tank.net.msg
 * @version: 1.0
 */
public class BulletMsg extends Msg{

    private Dir dir;
    private Group group;

    @Override
    public byte[] getBytes() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        try {
            dataOutputStream.writeInt(this.x);
            dataOutputStream.writeInt(this.y);
            dataOutputStream.writeInt(dir.ordinal());
            dataOutputStream.writeInt(group.ordinal());
            dataOutputStream.writeLong(uuid.getLeastSignificantBits());
            dataOutputStream.writeLong(uuid.getMostSignificantBits());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return outputStream.toByteArray();
    }

    @Override
    public MsgType getType() {
        return MsgType.BulletInit;
    }

    @Override
    public Msg parse(byte[] bytes) {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.x = dataInputStream.readInt();
            this.y = dataInputStream.readInt();
            this.dir = Dir.values()[dataInputStream.readInt()];
            this.group = Group.values()[dataInputStream.readInt()];
            long least = dataInputStream.readLong();
            long most = dataInputStream.readLong();
            this.uuid = new UUID(most, least);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
    public void setDir(Dir dir){
        this.dir = dir;
    }
    public void setGroup(Group group){
        this.group =group;
    }
    public Dir getDir(){
        return this.dir;
    }
    public Group getGroup(){
        return this.group;
    }
}
