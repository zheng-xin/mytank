package com.zhengxin.tank.clent;

import com.sun.org.apache.bcel.internal.generic.NEW;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Auther: zhengxin
 * @Date: 2020/6/20 - 06 - 20 - 15:53
 * @Description: com.zhengxin.tank.clent
 * @version: 1.0
 */
public class Client {
    private Client(){};
    public static Client INSTANCE = new Client();
    public void conect(){
        EventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        try {
            ChannelFuture f = bootstrap.group(group).
                    channel(NioSocketChannel.class).
                    handler(new ClientChannelInitializer()).
                    bind("localhost", 8888).
                    sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
