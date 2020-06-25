package com.zhengxin.tank.net.handler;

import com.zhengxin.tank.net.msg.Msg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Auther: zhengxin
 * @Date: 2020/6/21 - 06 - 21 - 16:06
 * @Description: com.zhengxin.tank.net.client
 * @version: 1.0
 */
public class TankMsgEncoder extends MessageToByteEncoder<Msg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf buf) throws Exception {
        buf.writeInt(msg.getType().ordinal());
        byte[] bytes = msg.getBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }


}
