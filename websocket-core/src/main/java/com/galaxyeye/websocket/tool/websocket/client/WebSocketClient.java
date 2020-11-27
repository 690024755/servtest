package com.galaxyeye.websocket.tool.websocket.client;

import com.alibaba.fastjson.JSON;
import com.galaxyeye.websocket.test.galaxyeye.BaseTest;
import com.galaxyeye.websocket.tool.mq.TestProducer;
import com.galaxyeye.websocket.tool.websocket.client.message.WebsocketMessage;
import com.galaxyeye.websocket.tool.websocket.response.WebsocketResContentPara;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@ClientEndpoint
@Slf4j
@Component
public class WebSocketClient implements BeanFactoryAware {
    public void setTestProducer(TestProducer testProducer) {
        this.testProducer = testProducer;
    }

    @Autowired
    private TestProducer testProducer;

    private Session session = null;
    private Object lock = new Object();
    private static String  result = null;
    private CountDownLatch downLatch = null;
    private Map<String,List<Object>> map=new HashMap<>();
    private boolean receiveMultiple=false;
    private long waitTime=2000L;
    private BeanFactory factory;
    private URI uri;
    public static List<Map<String, String>> Contents =new ArrayList<Map<String, String>>();
    public static AtomicInteger index=new AtomicInteger(0);
    public static AtomicInteger getIndex() {
        return index;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static void setIndex(AtomicInteger index) {
        WebSocketClient.index = index;
    }


    public List<Map<String, String>> getContents() {
        return Contents;
    }

    public void setContents(List<Map<String, String>> contents) {
        Contents = contents;
    }



    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    public boolean isNotReceiveMultiple() {
        return receiveMultiple;
    }

    public void setReceiveMultiple(boolean receiveMultiple) {
        this.receiveMultiple = receiveMultiple;
    }

    @OnOpen
    public void onOpen(Session session)  {
        log.info("握手 ...");

    }


    @OnMessage
    public void onMessage(String message,Session session)  {
        try{
            if(!message.contains("heartbeat")){
                testProducer.sendToMqByTextMessage(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        result=message;
        log.info("websocket返回参数"+ message);
        if(receiveMultiple && !Contents.isEmpty()){
            String Content=Contents.get(index.get()).get("预期的应答");
            WebsocketResContentPara response=JSON.parseObject(message, WebsocketResContentPara.class);
            String Result=response.getContent();
            Contents.get(index.get()).put("等待结果",Result+response.getAction());
            String CompareResult=Contents.get(index.get()).get("校验结果 ");
            if (Content.equals(Result)){
                CompareResult="true";
                Contents.get(index.get()).put("校验结果",CompareResult);
            }else {
                CompareResult="false";
                Contents.get(index.get()).put("校验结果",CompareResult);
            }
            log.info("ContentMap  Asy=====："+ JSON.toJSONString(Contents.get(index.get())));
            WebsocketMessage.saveMultipleMessage(message);
            index.incrementAndGet();
        }else if(!receiveMultiple && !Contents.isEmpty()){
            WebsocketResContentPara response=JSON.parseObject(message, WebsocketResContentPara.class);
            String Result=response.getContent();
            String Content=Contents.get(index.get()).get("预期的应答");
            Contents.get(index.get()).put("等待结果",Result+response.getAction());
            String CompareResult=Contents.get(index.get()).get("校验结果 ");
            if (Content.equals(Result)){
                CompareResult="true";
                Contents.get(index.get()).put("校验结果",CompareResult);
            }else {
                CompareResult="false";
                Contents.get(index.get()).put("校验结果",CompareResult);
            }
            log.info("ContentMap Sy=====："+ JSON.toJSONString(Contents.get(index.get())));
            index.incrementAndGet();
        }
        if(downLatch!=null){
            downLatch.countDown();
        }
    }


    public String sendMessageSy(Object obj,long timeout,int count)  {
        synchronized (lock){
            try {
                sendImpl(obj);
                if(timeout>0 || count>0){
                    downLatch = new CountDownLatch(count);
                    downLatch.await(timeout, TimeUnit.SECONDS);
                }else {
                    downLatch = new CountDownLatch(10);
                    downLatch.await(1, TimeUnit.SECONDS);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                try {
                    this.session.getBasicRemote().flushBatch();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }
        }
    }

    public String sendMessageSy(Object obj)  {
        synchronized (lock){
            downLatch = new CountDownLatch(1);
            try {
                sendImpl(obj);
                downLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                try {
                    this.session.getBasicRemote().flushBatch();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }
        }
    }

    private void sendImpl( Object object ) throws IOException, EncodeException {
        if(object instanceof String){
            this.session.getBasicRemote().sendText((String)object);
        }else if(object instanceof Object){
            this.session.getBasicRemote().sendObject(object);
        }
    }


    @OnClose
    public void onClose() {
        if(this.session.isOpen()){
            try {
                this.session.close();
            } catch (IOException e) {
                log.error("session close 关闭: " ,e);
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session,Throwable thr){
        log.error("websocket Error："+ thr.getStackTrace());
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public WebSocketClient() {
    }

    public void getWebSocketClient(URI uri) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();	// 获得WebSocketContainer
            this.session = container.connectToServer(WebSocketClient.class,uri );	// 该方法会阻塞
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WebSocketClient(String endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();	// 获得WebSocketContainer
            this.session = container.connectToServer(this, new URI(endpointURI) );	// 该方法会阻塞
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WebSocketClient(URI uri) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();	// 获得WebSocketContainer
            this.session = container.connectToServer(WebSocketClient.class, uri );	// 该方法会阻塞
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    public Session getSession() {
        return session;
    }



    public void sendMessageAsy(Object obj)  {
        try {
            sendImpl(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }finally {
            try {
                this.session.getBasicRemote().flushBatch();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }





    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.factory=beanFactory;

    }


    public static void main(String[] args) {

        WebSocketClient webSocketClientSy=null;
        try {
            String uriSy = "ws://echo.websocket.org/";
            webSocketClientSy=new WebSocketClient(uriSy);
            for (int i = 0; i < 3; i++) {
                String message = "Hello " + i;
                String result=webSocketClientSy.sendMessageSy(message);
                System.out.println("send message= " + message);
                System.out.println("receive message= " + result);
            }
            webSocketClientSy.onClose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        WebSocketClient webSocketClientAsy=null;
        try {
            String uriAsy = "ws://echo.websocket.org/";
            webSocketClientAsy=new WebSocketClient(uriAsy);
            for (int i = 0; i < 3; i++) {
                String message = "Hello " + i;
                webSocketClientAsy.sendMessageAsy(message);
                System.out.println("send message= " + message);
                Thread.sleep(5000);
            }
            webSocketClientAsy.onClose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
