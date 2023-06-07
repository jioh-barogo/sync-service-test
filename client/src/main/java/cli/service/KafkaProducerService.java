package cli.service;

import cli.property.KafkaProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

  private final ReplyingKafkaTemplate<String, String, String> replyKafkaTemplate;
  private final AdminClient adminClient;
  private final KafkaProperty kafkaProperty;

  public String replyingSend(String message) throws Exception {

    // Create Topic
//    adminClient.createTopics(List.of(new NewTopic(kafkaProperty.getTopic().getRequestTopic(), 0, (short) 1)));
    // Create Request and receive reply

    ProducerRecord<String, String> producerRecord = new ProducerRecord<>(kafkaProperty.getTopic().getRequestTopic(), 0, "", message);
    producerRecord.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, kafkaProperty.getTopic().getRequestReplyTopic().getBytes(StandardCharsets.UTF_8)));
    RequestReplyFuture<String, String, String> replyFuture = replyKafkaTemplate.sendAndReceive(producerRecord, Duration.ofSeconds(30000));
    SendResult<String, String> sendResult = replyFuture.getSendFuture().get();
//    log.info("Sent ok: {}", sendResult.getRecordMetadata());
    ConsumerRecord<String, String> consumerRecord = replyFuture.get();
//    log.info("Return value: {}", consumerRecord.value());
    return consumerRecord.value();
  }


}
