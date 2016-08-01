package au.com.gridstone.training;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Robyn on 27/07/2016.
 */
public class NetworkManager {
  private static NetworkManager ourInstance = new NetworkManager();
  public static final String BASE_URL = "https://api.imgur.com/3/gallery/";
  private static ArrayList<Image> imageList;
  private Observer observer;

  public static NetworkManager getInstance() {
    return ourInstance;
  }

  private NetworkManager() {
  }

  public void registerObserver(Observer observer) {
    this.observer = observer;
  }

  public void notifyObservers(ArrayList<Image> images) {
    observer.notify(images);
  }

  public void unregisterObserver() {
    this.observer = null;
  }

  public void fetchImages(Context context) {

    final Context appContext = context;

    //Check if imageList is null or empty, if not return the imageList, otherwise perform network request
    if (imageList != null && !imageList.isEmpty()) {
      notifyObservers(imageList);
      return;
    }

    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
      @Override public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder requestBuilder =
            request.newBuilder().header("Authorization", "Client-ID 3436c108ccc17d3");

        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
      }
    }).build();

    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build();

    ImgurApiEndpointInterface imgurInterface = retrofit.create(ImgurApiEndpointInterface.class);

    Call<ImageResponse> call = imgurInterface.getImages();
    call.enqueue(new Callback<ImageResponse>() {

      @Override
      public void onResponse(Call<ImageResponse> call, retrofit2.Response<ImageResponse> response) {

        imageList = response.body().data;

        Iterator<Image> iterator = imageList.iterator();

        while (iterator.hasNext()) {
          Image img = iterator.next();

          if (img.is_album) {
            iterator.remove();
          }
        }

        notifyObservers(imageList);
      }

      @Override public void onFailure(Call<ImageResponse> call, Throwable t) {
        Toast toast = Toast.makeText(appContext, "Error fetching images", Toast.LENGTH_SHORT);
        toast.show();
      }
    });
  }

  public interface Observer {
    void notify(ArrayList<Image> images);
  }
}
