package com.zhengxin.tank.net.handler;

import com.zhengxin.tank.net.msg.BulletMsg;
import com.zhengxin.tank.net.msg.MsgType;
import com.zhengxin.tank.net.msg.TankMoveMsg;
import com.zhengxin.tank.net.msg.TankMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Auther: zhengxin
 * @Date: 2020/6/21 - 06 - 21 - 16:26
 * @Description: com.zhengxin.tank.net.client
 * @version: 1.0
 */
public class TankMsgDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 8) return; //TCP 消息头
        in.markReaderIndex();
        MsgType msgType = MsgType.values()[in.readInt()];
        int length = in.readInt();
        if (in.readableBytes() < length) {
           in.resetReaderIndex();
           return;
        }
        byte[] bytes = new byte[length];
        in.readBytes(bytes);
        switch (msgType) {
            case TankInit:
                TankMsg msg = new TankMsg();
                msg.parse(bytes);
                out.add(msg);
                break;
            case TankMove:
                TankMoveMsg msg1 = new TankMoveMsg();
                msg1.parse(bytes);
                out.add(msg1);
                break;
            case BulletInit:
                BulletMsg msg2 = new BulletMsg();
                msg2.parse(bytes);
                out.add(msg2);
            default:
                break;
        }


    }

}
