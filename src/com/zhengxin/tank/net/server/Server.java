package com.zhengxin.tank.net.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Auther: zhengxin
 * @Date: 2020/6/21 - 06 - 21 - 19:31
 * @Description: com.zhengxin.tank.net.server
 * @version: 1.0
 */
public class Server {
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(2);
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            ChannelFuture f = bootstrap.group(bossGroup, workGroup).
                    channel(NioServerSocketChannel.class).
                    childHandler(new ServerChannelInitializer()).
                    bind(8888).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
