package com.example.noa_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateNewAccount extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    Button btnRegister, btnLogin;
    EditText etEmailAddress, etNumberPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account_activity);
        btnRegister=findViewById(R.id.btnC);
        etEmailAddress=findViewById(R.id.editTextTextEmailAddress);
        etNumberPassword=findViewById(R.id.editTextTextPassword);
        btnRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v== btnRegister)
        {
            String email = etEmailAddress.getText().toString();
            String password = etNumberPassword.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(CreateNewAccount.this, SearchSongActivity.class);
                                startActivity(intent);
                            }
                             else  {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(CreateNewAccount.this, "fail register", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
}