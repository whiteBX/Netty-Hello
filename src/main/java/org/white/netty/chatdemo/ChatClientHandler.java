package org.white.netty.chatdemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * <p> </p >
 *
 * @author white
 * @version $Id: ChatClientHandler.java, v 0.1 2019年03月16日 上午12:48:00 white Exp$
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
