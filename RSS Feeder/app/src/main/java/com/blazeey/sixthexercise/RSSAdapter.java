package com.blazeey.sixthexercise;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class RSSAdapter extends ArrayAdapter<Item> {

    private List<Item> items;
    private Context context;
    public RSSAdapter(@NonNull Context context, int resource, List<Item> itemList) {
        super(context, resource);
        items = itemList;
        this.context =context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.data,parent,false);

        TextView link = v.findViewById(R.id.link);
        TextView description = v.findViewById(R.id.description);
        TextView title = v.findViewById(R.id.title);

        Item item = items.get(position);
        link.setText(item.getLink());
        description.setText(item.getDescription());
        title.setText(item.getTitle());
        Log.v("DATA",item.getTitle());
        return v;
    }

    @Override
    public int getCount() {
        return items.size();
    }

}
