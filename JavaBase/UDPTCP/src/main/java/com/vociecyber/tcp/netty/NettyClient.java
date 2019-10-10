package com.vociecyber.tcp.netty;

import com.vociecyber.tcp.netty.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;

public class NettyClient {
    public void initClient(String ip, Integer... ports) throws InterruptedException {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });

//                创建异步连接，添加多个tcp 端口
//        if (null!=ports&&ports.length>0){
//            for (Integer port : ports) {
        ChannelFuture channelFuture = bootstrap.connect(ip, 22203);
        String msg = "hello server port:  " + 22203;
        ChannelFuture channelFuture2 = bootstrap.connect(ip, 22204);
        channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
        String msg2 = "hello server port:  " + 22204;
        channelFuture2.channel().writeAndFlush(Unpooled.copiedBuffer(msg2.getBytes()));
        channelFuture.channel().closeFuture().sync();
        channelFuture2.channel().closeFuture().sync();
//            }
//        }
        workGroup.shutdownGracefully();

    }

    public static void main(String[] args) throws InterruptedException {
        NettyClient nettyClient = new NettyClient();
        Integer[] port = {22203, 22204};
        nettyClient.initClient("192.168.6.25", port);
    }
}
