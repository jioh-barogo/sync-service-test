package cli;

import dto.CreateRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TestRetrofitInterface {

  @GET("/test/{index}")
  Call<Void> test(@Path("index") Integer index);

  @POST("/test")
  Call<Void> testPost(@Body CreateRequest createRequest);
}