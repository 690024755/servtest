package com.galaxyeye.websocket.tool.mq; /*
 * Description:com.galaxyeye.websocket.test.mqTest
 * @Date Create on 14:39
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/18日galaxyeye All Rights Reserved.
 */

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Slf4j
public class TestReciever extends MqPerproty implements Destination {

    @Autowired
    private JmsTemplate recieveMessage;


    public void clearAllExistMessage() {
        while (getMessageCount() > 0) {
            log.info(getMessage());
        }
    }


    public String getMessage(){
        String test=null;
        Message message=recieveMessage.receive();
        try {
            TextMessage textMessage=(TextMessage)message;
            test=textMessage.getText();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            return test;
        }
    }

    public Integer getMessageCount(){
        AtomicInteger i=new AtomicInteger(0);
        List<String> contList = recieveMessage.browse(queue,
                new BrowserCallback<List<String>>() {
                    public List<String> doInJms(Session session,
                                                QueueBrowser browser) throws JMSException {
                        MessageConverter converter = new SimpleMessageConverter();
                        List<String> messContentList = new ArrayList<String>();
                        @SuppressWarnings("unchecked")
                        Enumeration<Message> messageEnum = browser
                                .getEnumeration();
                        while (messageEnum.hasMoreElements()) {
                            i.incrementAndGet();
                            Message msg = messageEnum.nextElement();
                            String text = (String) converter.fromMessage(msg);
                            messContentList.add(text);
                        }
                        return messContentList;
                    }
                });
        return i.get();
    }

    public void close(){
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
    }


    public static void main(String[] args) {
        MessageConsumer messageConsumer=null;
        Session session=null;
        Connection connection=null;
        try{
            ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory("tcp://172.16.0.25:61616");
            connection=activeMQConnectionFactory.createConnection();
            connection.start();

            session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            Queue queue=session.createQueue("queue_test");
            Queue queue=session.createQueue("10");

             messageConsumer=session.createConsumer(queue);
            Message message=messageConsumer.receive();
            TextMessage textMessage = (TextMessage) message;
            String result=textMessage.getText();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                messageConsumer.close();
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

}
