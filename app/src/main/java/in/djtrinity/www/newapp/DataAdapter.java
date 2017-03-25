package in.djtrinity.www.newapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<AndroidVersion> android_versions;
    private Context context;
    // OnItemClickListener mItemClickListener;


    public DataAdapter(Context context, ArrayList<AndroidVersion> android_versions) {
        this.context = context;
        this.android_versions = android_versions;

    }


    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tv_android.setText(android_versions.get(i).getAndroid_version_name());
        Picasso.with(context)
                .load(android_versions.get(i).getAndroid_image_url())
                .resize(1000, 600)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(viewHolder.img_android);
        viewHolder.eventdesc.setText(android_versions.get(i).geteventdesc());
        viewHolder.eventdates.setText(android_versions.get(i).geteventdates());
        viewHolder.venue.setText(android_versions.get(i).getVenue());

    }

    @Override
    public int getItemCount() {
        return android_versions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_android;
        ImageView img_android;
        TextView eventdesc;
        TextView eventdates;
        TextView venue;

        // private ItemClickListener clickListener;

        private RecyclerItemClickListener.OnItemClickListener clickListener;


        public ViewHolder(View view) {
            super(view);

            context = view.getContext();
            tv_android = (TextView) view.findViewById(R.id.tv_android);
            img_android = (ImageView) view.findViewById(R.id.img_android);
            eventdesc = (TextView) view.findViewById(R.id.eventdesc);
            eventdates=(TextView)view.findViewById(R.id.dates);
            venue=(TextView)view.findViewById(R.id.venue);
        }
    }
}
