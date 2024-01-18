package com.example.solrecupmul.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.solrecupmul.R;
import com.example.solrecupmul.model.Game;

import java.util.ArrayList;

public class GameAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Game> games;

    private int layout;

    public GameAdapter(Context context, ArrayList<Game> games, int layout) {
        this.context = context;
        this.games = games;
        this.layout = layout;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    @Override
    public int getCount() {
        return games.size();
    }

    @Override
    public Object getItem(int position) {
        return games.get(position);
    }

    @Override
    public long getItemId(int position) {
        return games.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = View.inflate(context, layout, null);
        else {
            TextView tvName = convertView.findViewById(R.id.name);
            tvName.setText(games.get(position).getName());
            TextView tvCompany = convertView.findViewById(R.id.company);
            tvCompany.setText(games.get(position).getCompany());
            TextView tvPlatform = convertView.findViewById(R.id.platform);
            tvPlatform.setText(games.get(position).getPlatform());
            TextView tvYear = convertView.findViewById(R.id.year);
            tvYear.setText(String.valueOf(games.get(position).getYear()));
        }
        return convertView;
    }
}
