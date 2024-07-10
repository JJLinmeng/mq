import com.itheima.publisher.PublisherApplication;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PublisherApplication.class)
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void testSimpleQueue() {
        // 队列名称
        String queueName = "simple.queue";
        // 消息
        String message = "hello, spring amqp!";
        // 发送消息
        rabbitTemplate.convertAndSend(queueName, message);
    }


    public void testWorkQueue() throws InterruptedException {
        String queueName="work.queue";
        String message="hello message_";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName,message+i);
            Thread.sleep(20);
        }
    }

//    @Test
    public void testFanoutExchange() {
        // 交换机名称
        String exchangeName = "test1.fanout";
        // 消息
        String message = "hello, everyone!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }
    @Test
    public void testSendDirectExchange() {
        // 交换机名称
        String exchangeName = "test.direct";
        // 消息
        String message = "蓝色警报！日本乱排核废水，导致海洋生物变异，惊现哥斯拉！";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "yellow", message);
    }
}