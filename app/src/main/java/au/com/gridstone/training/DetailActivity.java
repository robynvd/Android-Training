package au.com.gridstone.training;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    Image image = getIntent().getParcelableExtra("image");

    ImageView imageView = (ImageView) findViewById(R.id.image);
    TextView titleTextView = (TextView) findViewById(R.id.image_title);
    TextView timeTextView = (TextView) findViewById(R.id.image_time);
    TextView heightTextView = (TextView) findViewById(R.id.image_height);
    TextView widthTextView = (TextView) findViewById(R.id.image_width);
    TextView viewsTextView = (TextView) findViewById(R.id.image_view_count);

    Picasso.with(this).load(image.getUrl()).into(imageView);
    titleTextView.setText(image.getTitle());
    timeTextView.setText(image.getDatetime());
    heightTextView.setText(image.getHeight());
    widthTextView.setText(image.getWidth());
    viewsTextView.setText(Integer.toString(image.getViews()));
  }
}
