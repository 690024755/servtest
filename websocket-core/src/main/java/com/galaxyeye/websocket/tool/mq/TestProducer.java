package com.galaxyeye.websocket.tool.mq; /*
 * Description:com.galaxyeye.websocket.test.mqTest
 * @Date Create on 14:40
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/18日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.tool.websocket.response.WebsocketResLoginPara;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

@Data
@Slf4j
public class TestProducer extends MqPerproty {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ActiveMQConnectionFactory activeMQConnectionFactory;

    public void sendToMqByObjectMessage(final WebsocketResLoginPara websocketResLoginPara){
        jmsTemplate.send(queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createObjectMessage(websocketResLoginPara);
                return message;
            }
        });
    }

    public void sendToMqByTextMessage(final String content){
        jmsTemplate.send(queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage=session.createTextMessage(content);
                return textMessage;
            }
        });
    }

    public void close(){
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
    }

    public static void main(String[] args) {
//生成者，产生一条消息
        try{
            //创建连接工厂
            ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory("tcp://172.16.0.25:61616");
            //创建连接
            Connection connection=activeMQConnectionFactory.createConnection();
            //连接
            connection.start();

            //创建会话
            Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建队列，在控制台可以看到queue中有queue_test队列
            Queue queue=session.createQueue("queue_test");
            //创建消息生产者
            MessageProducer messageProducer=session.createProducer(queue);
            //创建消息对象
            String msg = "千里之行，始于足下";
            Message message = session.createTextMessage(msg);
            //发送消息
            messageProducer.send(message);

            //关闭所有连接
            messageProducer.close();
            session.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
