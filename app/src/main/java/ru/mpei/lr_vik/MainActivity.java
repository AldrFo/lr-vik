package ru.mpei.lr_vik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private final PersonsAdapter adapter = new PersonsAdapter();

    private EditText nameEdit;
    private Button refreshBtn;
    private Button newBtn;
    private Button playBtn;
    private Button removeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        loadPersons();

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
        nameEdit = findViewById(R.id.personEditText);
        refreshBtn = findViewById(R.id.refreshPerson);
        newBtn = findViewById(R.id.newPerson);
        playBtn = findViewById(R.id.play);
        removeBtn = findViewById(R.id.deletePlayer);

        newBtn.setOnClickListener(v -> {
            adapter.persons.add(new Person(nameEdit.getText().toString(), false));
            adapter.notifyDataSetChanged();
        });
        playBtn.setOnClickListener(v -> {
            Intent i = new Intent(this, PlayActivity.class);
            startActivity(i);
        });
        removeBtn.setOnClickListener(v ->{
            adapter.persons.remove(new Person(nameEdit.getText().toString(), false));
            adapter.notifyDataSetChanged();
        });

        rv.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        savePersons();
    }

    private void savePersons(){
        prefs.edit().putInt("size", adapter.persons.size()).apply();
        for(int i =0; i < adapter.persons.size(); i++){
            prefs.edit()
                .putString("person "+ i, adapter.persons.get(i).name)
                .putBoolean("person " + i + " here", adapter.persons.get(i).isHere)
                .apply();
        }
    }

    private void loadPersons(){
        int size = prefs.getInt("size", -1);
        if(size == -1)
            return;
        for(int i = 0; i < size; i++){
            adapter.persons.add(new Person(
                prefs.getString("person " + i, "unknown"),
                prefs.getBoolean("person " + i + " here", false)
            ));
        }
    }
}