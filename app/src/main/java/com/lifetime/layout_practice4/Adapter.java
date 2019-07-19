package com.lifetime.layout_practice4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter implements OnItemClickListener{



    private static final int HEADER = 1;
    private static final int CONTENT = 2;

    ArrayList<Info> infors;
    Search search = new Search();
    Context context;

    public Adapter(ArrayList<Info> infors, Context context) {
        this.infors = infors;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position){
        return position == 0 ? HEADER : CONTENT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view;
        switch(viewType){
            case HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_row,parent,false);
                return new HeaderViewHolder(view);
            case CONTENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
                return new ViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = this.getItemViewType(position);
        if(infors != null || search != null){
            switch (viewType){
                case HEADER:
                    break;
                case CONTENT:
                    ((ViewHolder)holder).bindView(infors.get(position));
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return infors.size();
    }

    // custom onClick
    @Override
    public void onItemClick(Info info) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView description;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            description = itemView.findViewById(R.id.description);
            time = itemView.findViewById(R.id.time);

        }
        public void bindView(Info info){
            img.setImageResource(info.getImage());
            description.setText(info.getDescription());
            time.setText(info.getTime());
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        public HeaderViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
