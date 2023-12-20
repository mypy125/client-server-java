package org.example.geek.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerApp {
    public static final int PORT = 8081;
    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);//создаем для входящих потоков
        EventLoopGroup workerGroup = new NioEventLoopGroup();//создаем для работы с потоками
        try{
            ServerBootstrap b = new ServerBootstrap();//создание сервера
            b.group(bossGroup,workerGroup)//надстройка Сервака для подклучение потоков и для работы с потоками
                    .channel(NioServerSocketChannel.class)//для подклученных исползуем канал NioServerSocketChannel
                    .childHandler(new ChannelInitializer<SocketChannel>() {//иницализируем подклучение
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                        }
                    });
            ChannelFuture future = b.bind(PORT).sync();//надстройка порта и запуска сервака
            future.channel().closeFuture().sync();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
