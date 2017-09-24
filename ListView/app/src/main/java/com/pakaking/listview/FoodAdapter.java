package com.pakaking.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by USER-PC on 2017-09-24.
 */

public class FoodAdapter extends BaseAdapter{
    private ArrayList<FoodItem> data;
    private LayoutInflater inflater;
    private int layout;

    public FoodAdapter(Context context, int layout, ArrayList<FoodItem> data) {
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
        this. data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i).getName();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) view = inflater.inflate(layout,viewGroup,false);

        FoodItem item = data.get(i);

        ImageView icon = (ImageView)view.findViewById(R.id.imageview);
        icon.setImageResource(item.getImage());

        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(item.getName());
        return view;
    }
}
