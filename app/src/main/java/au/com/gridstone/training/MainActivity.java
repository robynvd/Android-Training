package au.com.gridstone.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NetworkManager.Observer {

  private NetworkManager networkManager;
  private ProgressBar progressBar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    progressBar.setVisibility(ProgressBar.VISIBLE);

    networkManager = NetworkManager.getInstance();
    networkManager.registerObserver(this);
    networkManager.fetchImages();
  }

  public void notify(ArrayList<Image> imageList) {

    RecyclerView recyclerView;
    ImgurAdapter adapter;

    progressBar.setVisibility(ProgressBar.INVISIBLE);

    adapter = new ImgurAdapter(MainActivity.this, imageList);
    recyclerView = (RecyclerView) findViewById(R.id.list);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    networkManager.unregisterObserver();
  }
}
