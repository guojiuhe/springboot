package guojiuhe.demo.rocketmq;

import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费方
 * 1.如果两个消费者group和topic都一样，则二者轮循接收消息
 * 2.如果两个消费者topic一样，而group不一样，则消息变成广播机制
 * RocketMQListener<>泛型必须和接收的消息类型相同
 */
@Component
//@RocketMQMessageListener(topic = "topic1", consumerGroup = "test-group",messageModel = MessageModel.CLUSTERING, consumeMode = ConsumeMode.ORDERLY)
public class ConsumerController implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("消费者接收消息：" + message);
    }
    
    //消费者：消费者在消费的时候，默认是异步多线程消费的，所以无法保证顺序，需要指定同步消费。指定 consumeMode = ConsumeMode.ORDERLY。默认值是 consumeMode = ConsumeMode.CONCURRENT。
}