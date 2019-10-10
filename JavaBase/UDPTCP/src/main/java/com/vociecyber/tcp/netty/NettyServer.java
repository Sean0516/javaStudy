package com.vociecyber.tcp.netty;

import com.vociecyber.tcp.netty.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public void initServer(Integer... ports) {
        EventLoopGroup connectGroup = new NioEventLoopGroup();
//        用来处理已经连接的 客户端，用于实际的业务逻辑操作
        EventLoopGroup workGroup = new NioEventLoopGroup();
//        第一个线程组用于接受client连接。
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(connectGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ServerHandler());
                        }
                    })
//                设置TCP连接的缓冲区
                    .option(ChannelOption.SO_BACKLOG, 128)
//                设置发送缓冲区大小
//                    .option(ChannelOption.SO_SNDBUF, 1024)
//                设置接受缓冲区大小
                    .option(ChannelOption.SO_RCVBUF, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture = serverBootstrap.bind(22203);
            ChannelFuture channelFuture2 = serverBootstrap.bind(22204);
            channelFuture.channel().closeFuture().sync();
            channelFuture2.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            connectGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer();
        Integer[] port = {22203, 22204};
        nettyServer.initServer(port);
    }
}
