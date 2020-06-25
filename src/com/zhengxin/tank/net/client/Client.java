package com.zhengxin.tank.net.client;

import com.zhengxin.tank.Bullet;
import com.zhengxin.tank.GameModel;
import com.zhengxin.tank.Tank;
import com.zhengxin.tank.net.handler.TankMsgDecoder;
import com.zhengxin.tank.net.handler.TankMsgEncoder;
import com.zhengxin.tank.net.msg.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UnknownFormatConversionException;

/**
 * @Auther: zhengxin
 * @Date: 2020/6/21 - 06 - 21 - 16:03
 * @Description: com.zhengxin.tank.net.client
 * @version: 1.0
 */
public class Client {


    private volatile Channel channel = null;
    public static Client INSTANCE  = new Client();
    public Channel getChannel(){return this.channel;}
    private Client(){}
    public void connect() {
        // 线程池
        EventLoopGroup group = new NioEventLoopGroup(1);

        Bootstrap b = new Bootstrap();

        try {
            ChannelFuture f = b.group(group).channel(NioSocketChannel.class).handler(new ClientChannelInitializer())
                    .connect("localhost", 8888);

            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        System.out.println("not connected!");
                    } else {
                        System.out.println("connected!");
                        // initialize the channel
                        channel = future.channel();
                        TankMsg msg = new TankMsg(GameModel.GetInstance().getMainTank());
                        channel.writeAndFlush(msg);
                    }
                }
            });

            f.sync();
            // wait until close
            f.channel().closeFuture().sync();
            System.out.println("已经退出");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void send(String msg) {
        ByteBuf buf = Unpooled.copiedBuffer(msg.getBytes());
        channel.writeAndFlush(buf);
    }
    public void send(Msg msg) {
        channel.writeAndFlush(msg);
    }



    public void closeConnect() {
        this.send("_bye_");
        //channel.close();
    }
}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new TankMsgDecoder())
                .addLast(new TankMsgEncoder())
                .addLast(new ClientHandler());
    }

}

class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Msg msgObj = (Msg) msg;
        if (msgObj.getType() == MsgType.TankInit) {
            TankMsg tankMsg = (TankMsg) msgObj;
            Tank tank = (Tank) GameModel.GetInstance().getObjects().get(tankMsg.getUuid());
            if (tank == null) {
                GameModel.GetInstance().getObjects().put(tankMsg.getUuid(), new Tank(tankMsg));
                Client.INSTANCE.send(new TankMsg( GameModel.GetInstance().getMainTank()));
            }
        } else if (msgObj.getType() == MsgType.TankMove) {
            TankMoveMsg moveMsg = (TankMoveMsg) msgObj;
            Tank tank = (Tank) GameModel.GetInstance().getObjects().get(moveMsg.getUuid());
            tank.change(moveMsg);
        } else if (msgObj.getType() == MsgType.BulletInit) {
            BulletMsg bulletMsg = (BulletMsg)msgObj;
            Bullet bullet = (Bullet)GameModel.GetInstance().getObjects().get(bulletMsg.getUuid());
            if (bullet == null) {
                bullet = new Bullet(bulletMsg);
                GameModel.GetInstance().getObjects().put(bullet.getId(),bullet);
            }
        }

        System.out.println("tank init ");

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }
}
