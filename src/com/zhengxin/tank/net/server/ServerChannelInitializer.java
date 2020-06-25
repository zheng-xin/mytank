package com.zhengxin.tank.net.server;

import com.zhengxin.tank.net.handler.TankMsgDecoder;
import com.zhengxin.tank.net.handler.TankMsgEncoder;
import com.zhengxin.tank.net.server.handler.ServerChannelHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * @Auther: zhengxin
 * @Date: 2020/6/21 - 06 - 21 - 19:32
 * @Description: com.zhengxin.tank.net.server
 * @version: 1.0
 */
public class ServerChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pl = ch.pipeline();
        pl.addLast(new TankMsgEncoder());
        pl.addLast(new TankMsgDecoder());
        pl.addLast(new ServerChannelHandler());
        System.out.println("channel init success");
    }
}
