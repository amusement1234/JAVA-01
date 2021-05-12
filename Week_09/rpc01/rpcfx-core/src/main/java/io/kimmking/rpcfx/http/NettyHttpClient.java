package io.kimmking.rpcfx.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;


public class NettyHttpClient {

    private ClientHandler clientHandler = new ClientHandler();

    public NettyHttpClient() {
        try {
            start("127.0.0.1",8080);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start(String host,int port) throws InterruptedException {
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(loopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new HttpRequestEncoder()).addLast(new HttpResponseDecoder()).addLast(clientHandler);
            }
        });
        Channel channel = null;
        try {
            channel = b.connect(host, 8080).sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!channel.isActive()) {
            Thread.sleep(1000);
        }
    }


    public String sendRequest(String url,String data) throws URISyntaxException, UnsupportedEncodingException, InterruptedException {
        //配置HttpRequest的请求数据和一些配置信息
        FullHttpRequest request = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_0, HttpMethod.POST, new URI(url).toASCIIString(), Unpooled.wrappedBuffer(data.getBytes("UTF-8")));

        request.headers()
                .set(HttpHeaderNames.CONTENT_TYPE, "application/json")
                //开启长连接
                .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
                //设置传递请求内容的长度
                .set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
//        ChannelFuture channelFuture = channel.writeAndFlush(request);
//        channelFuture.addListener(future -> System.out.println(Thread.currentThread().getName() + " 请求发送完成"));
        ChannelPromise promise = clientHandler.sendMessage(request);
        promise.await();
        System.out.println("netty-response:"+clientHandler.getData());
        return clientHandler.getData();
    }


}
