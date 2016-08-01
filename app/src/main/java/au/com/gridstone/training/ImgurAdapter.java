package au.com.gridstone.training;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by Robyn on 21/07/2016.
 */
public class ImgurAdapter extends RecyclerView.Adapter<ViewHolder> {

  private Context context;
  private List<Image> imageList;

  public ImgurAdapter(Context context, List<Image> imageList) {

    this.context = context;
    this.imageList = imageList;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

    return new ViewHolder(itemView);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {

    final ViewHolder viewHolder = holder;
    Image image = imageList.get(position);

    holder.title.setText(image.getTitle());
    Picasso.with(context).load(image.getUrl()).into(holder.image);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        intent.putExtra("image", imageList.get(viewHolder.getAdapterPosition()));

        context.startActivity(intent);
      }
    });
  }

  @Override public int getItemCount() {

    return imageList.size();
  }
}
