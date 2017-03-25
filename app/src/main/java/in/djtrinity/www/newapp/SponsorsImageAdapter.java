package in.djtrinity.www.newapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Collections;
import java.util.List;

public class SponsorsImageAdapter extends RecyclerView.Adapter<SponsorsImageAdapter.SponsorsViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<SponsorsData> data = Collections.emptyList();
    int p = -1;

    public SponsorsImageAdapter(Context c, List<SponsorsData> d) {
        context = c;
        data = d;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(SponsorsViewHolder holder, int position) {
        SponsorsData current = data.get(position);
        holder.imageView.setImageResource(current.id);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public SponsorsImageAdapter.SponsorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_for_sponsors, parent, false);
        return new SponsorsViewHolder(view);
    }

    class SponsorsViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public SponsorsViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.sponsor_image);
        }
    }
}

