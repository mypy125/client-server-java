package org.example.client.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

public class MainHandler extends SimpleChannelInboundHandler<String> {
    //мой входящи оброботчик
    private static final List<Channel> channels = new ArrayList<>();
    private String clientName;
    private static int clientIndex = 1;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client Connected..." + ctx);
        channels.add(ctx.channel());
        clientName = "Client #" + clientIndex;
        clientIndex++;
    }



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String string) throws Exception {
        System.out.println("message received: " + string);
        String out = String.format("[%s]: %s\n", clientName, string);
        for (Channel channel: channels) {
            channel.writeAndFlush(out);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
