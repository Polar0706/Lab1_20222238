package com.example.lab1_20222238;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.lab1_20222238.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {
    private ActivityGameBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ActionBar ab = getSupportActionBar();
        if (ab != null) ab.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar, menu); // Menú AppBar
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) { // Up
            finish();
            return true;
        }
        if (id == R.id.action_stats) {
            startActivity(new Intent(this, StatsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int mistakes = 0;
    private static final String[] HANGMAN_STEPS = new String[]{
            "antenna_logo", "antenna", "antenna_sinfondo",
            "head1", "head1_sinfondo",
            "torso", "torso_sinfondo",
            "brazoizq_piernadere", "brazoizq_piernadere_sinfondo",
            "brazodere_piernaizq", "brazodere_piernaizq_sinfondo"
    };
    private void updateHangmanImage() {
        int idx = Math.min(mistakes, HANGMAN_STEPS.length - 1);
        int imgId = getResources().getIdentifier(
                HANGMAN_STEPS[idx], "drawable", getPackageName());
        binding.imgHangman.setImageResource(imgId);
    }

    private void startNewGame() {
        mistakes = 0;
        updateHangmanImage();
    }

    private void onWrongGuess() {
        mistakes++;
        updateHangmanImage();
        if (mistakes >= HANGMAN_STEPS.length - 1) {

        }
    }
}