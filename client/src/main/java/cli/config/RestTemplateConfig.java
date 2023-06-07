package cli.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Bean
  RestTemplate nonPoolRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  RestTemplate poolRestTemplate() {
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(100);
    connectionManager.setDefaultMaxPerRoute(20);

    RequestConfig requestConfig = RequestConfig
        .custom()
        .setConnectionRequestTimeout(5000) // timeout to get connection from pool
        .setSocketTimeout(5000) // standard connection timeout
        .setConnectTimeout(5000) // standard connection timeout
        .build();

    var httpClient = HttpClientBuilder.create()
        .setConnectionManager(connectionManager)
        .setDefaultRequestConfig(requestConfig)
//          .setMaxConnTotal(100)    //최대 오픈되는 커넥션 수
//          .setMaxConnPerRoute(5)   //IP, 포트 1쌍에 대해 수행할 커넥션 수
        .build();

    ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
    return new RestTemplate(factory);
  }
}
