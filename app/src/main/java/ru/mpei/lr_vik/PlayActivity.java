package ru.mpei.lr_vik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    private Random random = new Random();

    private SharedPreferences prefs;
    private PersonsAdapter adapter = new PersonsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        RecyclerView rv = findViewById(R.id.recycler_view_play);
        loadPersons();
        adapter.setDrawHere(true);
        rv.setAdapter(adapter);
    }

    private void loadPersons(){
        int size = prefs.getInt("size", -1);
        Log.d("asasa", "asdad " + size);
        if(size == -1)
            return;
        for(int i = 0; i < size; i++){
            Person p = new Person(
                prefs.getString("person " + i, "unknown"),
                prefs.getBoolean("person " + i + " here", false)
            );
            Log.d("asasa", "bbb " + p.name + " " + p.isHere);
            if(p.isHere) {
                boolean green = random.nextBoolean();
                p.name = p.name + ((green) ? " (зеленый)" : " (красный)");
                adapter.persons.add(p);
            }
        }
    }
}