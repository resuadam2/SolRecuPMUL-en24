package com.example.solrecupmul.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.solrecupmul.R;
import com.example.solrecupmul.db.DBManager;
import com.example.solrecupmul.model.Game;

public class AddActivity extends AppCompatActivity {
    EditText etName, etCompany, etYear;
    Spinner spPlatform;
    Button btnAdd, btnCancel;
    DBManager db = new DBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        etName = findViewById(R.id.etName);
        etCompany = findViewById(R.id.etCompany);
        etYear = findViewById(R.id.etYear);
        spPlatform = findViewById(R.id.spnPlatforms);
        btnAdd = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        if (getIntent().getExtras().getInt("id") != 0) {
            getIntent().getExtras().getInt("id");
            etName.setText(getIntent().getExtras().getString("name"));
            etCompany.setText(getIntent().getExtras().getString("company"));
            etYear.setText(String.valueOf(getIntent().getExtras().getInt("year")));
            spPlatform.setSelection(Game.getPlatformIndex(getIntent().getExtras().getString("platform")));
            btnAdd.setText("Update");
        }

        spPlatform.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Game.platforms));

        btnAdd.setOnClickListener(v -> {
            if (etName.getText().toString().isEmpty() || etCompany.getText().toString().isEmpty() || etYear.getText().toString().isEmpty())
                Toast.makeText(AddActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            else if(Integer.parseInt(etYear.getText().toString()) < 1958)
                Toast.makeText(AddActivity.this, "Please enter a valid year", Toast.LENGTH_SHORT).show();
            else if (getIntent().getExtras().getInt("id") != 0) {
                db.edit(getIntent().getExtras().getInt("id"), etName.getText().toString(), etCompany.getText().toString(), spPlatform.getSelectedItem().toString(), Integer.parseInt(etYear.getText().toString()));
                Toast.makeText(AddActivity.this, "Game updated successfully", Toast.LENGTH_SHORT).show();
            } else {
                db.insert(etName.getText().toString(), etCompany.getText().toString(), spPlatform.getSelectedItem().toString(), Integer.parseInt(etYear.getText().toString()));
                Toast.makeText(AddActivity.this, "Game added successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
