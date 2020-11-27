package com.galaxyeye.websocket.tool.netty.websocket; /*
 * Description:com.galaxyeye.websocket.tool.netty
 * @Date Create on 12:31
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/1日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.WxServiceReqLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase.conTest.WebSocketClientHandlerTest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.AsciiString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class NettyWebSocketClient {
    public static ConcurrentHashMap<Integer, Channel> webSocketClientCustomMap = new ConcurrentHashMap();

    @Test
    public void test() {
        int thread=100;
        initChannel(thread, "ws://172.16.0.25:1443");
        WxServiceReqLoginBO wxServiceReqLoginBO=new WxServiceReqLoginBO();
        Map<String, String> statistics = new HashMap<>();
        statistics.put("channelNo", "小程序服务端渠道编号");
        statistics.put("维度一", "渠道编号");
        statistics.put("维度二", "28");
        wxServiceReqLoginBO.setBmAppid("1.00002");
        wxServiceReqLoginBO.setMethod("loginReq");
        wxServiceReqLoginBO.setPasswd("123456");
        wxServiceReqLoginBO.setUname("hxw002");
        wxServiceReqLoginBO.setStatistics(statistics);
        String content=JSON.toJSONString(wxServiceReqLoginBO);
        for (int i = 0; i <thread ; i++) {
            sengMessage(webSocketClientCustomMap.get(i), content);
        }
    }


    public static void sengMessage(Channel channel, String content) {
        //发送的内容，是一个文本格式的内容
        log.info("sengMessage请求参数" + content);
        TextWebSocketFrame frame = new TextWebSocketFrame(content);
        channel.writeAndFlush(frame).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    log.info("消息发送成功，发送的消息是：" + content);
                } else {
                    log.info("消息发送失败 " + channelFuture.cause().getMessage());
                }
            }
        });
    }


    private void connect(Bootstrap boot, WebSocketClientHandshaker handshaker, Integer k, String host, int port) {
        boot.connect(host, port).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                /*
                 * 这里就不是主线程了，这里是 netty 线程中执行
                 */
                Channel channel = future.channel();
                NettyWebSocketClientHandler handler = (WebSocketClientHandlerTest) channel.pipeline().get("hookedHandler");
                handler.setHandshaker(handshaker);
                //开始与服务端进行握手
                handshaker.handshake(channel);
                //不阻塞等待是否握手成功
                handler.handshakeFuture();
                if (future.isSuccess()) {
                    log.info("握手成功");
                    webSocketClientCustomMap.put(k, channel);
                }
            }
        });
    }

    /**
     * @param url 与服务器通信的url=ws://172.16.0.25:1443
     * @param num 建立channel的数量
     */
    private void initChannel(Integer num, String url) {
        //设置请求头的默认key=sec-websocket-key为key=Sec-WebSocket-Key
        initHeader();
        String wsUrlSy = url;
        //netty基本操作，线程组
        EventLoopGroup group = new NioEventLoopGroup();
        //netty基本操作，启动类
        Bootstrap boot = new Bootstrap();
        boot.option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .group(group)
                .handler(new LoggingHandler(LogLevel.INFO))
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("http-codec", new HttpClientCodec());
                        pipeline.addLast("aggregator", new HttpObjectAggregator(1024 * 1024 * 10));
                        pipeline.addLast("hookedHandler", new WebSocketClientHandlerTest());
                    }
                });
        try {
            //websocke连WebSocketClientHandler接的地址
            URI websocketURI = new URI(wsUrlSy);
            HttpHeaders httpHeaders = new DefaultHttpHeaders();
            for (int j = 0; j < num; j++) {
                //进行握手
                WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(websocketURI, WebSocketVersion.V13, (String) null, true, httpHeaders);
                connect(boot, handshaker, j, websocketURI.getHost(), websocketURI.getPort());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 修改请求头的key=sec-websocket-key为key=Sec-WebSocket-Key
     */
    public static void initHeader() {
        try {
            Field field = HttpHeaderNames.class.getDeclaredField("SEC_WEBSOCKET_KEY");
            updateFinalModifiers(field);
            field.set(HttpHeaderNames.SEC_WEBSOCKET_KEY, new AsciiString("Sec-WebSocket-Key"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateFinalModifiers(Field field) throws NoSuchFieldException, IllegalAccessException {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
    }


    /**
     * 基于websocket的netty客户端
     * https://www.cnblogs.com/daijiting/p/12092202.html
     */
    public static void main(String[] args) throws Exception {
        //netty基本操作，线程组
        EventLoopGroup group = new NioEventLoopGroup();
        //netty基本操作，启动类
        Bootstrap boot = new Bootstrap();
        boot.option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .group(group)
                .handler(new LoggingHandler(LogLevel.INFO))
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("http-codec", new HttpClientCodec());
                        pipeline.addLast("aggregator", new HttpObjectAggregator(1024 * 1024 * 10));
                        pipeline.addLast("hookedHandler", new WebSocketClientHandlerTest());
                    }
                });
        //websocke连接的地址
        URI websocketURI = new URI("ws://172.16.0.25:1443");
        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        initHeader();
        //进行握手
        WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(websocketURI, WebSocketVersion.V13, (String) null, true, httpHeaders);
        //客户端与服务端连接的通道，final修饰表示只会有一个
        final Channel channel = boot.connect(websocketURI.getHost(), websocketURI.getPort()).sync().channel();
        NettyWebSocketClientHandler handler = (WebSocketClientHandlerTest) channel.pipeline().get("hookedHandler");
        handler.setHandshaker(handshaker);
        //开始与服务端进行握手
        handshaker.handshake(channel);
        //阻塞等待是否握手成功
        handler.handshakeFuture().sync();
        log.info("握手成功");
        //给服务端发送的内容，如果客户端与服务端连接成功后，可以多次掉用这个方法发送消息
        sengMessage(channel,null);
        //接收来自服务端消息WebSocketClientHandler.channelRead0,根据frame打印即可
    }

}
