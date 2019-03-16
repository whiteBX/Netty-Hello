package org.white.netty.chatdemo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


/**
 * <p> </p >
 *
 * @author white
 * @version $Id: ChatServerHandler.java, v 0.1 2019年03月16日 上午12:23:00 white Exp$
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "：" + msg);
        channelGroup.forEach(c -> {
            if (channel == c) {
                c.writeAndFlush("我：" + msg);
            } else {
                c.writeAndFlush(channel.remoteAddress() + "：" + msg);
            }
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "上线");
        channelGroup.writeAndFlush(channel.remoteAddress() + "上线");
        channelGroup.add(channel);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(channel.remoteAddress() + "下线");
        System.out.println(channel.remoteAddress() + "下线");
    }
}
