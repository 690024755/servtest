package com.galaxyeye.websocket.tool.netty.tcp; /*
 * Description:com.galaxyeye.websocket.tool.netty.tcp
 * @Date Create on 13:12
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/25日galaxyeye All Rights Reserved.
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.UnsupportedEncodingException;
@Component
@Slf4j
public class NettyTcpClientHandler extends SimpleChannelInboundHandler {


    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) {
        cause.printStackTrace();
        channelHandlerContext.close();
    }


    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof ByteBuf){
            ByteBuf buf = (ByteBuf) msg;
            String rev = getMessage(buf);
            log.info("客户端收到服务器数据:" + rev);
        }else {
            System.out.println("消息格式解析错误！");
        }
    }

    private String getMessage(ByteBuf buf) {
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
