package au.com.gridstone.training;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Robyn on 22/07/2016.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

  public TextView title;
  public ImageView image;

  public ViewHolder(View view) {
    super(view);
    title = (TextView) view.findViewById(R.id.list_item_title);
    image = (ImageView) view.findViewById(R.id.list_item_image);
  }
}