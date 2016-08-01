package au.com.gridstone.training;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Robyn on 20/07/2016.
 */
public interface ImgurApiEndpointInterface {

  @GET("hot/viral/0")
  Call<ImageResponse> getImages();
}
