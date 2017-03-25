package in.djtrinity.www.newapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return GalleryFragment.mThumbIds.length;
    }

    public Object getItem(int position) {
        return GalleryFragment.mThumbIds[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    // Create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // If it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
        } else {
            imageView = (ImageView) convertView;
        }

       // Picasso.with(mContext).load(MainActivity.mThumbIds[position]).resize(600,500).into(imageView);
        Picasso.with(mContext)
                .load(GalleryFragment.mThumbIds[position])
                .placeholder(R.drawable.loading)
                .resize(600,500)
                .error(R.drawable.error)
                .into(imageView);
        //imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    //

    // References to our images in res > drawable

}