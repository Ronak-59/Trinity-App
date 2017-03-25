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
public class Sponsoradapter extends RecyclerView.Adapter<Sponsoradapter.ViewHolder> {
    private ArrayList<Sponsorgettersetter> android_versions;
    private Context context;
    // OnItemClickListener mItemClickListener;


    public Sponsoradapter(Context context, ArrayList<Sponsorgettersetter> android_versions) {
        this.context = context;
        this.android_versions = android_versions;

    }


    @Override
    public Sponsoradapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sponsor_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.sponsor_name.setText(android_versions.get(i).getSponsorname());
        Picasso.with(context)
                .load(android_versions.get(i).getSponsorposter())
                .resize(1000, 600)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(viewHolder.sponsor_poster);


    }

    @Override
    public int getItemCount() {
        return android_versions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sponsor_name;
        ImageView sponsor_poster;


        // private ItemClickListener clickListener;

        private RecyclerItemClickListener.OnItemClickListener clickListener;


        public ViewHolder(View view) {
            super(view);

            context = view.getContext();
            sponsor_name = (TextView) view.findViewById(R.id.sponsor_name);
            sponsor_poster = (ImageView) view.findViewById(R.id.sponsor_poster);

        }
    }
}




