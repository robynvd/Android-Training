package au.com.gridstone.training;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;

/**
 * Created by Robyn on 21/07/2016.
 */
public class Image implements Parcelable {

  String title;
  long datetime;
  Integer width;
  Integer height;
  Integer views;
  String link;
  boolean is_album;

  public String getTitle() {
    return title;
  }

  public String getUrl() {
    return link;
  }

  public String getHeight() {

    return height + " px";
  }

  public String getWidth() {

    return width + " px";
  }

  public CharSequence getDatetime() {

    CharSequence convertedTime =
        DateUtils.getRelativeTimeSpanString(datetime * 1000, System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS);

    return convertedTime;
  }

  public int getViews() {
    return views;
  }

  protected Image(Parcel in) {
    title = in.readString();
    datetime = in.readLong();
    width = in.readByte() == 0x00 ? null : in.readInt();
    height = in.readByte() == 0x00 ? null : in.readInt();
    views = in.readByte() == 0x00 ? null : in.readInt();
    link = in.readString();
    is_album = in.readByte() != 0x00;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(title);
    dest.writeLong(datetime);
    if (width == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeInt(width);
    }
    if (height == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeInt(height);
    }
    if (views == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeInt(views);
    }
    dest.writeString(link);
    dest.writeByte((byte) (is_album ? 0x01 : 0x00));
  }

  @SuppressWarnings("unused") public static final Parcelable.Creator<Image> CREATOR =
      new Parcelable.Creator<Image>() {
        @Override public Image createFromParcel(Parcel in) {
          return new Image(in);
        }

        @Override public Image[] newArray(int size) {
          return new Image[size];
        }
      };
}
