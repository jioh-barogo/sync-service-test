package cli.service;

import cli.TestRetrofitInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CreateRequest;
import net.devh.boot.grpc.examples.lib.TestRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@SpringBootTest
class PerformanceTest {

  @Value("${api-server}")
  String testUrl;

  @Autowired
  private RestTemplate poolRestTemplate;

  @Autowired
  private RestTemplate nonPoolRestTemplate;

  @Autowired
  @Qualifier("retrofit")
  private TestRetrofitInterface retrofit;

  @Autowired
  @Qualifier("retrofitPool")
  private TestRetrofitInterface retrofitPool;

  @Autowired
  private GrpcClientService grpcClientService;

  @Autowired
  private KafkaProducerService kafkaProducerService;

  @Autowired
  private ObjectMapper objectMapper;

  public static class CallPerformance {
    String name;
    Double time;

    public CallPerformance(String name, Double time) {
      this.name = name;
      this.time = time;
    }

    public static Builder builder() {
      return new Builder();
    }

    public static class Builder {
      private String name;
      private Double time;

      public Builder() {
      }

      public Builder name(String name) {
        this.name = name;
        return this;
      }

      public Builder time(Double time) {
        this.time = time;
        return this;
      }

      public CallPerformance build() {
        return new CallPerformance(name, time);
      }
    }

    public String getName() {
      return name;
    }

    public Double getTime() {
      return time;
    }

    @Override
    public String toString() {
      return "name='" + name + '\'' +
          ", time=" + time;
    }
  }


  @DisplayName("TEST!!!")
  @Test
  void testEachCall() throws Exception {
    // given
    var list = List.of(
        CallPerformance.builder().name("RestTemplate-Connection-Pool").time(testRestTemplate()).build(),
        CallPerformance.builder().name("RestTemplate-Non-Connection-Pool").time(testRestTemplate2()).build(),
        CallPerformance.builder().name("retrofit-Non-Connection-Pool").time(retrofit()).build(),
        CallPerformance.builder().name("retrofit2-Connection-Pool").time(retrofitPool()).build(),
        CallPerformance.builder().name("GRPC").time(testGrpc()).build(),
        CallPerformance.builder().name("KAFKA").time(testKafka()).build()
    );
    // when
    var rank = new AtomicInteger(1);
    list.stream().sorted(Comparator.comparing(CallPerformance::getTime))
        .forEach(cp -> System.out.println(rank.getAndIncrement() + " : " + cp));
    // the
  }

  @DisplayName("RestTemplate-Connection-Pool")
  Double testRestTemplate() {
    long start = System.currentTimeMillis();
    IntStream.range(0, 10000).forEach(i ->
        poolRestTemplate.postForEntity(testUrl + "/test", getCreateRequest(), Void.class)
    );
    long end = System.currentTimeMillis();
    return (end - start) / 1000.0;
  }

  @DisplayName("RestTemplate-Non-Connection")
  Double testRestTemplate2() {
    long start = System.currentTimeMillis();
    IntStream.range(0, 10000).forEach(i ->
        nonPoolRestTemplate.postForEntity(testUrl + "/test", getCreateRequest(), Void.class)
    );
    long end = System.currentTimeMillis();
    return (end - start) / 1000.0;
  }

  /**
   * 왜 안되지??....
   * @return
   */
  @DisplayName("Retrofit2-Connection-Pool")
  Double retrofitPool() {
    long start = System.currentTimeMillis();
    IntStream.range(0, 10000).forEach(i ->
        {
          try {
            retrofitPool.testPost(getCreateRequest()).execute();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
    );
    long end = System.currentTimeMillis();
    return (end - start) / 1000.0;
  }

  @DisplayName("Retrofit2-Non-Connection")
  Double retrofit() {
    long start = System.currentTimeMillis();
    IntStream.range(0, 10000).forEach(i ->
        {
          try {
            retrofit.testPost(getCreateRequest()).execute();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
    );
    long end = System.currentTimeMillis();
    return (end - start) / 1000.0;
  }

  @DisplayName("4. grpc test")
  Double testGrpc() {
    long start = System.currentTimeMillis();
    IntStream.range(0, 10000).forEach(i ->
        grpcClientService.sendParam(testRequest())
    );
    long end = System.currentTimeMillis();
    return (end - start) / 1000.0;
  }

  @DisplayName("5. kafka test")
  Double testKafka() {
    long start = System.currentTimeMillis();
    IntStream.range(0, 10000).forEach(i ->
        {
          try {
            kafkaProducerService.replyingSend(objectMapper.writeValueAsString(getCreateRequest()));
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }
    );
    long end = System.currentTimeMillis();
    return (end - start) / 1000.0;
  }

  private static CreateRequest getCreateRequest() {
    return CreateRequest.builder()
        .partnerOrderId("partnerOrderId")
        .orderAgencyOrderId("orderAgencyOrderId")
        .orderAgencyTodayOrderId("orderAgencyTodayOrderId")
        .partnerStoreId("partnerStoreId")
        .dropJibunAddress("dropJibunAddress")
        .dropRoadAddress("dropRoadAddress")
        .dropAddressDetail("dropAddressDetail")
        .dropLocation("dropLocation")
        .dropAddressInfo("dropAddressInfo")
        .customerPhone("customerPhone")
        .ordererSubPhone("ordererSubPhone")
        .customerName("customerName")
        .receiverPhone("receiverPhone")
        .receiverSubPhone("receiverSubPhone")
        .receiverName("receiverName")
        .pickupExpectedAt(1123123L)
        .isReservation(Boolean.FALSE)
        .dropExpectedAt(321321L)
        .reservedOrderDisplayTime(33333L)
        .totalPayPrice(3333)
        .orderAmount(BigDecimal.TEN)
        .prePayAmount(BigDecimal.TEN)
        .deferredPrice(12312313)
        .payCashAmount(BigDecimal.TEN)
        .payCardAmount(BigDecimal.TEN)
        .taxFreePayAmount(BigDecimal.TEN)
        .goodsNames("goodsNames")
        .orderGoods(List.of("goods1", "goods2", "goods3", "goods4", "goods5", "goods6"))
        .storeOrderMemo("storeOrderMemo")
        .memo("memo")
        .orderAgencyOrderCreatedAt(1231231231L)
        .orderChannel("orderChannel")
        .isUntact(Boolean.TRUE)
        .shouldScanQRCodeForPickup(Boolean.FALSE)
        .timeOutAt(1111L)
        .build();
  }

  public static TestRequest testRequest() {
    return TestRequest.newBuilder()
        .setPartnerOrderId("partnerOrderId")
        .setOrderAgencyOrderId("orderAgencyOrderId")
        .setOrderAgencyTodayOrderId("orderAgencyTodayOrderId")
        .setPartnerStoreId("partnerStoreId")
        .setDropJibunAddress("dropJibunAddress")
        .setDropRoadAddress("dropRoadAddress")
        .setDropAddressDetail("dropAddressDetail")
        .setDropLocation("dropLocation")
        .setDropAddressInfo("dropAddressInfo")
        .setCustomerPhone("customerPhone")
        .setOrdererSubPhone("ordererSubPhone")
        .setCustomerName("customerName")
        .setReceiverPhone("receiverPhone")
        .setReceiverSubPhone("receiverSubPhone")
        .setReceiverName("receiverName")
        .setPickupExpectedAt(123)
        .setIsReservation(Boolean.FALSE)
        .setDropExpectedAt(321321L)
        .setReservedOrderDisplayTime(33333L)
        .setTotalPayPrice(3333)
        .setOrderAmount(BigDecimal.TEN.scale())
        .setPrePayAmount(BigDecimal.TEN.scale())
        .setDeferredPrice(12312313)
        .setPayCashAmount(BigDecimal.TEN.scale())
        .setPayCardAmount(BigDecimal.TEN.scale())
        .setTaxFreePayAmount(BigDecimal.TEN.scale())
        .setGoodsNames("goodsNames")
        .addAllOrderGoods(List.of("goods1", "goods2", "goods3", "goods4", "goods5", "goods6"))
        .setStoreOrderMemo("storeOrderMemo")
        .setMemo("memo")
        .setOrderAgencyOrderCreatedAt(1231231231L)
        .setOrderChannel("orderChannel")
        .setIsUntact(Boolean.TRUE)
        .setShouldScanQRCodeForPickup(Boolean.FALSE)
        .setTimeOutAt(1111L)
        .build();
  }

}