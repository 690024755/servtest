package com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase.conTest; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase
 * @Date Create on 10:27
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/25日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.KfChatBO;
import com.galaxyeye.websocket.tool.netty.websocket.NettyWebSocketClientHandler;
import com.jayway.jsonpath.JsonPath;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebSocketClientHandlerTest extends NettyWebSocketClientHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("当前握手的状态"+this.handshaker.isHandshakeComplete());
        Channel ch = ctx.channel();
        FullHttpResponse response;
        if (!this.handshaker.isHandshakeComplete()) {
            try {
                response = (FullHttpResponse)msg;
                this.handshaker.finishHandshake(ch, response);
                this.handshakeFuture.setSuccess();
                log.info("服务端的消息"+response.headers());
            } catch (WebSocketHandshakeException var7) {
                FullHttpResponse res = (FullHttpResponse)msg;
                String errorMsg = String.format("握手失败,status:%s,reason:%s", res.status(), res.content().toString(CharsetUtil.UTF_8));
                this.handshakeFuture.setFailure(new Exception(errorMsg));
            }
        } else if (msg instanceof FullHttpResponse) {
            response = (FullHttpResponse)msg;
            throw new IllegalStateException("Unexpected FullHttpResponse (getStatus=" + response.status() + ", content=" + response.content().toString(CharsetUtil.UTF_8) + ')');
        } else {
            WebSocketFrame frame = (WebSocketFrame)msg;
            if (frame instanceof TextWebSocketFrame) {
                TextWebSocketFrame textFrame = (TextWebSocketFrame)frame;
                String resultMessage=textFrame.text();
                log.info("客户端接收的消息是:"+resultMessage);
                if(resultMessage.contains("kfConnResp") && !resultMessage.contains("queue")){
                    log.info("channelId="+ch.id()+",客户端接收的消息kfConnResp是:"+resultMessage);
                    String uid= JsonPath.read(resultMessage,"$.uid");
                    String chatPackSeq=JsonPath.read(resultMessage,"$.chatPackSeq");
                    NettyClientWebSocketTest.sengMessageByKfChatReq(ch,new KfChatBO(){
                        {setChatPackSeq(chatPackSeq);
                            setUid(Integer.valueOf(uid));
                        }
                    });
                }
            }
            if (frame instanceof BinaryWebSocketFrame) {
                BinaryWebSocketFrame binFrame = (BinaryWebSocketFrame)frame;
                log.info("BinaryWebSocketFrame");
            }
            if (frame instanceof PongWebSocketFrame) {
                log.info("WebSocket Client received pong");
            }
            if (frame instanceof CloseWebSocketFrame) {
                log.info("receive close frame");
                ch.close();
            }

        }
    }
}
