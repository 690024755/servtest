package com.galaxyeye.websocket.tool.netty.tcp; /*
 * Description:com.galaxyeye.websocket.tool.netty.tcp
 * @Date Create on 12:23
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/25日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class NettyTcpClient {

    private static ChannelFuture channelFuture =null;
    private static EventLoopGroup group =null;

    private static void initChannel(String tcpurl) {
        group = new NioEventLoopGroup();
        try{
            URI tcp=new URI(tcpurl);
            Bootstrap clientBootstrap = new Bootstrap();
            clientBootstrap.group(group);
            clientBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            clientBootstrap.option(ChannelOption.TCP_NODELAY, true);
            clientBootstrap.channel(NioSocketChannel.class);
            clientBootstrap.remoteAddress(new InetSocketAddress(tcp.getHost(), tcp.getPort()));
            clientBootstrap.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyTcpClientHandler());
                }
            });
            channelFuture = clientBootstrap.connect().sync();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void sengMessageByByteArray(Channel channel, byte[] data) {
        byte[] tmp=data;
        ByteBuf frame= Unpooled.buffer();
        frame.writeBytes(data);
        channel.writeAndFlush(frame).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    log.info("sengMessageByByteArray消息发送成功:" + new String(tmp,"UTF-8"));
                } else {
                    log.info("sengMessageByByteArray消息发送失败:" + channelFuture.cause().getMessage());
                }
            }
        });
    }

    public static void sengMessageByString(String data,int cmd) throws Exception {
        ByteBuf frame= Unpooled.buffer();
        frame.writeBytes(TcpMessageUtil.getMessage(data,cmd));
        channelFuture.channel().writeAndFlush(frame).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    Map<String,Object> hs=new HashMap<>();
                    hs.put("cmd",cmd);
                    hs.put("data",JSON.parse(data));
                    log.info("sengMessageByByteArray消息发送成功:" + JSON.toJSONString(hs));
                } else {
                    log.info("sengMessageByByteArray消息发送失败:" + channelFuture.cause().getMessage());
                }
            }
        });
    }


    public static String sendRegisterPack(String appidValue ) throws Exception {
        HashMap<String,Object> hs=new HashMap<String,Object>();
        hs.put("appid",appidValue);
        hs.put("servType",1);
        return JSON.toJSONString(hs);
    }




    public static void main(String[] args) throws Exception {
        String tcpurl="tcp://172.16.0.25:2345";
        initChannel(tcpurl);
        sengMessageByString(sendRegisterPack("1.00002"),201);
        String tt=getMessage(channelFuture.channel().alloc().buffer());
        System.out.println(tt);
        group.shutdownGracefully().sync();

    }

    private static String getMessage(ByteBuf buf) {
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
