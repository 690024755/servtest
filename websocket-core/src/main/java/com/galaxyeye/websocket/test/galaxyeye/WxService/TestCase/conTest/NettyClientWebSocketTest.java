package com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase.conTest; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase
 * @Date Create on 10:19
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/24日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.ResetPassBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.BO.UserLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase.LoginTest;
import com.galaxyeye.websocket.test.galaxyeye.Acc.TestCase.ResetPassTest;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.test.galaxyeye.CustomReport.Enum.ModuleNameEnum;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.KfChatBO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.KfConnBO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.BO.WxServiceReqLoginBO;
import com.galaxyeye.websocket.test.galaxyeye.WxService.TestCase.QueueInfoReqTest;
import com.galaxyeye.websocket.tool.jedis.JedisTemplate;
import com.galaxyeye.websocket.tool.netty.websocket.NettyWebSocketClient;
import com.galaxyeye.websocket.tool.netty.websocket.NettyWebSocketClientHandler;
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
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@Component
public class NettyClientWebSocketTest extends BaseTest {

    public static ConcurrentHashMap<Integer, Channel> webSocketClientCustomMap = new ConcurrentHashMap();
    private static final Integer numCus = 100;
    private static final Integer threadNum = 100;
    private static  List<ConcurrentHashMap<String, String>> userList = new ArrayList<>();
    public static  AtomicInteger runNum = new AtomicInteger(0);

    @Autowired
    private JedisTemplate jedisTemplate;

    @Autowired
    private LoginTest loginTest;

    @Autowired
    private ResetPassTest resetPassTest;

    @Autowired
    private QueueInfoReqTest queueInfoReqTest;

    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("/META-INF/spring/websocket-context.xml");
        NettyClientWebSocketTest nettyClientTest = app.getBean(NettyClientWebSocketTest.class);
        nettyClientTest.initData();
        nettyClientTest.conTest();
    }


    @Test
    public void conTest() {
        queueInfoReqTest.clearQueueInfo();
        try {
            initLogin();
            for (int j = 0; j < threadNum; j++) {
                getExecutor().execute(new Runnable() {
                    @SneakyThrows
                    @Override
                    public void run() {
                        int yy= runNum.getAndIncrement();
                        sengMessageByKfConnReq(webSocketClientCustomMap.get(yy),new KfConnBO(),yy);

                    }
                });
            }
            Boolean flag1 = false;
            while (!flag1) {
                log.info("继续运行");
                Thread.sleep(30000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("测试结束！！！！！！！！！");
            System.exit(0);
        }
    }


    private void initLogin() throws Exception {
        Map<String, String> statistics = new HashMap<>();
        statistics.put("channelNo", "小程序服务端渠道编号");
        statistics.put("维度一", "渠道编号");
        statistics.put("维度二", "28");
        for (int i = 0; i < numCus; i++) {
            ConcurrentHashMap<String, String> userMap = new ConcurrentHashMap();
            UserLoginBO userLoginBO = new UserLoginBO();
            userLoginBO.setUname("Unionid_" + i);
            userLoginBO.setPasswd("");
            userLoginBO.setAppid("1.00002");
            userLoginBO.setKeytp("unionid");
            UserLoginBO.Keys keys = new UserLoginBO.Keys();
            keys.setOpenid("Unionid_" + i);
            List<UserLoginBO.Keys> list = new ArrayList<>();
            list.add(keys);
            userLoginBO.setKeys(list);
            userLoginBO.setThirdlogin(false);
            userLoginBO.setSeq(UUID.randomUUID().toString());
            String userLoginResult = loginTest.LoginByGernal(userLoginBO);
            String token = JSON.parseObject(userLoginResult).getString("token");
            String uid = JSON.parseObject(userLoginResult).getString("uid");
            String uname = JSON.parseObject(userLoginResult).getString("uname");
            String appid = userLoginBO.getAppid();
            userMap.put("token",token);
            userMap.put("uid",uid);
            userMap.put("uname",uname);
            userMap.put("appid",appid);
            userList.add(userMap);
            ResetPassBO resetPassBO = new ResetPassBO();
            resetPassBO.setUname(uname);
            resetPassBO.setPasswd("123456");
            resetPassBO.setAppid(appid);
            resetPassTest.resetPass(resetPassBO);

            String customerServInfoKey=uid+":1.00002:customerServInfo";
            Object customerServInfo=jedisTemplate.get(customerServInfoKey);
            if(customerServInfo!=null){
                jedisTemplate.del(customerServInfoKey);
            }
            String chatOfflineKey=uid+":1.00002:customerServInfo";
            Object chatOfflineInfo=jedisTemplate.get(chatOfflineKey);
            if(chatOfflineInfo!=null){
                jedisTemplate.del(chatOfflineKey);
            }
            WxServiceReqLoginBO wxServiceReqLoginBO=new WxServiceReqLoginBO();
            wxServiceReqLoginBO.setBmAppid(userLoginBO.getAppid());
            wxServiceReqLoginBO.setMethod("loginReq");
            wxServiceReqLoginBO.setPasswd(resetPassBO.getPasswd());
            wxServiceReqLoginBO.setUname(resetPassBO.getUname());
            wxServiceReqLoginBO.setStatistics(statistics);
            sengMessageByLogin(webSocketClientCustomMap.get(i),wxServiceReqLoginBO);
        }
    }

    public static void sengMessageByLogin(Channel channel,WxServiceReqLoginBO wxServiceReqLoginBO){
        log.info("websocket登录请求参数" + JSON.toJSONString(wxServiceReqLoginBO));
        TextWebSocketFrame frame = new TextWebSocketFrame(JSON.toJSONString(wxServiceReqLoginBO));
        channel.writeAndFlush(frame).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    log.info("websocket登录请求参数,消息发送成功"+JSON.toJSONString(wxServiceReqLoginBO));
                } else {
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }


    public static void sengMessageByKfConnReq(Channel channel, KfConnBO kfConnBO,Integer i){
        if(i<numCus){
            kfConnBO.setBmAppid(userList.get(i).get("appid"));
            kfConnBO.setUid(Integer.valueOf(userList.get(i).get("uid")));
            String chatPackSeq = null;
            kfConnBO.setMethod("kfConnReq");
            kfConnBO.setWxAppid("1234567812345678");
            List<KfConnBO.ContextBean> context = new ArrayList<>();
            KfConnBO.ContextBean contextBean = new KfConnBO.ContextBean();
            List<String> msg = new ArrayList<>();
            msg.add("第"+i+"个用户uid="+userList.get(i).get("uid")+"接入消息，ChannelId="+channel.id());
            contextBean.setMsgType("common");
            contextBean.setType("user");
            contextBean.setMsg(msg);
            context.add(contextBean);
            kfConnBO.setContext(context);
            kfConnBO.setWxAppid("wxd996989304eaa926");
            TextWebSocketFrame frame = new TextWebSocketFrame(JSON.toJSONString(kfConnBO));
            channel.writeAndFlush(frame).addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        log.info("第"+i+"个客户"+",sengMessageByKfConnReq消息发送成功"+JSON.toJSONString(kfConnBO));
                    } else {
                        log.info("第"+i+"个客户"+",sengMessageByKfConnReq消息发送失败" + channelFuture.cause().getMessage());
                    }
                }
            });
        }
    }



    public static void sengMessageByKfChatReq(Channel channel, KfChatBO kfChatBO){
        kfChatBO.setBmAppid(userList.get(0).get("appid"));
        kfChatBO.setMethod("kfchatReq");
        kfChatBO.setWxAppid("1234567812345678");
        kfChatBO.setMsgType("common");
        kfChatBO.setContent("chatPackSeq="+kfChatBO.getChatPackSeq()+",ChannelId="+channel.id()+",我的uid="+kfChatBO.getUid() + ",我无聊，我要找客服聊天");
        TextWebSocketFrame frame = new TextWebSocketFrame(JSON.toJSONString(kfChatBO));
        channel.writeAndFlush(frame).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    log.info("sengMessageByKfChatReq,消息发送成功"+JSON.toJSONString(kfChatBO));
                } else {
                    log.info("sengMessageByKfChatReq,消息发送失败" + channelFuture.cause().getMessage());
                }
            }
        });
    }

    public ThreadPoolTaskExecutor getExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.initialize();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(500);
        return executor;
    }


    @Override
    public String initUrl() {
        return urls.get(ModuleNameEnum.getName(12));
    }

    public void connect(Bootstrap boot,WebSocketClientHandshaker handshaker,Integer k,String host, int port) {
        boot.connect(host, port).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {

                Channel channel=future.channel();
                NettyWebSocketClientHandler handler = (WebSocketClientHandlerTest) channel.pipeline().get("hookedHandler");
                handler.setHandshaker(handshaker);
                handshaker.handshake(channel);
                handler.handshakeFuture();
                if (future.isSuccess()) {
                    log.info("第"+k+"个握手成功");
                    webSocketClientCustomMap.put(k, channel);
                }
            }
        });
    }


    @Override
    public void initData() {
        NettyWebSocketClient.initHeader();
        String wsUrlSy = url;
        EventLoopGroup group = new NioEventLoopGroup();
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
                        pipeline.addLast("http-codec",new HttpClientCodec());
                        pipeline.addLast("aggregator",new HttpObjectAggregator(1024*1024*10));
                        pipeline.addLast("hookedHandler", new WebSocketClientHandlerTest());
                    }
                });
        try{
            URI websocketURI = new URI(wsUrlSy);
            HttpHeaders httpHeaders = new DefaultHttpHeaders();
            for (int k = 0; k < numCus; k++) {
                WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(websocketURI, WebSocketVersion.V13, (String) null, true, httpHeaders);
                connect(boot, handshaker, k,websocketURI.getHost(),websocketURI.getPort());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void destroyData() {

    }

}
