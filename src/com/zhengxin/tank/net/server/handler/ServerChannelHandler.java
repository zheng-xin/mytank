package com.zhengxin.tank.net.server.handler;

import com.zhengxin.tank.net.server.Server;
import com.zhengxin.tank.net.msg.Msg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: zhengxin
 * @Date: 2020/6/21 - 06 - 21 - 19:33
 * @Description: com.zhengxin.tank.net.server.handler
 * @version: 1.0
 */
public class ServerChannelHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        Msg msg1 = (Msg)msg;
        System.out.println(msg1.getX());
        Server.clients.writeAndFlush(msg1);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel2 :" + ctx.channel());
        Server.clients.add(ctx.channel());
    }
}
