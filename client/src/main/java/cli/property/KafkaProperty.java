package cli.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@EnableConfigurationProperties(KafkaProperty.class)
@ConfigurationProperties("kafka")
public class KafkaProperty {

  private String bootstrapServers;
  private String consumerGroupId;
  private String consumerGroup;
  private Topic topic;
  private Integer receivePartition;
  private Integer sendPartition;
  private Integer ack;

  @Getter
  @Setter
  public static class Topic {
    private String requestTopic;
    private String requestReplyTopic;
  }

}
