package com.example.solrecupmul.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.solrecupmul.R;
import com.example.solrecupmul.db.DBManager;
import com.example.solrecupmul.model.Game;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText searchBar;
    Spinner platforms;
    ListView gamesList;
    Button addGame;
    ArrayList<Game> games;
    DBManager db;
    GameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBManager(this);
        games = db.getAllGames();
        searchBar = findViewById(R.id.searchBar);
        platforms = findViewById(R.id.selectPlatform);
        gamesList = findViewById(R.id.games);
        addGame = findViewById(R.id.btnAdd);

        platforms.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Game.platforms));
        adapter = new GameAdapter(this, games, R.layout.item_game);
        gamesList.setAdapter(adapter);


        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                filterGames(searchBar.getText().toString(), (String) platforms.getSelectedItem());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterGames(searchBar.getText().toString(), (String) platforms.getSelectedItem());
            }

            @Override
            public void afterTextChanged(Editable s) {
                filterGames(searchBar.getText().toString(), (String) platforms.getSelectedItem());
            }
        });

        platforms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterGames(searchBar.getText().toString(), (String) platforms.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gamesList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            intent.putExtra("id", games.get(position).getId());
            intent.putExtra("name", games.get(position).getName());
            intent.putExtra("company", games.get(position).getCompany());
            intent.putExtra("platform", games.get(position).getPlatform());
            intent.putExtra("year", games.get(position).getYear());
            startActivity(intent);
        });

        gamesList.setOnItemLongClickListener((parent, view, position, id) -> {
            deleteGame(position);
            return true;
        });

        addGame.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            intent.putExtra("id", 0);
            startActivity(intent);
        });
    }

    private void deleteGame(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Delete game");
        builder.setMessage("Are you sure you want to delete this game ?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            db.delete(adapter.getGames().get(position).getId());
            adapter.getGames().remove(position);
            adapter.notifyDataSetChanged();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        searchBar.setText(preferences.getString("searchBar", ""));
        platforms.setSelection(((ArrayAdapter<String>) platforms.getAdapter()).getPosition(preferences.getString("platform", "All")));
        filterGames(searchBar.getText().toString(), (String) platforms.getSelectedItem());
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("searchBar", searchBar.getText().toString());
        editor.putString("platform", (String) platforms.getSelectedItem());
        editor.apply();
    }

    private void filterGames(String name, String platform) {
        ArrayList<Game> filteredGames = new ArrayList<>();
        name = name.toLowerCase();
        games = db.getAllGames();
        for (Game game : games) {
            if (name.isEmpty() && platform.equals("All")) {
                filteredGames = games;
                break;
            }
            if ((game.getName().toLowerCase().contains(name) || game.getCompany().toLowerCase().contains(name))
                    && (game.getPlatform().equals(platform) || platform.equals("All")))
                filteredGames.add(game);
        }
        adapter.setGames(filteredGames);
        adapter.notifyDataSetChanged();
    }
}