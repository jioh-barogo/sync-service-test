package cli.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import cli.property.KafkaProperty;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@RequiredArgsConstructor
public class KafkaConfig {

  private final KafkaProperty kafkaProperty;

  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperty.getBootstrapServers());
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return props;
  }

  @Bean
  public Map<String, Object> consumerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperty.getBootstrapServers());
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "hello2");
    return props;
  }

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public ReplyingKafkaTemplate<String, String, String> replyKafkaTemplate(ProducerFactory<String, String> pf,
                                                                          ConcurrentMessageListenerContainer<String, String> replyContainer) {
    return new ReplyingKafkaTemplate<>(pf, replyContainer);
  }

  @Bean
  public KafkaAdmin kafkaAdmin() {
    return new KafkaAdmin(Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperty.getBootstrapServers()));
  }

  @Bean
  public ConcurrentMessageListenerContainer<String, String> replyContainer(ConcurrentKafkaListenerContainerFactory<String, String> factory,
                                                                           KafkaTemplate<String, String> kafkaTemplate) {
    factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new StringDeserializer()));
    factory.setReplyTemplate(kafkaTemplate);
    ConcurrentMessageListenerContainer<String, String> repliesContainer = factory.createContainer(kafkaProperty.getTopic().getRequestReplyTopic());
    repliesContainer.setAutoStartup(false);
    return repliesContainer;
  }

  @Bean
  public NewTopic requestTopic() {
    return TopicBuilder.name(kafkaProperty.getTopic().getRequestTopic())
        .partitions(kafkaProperty.getSendPartition())
        .replicas(1)
        .build();
  }

  @Bean
  public AdminClient adminClient(KafkaAdmin kafkaAdmin) {
    return AdminClient.create(kafkaAdmin.getConfig());
  }

}
