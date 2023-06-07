package cli.service;

import cli.TestRetrofitInterface;
import cli.service.GrpcClientService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Disabled
class GrpcClientServiceTest {

  @Value("${api-server}")
  String testUrl;

  @Autowired
  private RestTemplate poolRestTemplate;

  @Autowired
  private RestTemplate nonPoolRestTemplate;

  @Autowired
  private TestRetrofitInterface retrofit;

  @Autowired
  private GrpcClientService grpcClientService;

  public static class CallPerformance {
    String name;
    Double time;

    public CallPerformance(String name, Double time) {
      this.name = name;
      this.time = time;
    }

    public static Builder builder() {
      return new CallPerformance.Builder();
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


  @DisplayName("rest, pool rest, retrofit, grpc performance test")
  @Test
  void testEachCall() {
    // given
    var list = List.of(
        CallPerformance.builder().name("pool restTemplate").time(testRestTemplate()).build(),
        CallPerformance.builder().name("non pool restTemplate ").time(testRestTemplate2()).build(),
        CallPerformance.builder().name("retrofit2").time(retrofit()).build(),
        CallPerformance.builder().name("grpc").time(testGrpc()).build()
    );
    // when
    list.stream().sorted(Comparator.comparing(CallPerformance::getTime)).forEach(System.out::println);
    // the
  }

  @DisplayName("1. testRestTemplate - connection pool")
  Double testRestTemplate() {

    // given
    long start = System.currentTimeMillis();

    // when
    IntStream.range(0, 10000).forEach(i ->
        poolRestTemplate.getForEntity(testUrl + "/test/" + i, String.class)
    );

    // then
    long end = System.currentTimeMillis();
//    System.out.println("실행 시간 : " + (end - start) / 1000.0 + "초");
    return (end - start) / 1000.0;
  }

  @DisplayName("2. testRestTemplate - non connection pool")
  Double testRestTemplate2() {
    // given
    long start = System.currentTimeMillis();
    // when
    IntStream.range(0, 10000).forEach(i ->
        nonPoolRestTemplate.getForEntity(testUrl + "/test/" + i, String.class)
    );
    // then
    long end = System.currentTimeMillis();
//    System.out.println("실행 시간 : " + (end - start) / 1000.0 + "초");
    return (end - start) / 1000.0;
  }

  @DisplayName("3. retrofit")
  Double retrofit() {
    // given

    long start = System.currentTimeMillis();

    // when
    IntStream.range(0, 10000).forEach(i ->
        {
          try {
            retrofit.test(i).execute();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
    );
    // then
    long end = System.currentTimeMillis();
//    System.out.println("실행 시간 : " + (end - start) / 1000.0 + "초");
    return (end - start) / 1000.0;
  }

  @DisplayName("4. grpc test")
  Double testGrpc() {
    // given
    // 소스 실행전 시간 취득
    long start = System.currentTimeMillis();


    // when
    IntStream.range(0, 10000).forEach(i ->
        grpcClientService.sendMessage(i)
    );

    // the
    long end = System.currentTimeMillis();
    // 측정 시간 출력
//    System.out.println("실행 시간 : " + (end - start) / 1000.0 + "초");
    return (end - start) / 1000.0;
  }


}