package org.example.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<String> {
    private Callback onMassageReceivedCallback;

    public ClientHandler(Callback onMassageReceivedCallback) {
        this.onMassageReceivedCallback = onMassageReceivedCallback;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String string) throws Exception {
        if(onMassageReceivedCallback != null){
            onMassageReceivedCallback.callback(string);
        }
    }
}
