package org.example.client;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Network {
    public static final int PORT = 8081;
    public static final String HOST = "localhost";
    private SocketChannel channel;
    public Network(){
        new Thread(()->{
            EventLoopGroup worker = new NioEventLoopGroup();
            try{
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(worker)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                               channel = socketChannel;
                               socketChannel.pipeline().addLast(new StringDecoder(), new StringEncoder());//зоворачиваем в байт буффер
                            }
                        });
                ChannelFuture future = bootstrap.connect(HOST,PORT).sync();
                future.channel().closeFuture().sync();//делаем ожидание
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                worker.shutdownGracefully();//зокриваем поток
            }
        }).start();
    }

    public void sendMassage(String string){
        channel.writeAndFlush(string);
    }
}
