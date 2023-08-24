package guojiuhe.demo.rocketmq;

import javax.annotation.Resource;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq")
public class ProducerController {
    //@Resource
    private RocketMQTemplate rocketMQTemplate;
    
    // Producer、 Consumer、 Broker 、NameServer
    // NameServer ： mqnamesrv.cmd
    // brocker : mqbroker.cmd -n localhost:9876 autoCreateTopicEnable=true
    
    @GetMapping("/sync/send1")
    public String syncSendString() {
    	//http://localhost:8080/mq/sync/send1
    	for (int i = 0; i < 100; i++) {
    		syncSend("topic1", "Hello RocketMQ" + i);
    	}
        return "消息发送完成";
    }
    
    @GetMapping("/send/ordered")
    public String sendOrderedMsg(){
        /**
         * hashKey: 为了保证报到同一个队列中，将消息发送到orderTopic主题上
         */
        rocketMQTemplate.syncSendOrderly("orderTopic","no1","order");
        rocketMQTemplate.syncSendOrderly("orderTopic","no2","order");
        rocketMQTemplate.syncSendOrderly("orderTopic","no3","order");
        rocketMQTemplate.syncSendOrderly("orderTopic","no4","order");
        rocketMQTemplate.syncSendOrderly("orderTopic","no5","order");
        return "success";
    }
    
    /**
     * 普通字符串消息
     */
    public void sendMessage(String topic, String message) {
        rocketMQTemplate.convertAndSend(topic, message);
    }

    /**
     * 同步消息
     * producer向 broker 发送消息后同步等待， 直到broker 服务器返回发送结果
     */
    public void syncSend(String topic, String message) {
        SendResult sendMessage = rocketMQTemplate.syncSend(topic, message);
        System.out.println(sendMessage);
    }

    /**
     * 异步消息
     * producer向 broker 发送消息注册回调方法，调用 API 后立即返回，消息发送成功或失败的回调任务在一个新的线程中执行
     */
    public void asyncSend(String topic, String message) {
        SendCallback callback = new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("消息发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("消息发送失败");
            }
        };
        rocketMQTemplate.asyncSend(topic, message, callback);
    }

    /**
     * 单向消息
     * producer向 broker 发送消息，执行 API 时直接返回，不等待broker 服务器的结果 ，也不注册回调函数
     */
    public void onewaySend(String topic, String message) {
        rocketMQTemplate.sendOneWay(topic, message);
    }
}