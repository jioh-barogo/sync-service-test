package serv.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import serv.property.KafkaProperty;

@Slf4j
@Service
public class KafkaConsumerService {

  @KafkaListener(groupId = "#{@kafkaProperty.getConsumerGroupId()}", topics = "#{@kafkaProperty.getTopic().getRequestTopic()}")
  public Message<String> listen(ConsumerRecord<String, String> in, @Header(KafkaHeaders.REPLY_TOPIC) byte[] replyTo,
                                @Header(KafkaHeaders.CORRELATION_ID) byte[] correlation) {
//    log.info("{} :Server received: [key: {}, value: {}]", Thread.currentThread().getName(), in.key(), in.value());
    return MessageBuilder.withPayload(in.value())
        .setHeader(KafkaHeaders.TOPIC, replyTo)
        .setHeader(KafkaHeaders.CORRELATION_ID, correlation)
        .setHeader(KafkaHeaders.MESSAGE_KEY, "ReturnValue")
        .build();
  }

}