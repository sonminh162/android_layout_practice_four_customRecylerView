package com.lifetime.layout_practice4;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lifetime.layout_practice4.Common.Common;
import com.lifetime.layout_practice4.SpecialInterface.ItemClickListener;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter {
    public interface OnItemClickListener {
        void onItemClick(Info info);
        void onItemClick(boolean state,int position);
        void onItemClickLike(boolean state,int position);
        void onItemClick(String text);
    }

    ItemClickListener itemClickListener;
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener  ;
    }


    private OnItemClickListener listener;
    Context context;
    private static final int HEADER = 1;
    private static final int CONTENT = 2;
    private final ArrayList<Info> infors;
    Search search = new Search();
    int row_index = -1;

    public Adapter(ArrayList<Info> infors, OnItemClickListener listener, Context context) {
        this.infors = infors;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEADER : CONTENT;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_row, parent, false);
                return new HeaderViewHolder(view);
            case CONTENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
                return new ViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = this.getItemViewType(position);
        if (infors != null || search != null) {
            switch (viewType) {
                case HEADER:
                    ((HeaderViewHolder) holder).bindView(search, listener);
                    break;
                case CONTENT:
                    ((ViewHolder) holder).bindView(infors.get(position), listener, position);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return infors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView description;
        TextView time;
        ImageButton book_mark;
        ImageButton like;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            description = itemView.findViewById(R.id.description);
            time = itemView.findViewById(R.id.time);
            book_mark = itemView.findViewById(R.id.button1);
            like = itemView.findViewById(R.id.button2);
        }

        public void bindView(final Info info, final OnItemClickListener listener, final int position) {
            img.setImageResource(info.getImage());
            description.setText(info.getDescription());
            time.setText(info.getTime());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(info);
                }
            });
            if(!infors.get(position).isChecked()) {
                book_mark.setImageResource(R.drawable.ic_book_mark_off);
            } else {
                book_mark.setImageResource(R.drawable.ic_book_mark_on);
            }
            if(!infors.get(position).isLiked()) {
                like.setImageResource(R.drawable.ic_like_off);
            } else {
                like.setImageResource(R.drawable.ic_like_on);
            }
            book_mark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if(infors.get(position).isChecked() == false) {
//                        infors.get(position).setChecked(true);
//                    } else {
//                        infors.get(position).setChecked(false);
//                    }
//                    notifyDataSetChanged();
                    if(infors.get(position).isChecked()){
                        listener.onItemClick(false,position);
                    }else{
                        listener.onItemClick(true,position);
                    }
                    notifyDataSetChanged();
                }
            });
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(infors.get(position).isLiked())
                        listener.onItemClickLike(false,position);
                    else
                        listener.onItemClickLike(true,position);
                    notifyDataSetChanged();
                }
            });
//            ---------
//            book_mark.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    row_inder == positioin
//                }
//            });
//            setItemClickListener(new ItemClickListener() {
//                @Override
//                public void onClick(View view, int position, OnItemClickListener listener) {
//                    row_index = position;
//                    Common.currentItem = infors.get(position);
//                    notifyDataSetChanged();
//                }
//            });
//            itemView.setOnClickListener(new View.OnClickListener(){
//
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClick(info);
//                }
//            });
//            if(row_index == position){
//                book_mark.setImageResource(R.drawable.ic_book_mark_off);
//            }else{
//                book_mark.setImageResource(R.drawable.ic_book_mark_on);
//            }
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),listener);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        EditText editText;
        View view;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.edit_text);
            view = itemView.findViewById(R.id.view7);
        }

        public void bindView(final Search search, final OnItemClickListener listener) {
            editText.setText(search.getText());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(editText.getText().toString());
                }
            });
        }
    }
}
