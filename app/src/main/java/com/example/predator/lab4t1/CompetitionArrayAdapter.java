package com.example.predator.lab4t1;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CompetitionArrayAdapter extends ArrayAdapter<Competition> {

    static final int VIEW_TYPE_COUNT = 1;
    static final int VIEW_TYPE = 2;

    public CompetitionArrayAdapter(Context context, ArrayList<Competition> items) {
        super(context, 0, items);
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }


    @Override
    public int getItemViewType(int position) {
        Competition competition = getItem(position);
        return VIEW_TYPE;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Competition competition = getItem(position);

        if (convertView == null) {
            int layoutid = 0;
            layoutid = R.layout.list_item;
            convertView = LayoutInflater.from(getContext()).inflate(layoutid, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.tv_name);
        textViewName.setText(competition.getName());
        return convertView;
    }
}