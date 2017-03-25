package in.djtrinity.www.newapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.MyViewHolder> {

    public List<NavDrawerElements> data = Collections.emptyList();
    public LayoutInflater layoutInflater;
    public Context context;
    private ClickListener clickListener;
    public int selectedItem = 0;
    View v;

    public NavDrawerAdapter(Context c, List<NavDrawerElements> d) {
        context = c;
        data = d;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_for_nav_drawer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerElements current = data.get(position);
        holder.textView.setText(current.elementName);
        if (position == selectedItem) {
            holder.linearLayout.setBackgroundColor(Color.parseColor("#4dd0e1"));
        } else {
            holder.linearLayout.setBackgroundResource(R.drawable.custom_bg);
        }
        holder.imageView.setImageResource(current.iconId);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public ImageView imageView;
        public LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            getView(view);
            view.setOnClickListener(this);
            linearLayout = (LinearLayout) view.findViewById(R.id.list_holder_layout);
            textView = (TextView) view.findViewById(R.id.list_text_view);
            imageView = (ImageView) view.findViewById(R.id.list_icon);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                notifyItemChanged(selectedItem);
                selectedItem = getLayoutPosition();
                notifyItemChanged(selectedItem);
                clickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public void getView(View view) {
        v = view;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View view, int position);
    }

}


