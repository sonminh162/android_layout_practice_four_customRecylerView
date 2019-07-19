package com.lifetime.layout_practice4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter{
    public interface OnItemClickListener{
        void onItemClick(Info info);
        void onItemClick(Search search);
    }

    private OnItemClickListener listener;
    Context context;
    private static final int HEADER = 1;
    private static final int CONTENT = 2;
    private final ArrayList<Info> infors;
    Search search = new Search();

    public Adapter(ArrayList<Info> infors,OnItemClickListener listener) {
        this.infors = infors;
        this.listener = listener;
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
                    ((HeaderViewHolder)holder).bindView(search,listener);
                    break;
                case CONTENT:
                    ((ViewHolder)holder).bindView(infors.get(position),listener);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return infors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView description;
        TextView time;
        ImageButton book_mark;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            description = itemView.findViewById(R.id.description);
            time = itemView.findViewById(R.id.time);
            book_mark = itemView.findViewById(R.id.button1);
        }
        public void bindView(final Info info, final OnItemClickListener listener){
            img.setImageResource(info.getImage());
            description.setText(info.getDescription());
            time.setText(info.getTime());
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    listener.onItemClick(info);
                }
            });
//            itemView.setOnClickListener(new View.OnClickListener(){
//
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClick(info);
//                }
//            });
        }

    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{

        EditText editText;
        View view;

        public HeaderViewHolder(@NonNull View itemView){
            super(itemView);
            editText = itemView.findViewById(R.id.edit_text);
            view = itemView.findViewById(R.id.view7);
        }
        public void bindView(final Search search,final OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    listener.onItemClick(search);
                }
            });
        }
    }
}
