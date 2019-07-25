package com.lifetime.layout_practice4.SpecialInterface;

import android.view.View;

import com.lifetime.layout_practice4.Adapter;

public interface ItemClickListener {
    void onClick(View view, int position, Adapter.OnItemClickListener listener);
}
