package cli.config;

import cli.TestRetrofitInterface;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfig {

  @Value("${api-server}")
  String testUrl;

  @Bean(name = "retrofit")
  TestRetrofitInterface retrofit() {
    var retrofit = new Retrofit.Builder()
        .baseUrl(testUrl)
        .addConverterFactory(JacksonConverterFactory.create())
        .client(new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build())
        .build();
    return retrofit.create(TestRetrofitInterface.class);
  }

  @Bean(name = "retrofitPool")
  TestRetrofitInterface retrofitPool() {
    var retrofit = new Retrofit.Builder()
        .baseUrl(testUrl)
        .addConverterFactory(JacksonConverterFactory.create())
        .client(new OkHttpClient.Builder()
            .connectionPool(new ConnectionPool(100, 10, TimeUnit.SECONDS))
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build())
        .build();
    return retrofit.create(TestRetrofitInterface.class);
  }
}
