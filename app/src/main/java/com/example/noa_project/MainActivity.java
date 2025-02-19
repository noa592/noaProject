package com.example.noa_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button entermyacc, createmyacc, enterwithoutacc, one;

    private String backgroundColor = "white";
    public FirebaseAuth mAuth;
   private SharedPreferences.Editor editor;
    public static FbModule fbModule;
    private ConstraintLayout Layout;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entermyacc = findViewById(R.id.btnA);
        createmyacc = findViewById(R.id.btnNewA);
        enterwithoutacc = findViewById(R.id.btnNA);
        enterwithoutacc.setOnClickListener(this);
        createmyacc.setOnClickListener(this);
        entermyacc.setOnClickListener(this);
        one = findViewById(R.id.button9);
        one.setOnClickListener(this);
        customDialog = new CustomDialog(this);
        mAuth = FirebaseAuth.getInstance();
        // אתחול FbModule
        fbModule = new FbModule();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

// Create an editor to write data
      editor = sharedPreferences.edit();
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String color = data.getStringExtra("color");
                            if (color != null) {
                                updateBackgroundColor(color);  // עדכון הצבע
                            }
                        }
                    }
                });
    }

    private void updateBackgroundColor(String color) {

        View view = findViewById(R.id.main_activity);
        if ("dark".equals(color)) {
            view.setBackgroundColor(getResources().getColor(android.R.color.black));
            editor.putInt("color", R.color.black);// צבע כהה
        } else if ("light".equals(color)) {
            view.setBackgroundColor(getResources().getColor(android.R.color.white));
            editor.putInt("color", R.color.white);
        }
    }


    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            reload();
        }
    }

    private void reload() {
        Intent intent = new Intent(MainActivity.this, SearchSongActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        if (v == createmyacc) {
            Intent intent = new Intent(this, NewAccountActivity.class);
            startActivity(intent);
        } else if (v == enterwithoutacc) {
            Intent intent = new Intent(this, SearchSongActivity.class);
            startActivity(intent);

        }
        if (v == entermyacc) {
            Intent intent = new Intent(this, LogingActivity.class);
            startActivity(intent);
        }
        if (v == one)
            if (v == one) {
                try {
                    Intent intent = new Intent(this, MainActivity2.class);
                    activityResultLauncher.launch(intent);
                } catch (Exception e) {
                    Log.e("MainActivity", "Error starting MainActivity2", e);
                }
            }
    }

}




