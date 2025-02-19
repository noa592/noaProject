package com.example.noa_project;

import static com.example.noa_project.MainActivity.fbModule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogingActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister, btnLogin;
    boolean v;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;
    EditText etEmailAddress, etNumberPassword;
private int color ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging);
     sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        updateBackgroundColor();
        mAuth = FirebaseAuth.getInstance();
        btnLogin = findViewById(R.id.buttonlisten);
        etEmailAddress=findViewById(R.id.editTextTextEmailAddress2);
        etNumberPassword=findViewById(R.id.editTextTextPassword2);
        btnLogin.setOnClickListener(this);

    }
    private void updateBackgroundColor() {

        View view = findViewById(R.id.loging);

            view.setBackgroundColor(getResources().getColor(android.R.color.black));
color=sharedPreferences.getInt("color", Color.WHITE);

    }


    public void onClick(View view) {//לבקש עזרה מישראל


        if (view == btnLogin) {
            String email = etEmailAddress.getText().toString();
            String password = etNumberPassword.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(LogingActivity.this, SearchSongActivity.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LogingActivity.this, "login failed.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }
}
