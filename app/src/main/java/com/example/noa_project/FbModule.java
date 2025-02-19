package com.example.noa_project;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.ExecutionException;

public class FbModule {

    private DatabaseReference mDatabase;


    public FbModule() {
        // אתחול Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference("UserSettings");
    }

    // פונקציה לשמירת הצבע ב-Firebase
}




