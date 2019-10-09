package com.vociecyber.tcp.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf buf = (ByteBuf) msg;
            System.out.println("server  get client msg : " + buf.toString(CharsetUtil.UTF_8));

            //server端向client发送反馈数据 如果是绑定了多个端口 那么都会进行发送
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello client ".getBytes()));
        } finally {
            ReferenceCountUtil.refCnt(msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
