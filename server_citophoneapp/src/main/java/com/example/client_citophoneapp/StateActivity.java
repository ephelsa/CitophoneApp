package com.example.client_citophoneapp;

import android.*;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StateActivity extends AppCompatActivity {

    private final int REQUEST_PERMISSION_CALL_PHONE = 1;

    private TextView fState;

    // Firebase
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Llamar");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        // View
        fState = (TextView) findViewById(R.id.forwarding_state);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = "**21*" + dataSnapshot.getValue().toString() + "#";

                if (!value.equals("#")) {
                    fState.setText(value.toString());

                    call(value.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    // Private methods
    @TargetApi(23)
    private void call(String number) {
        try {
            int permissionCheck = ContextCompat.
                    checkSelfPermission(this, android.Manifest.permission.CALL_PHONE);

            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        android.Manifest.permission.CALL_PHONE)) {
                    return;
                } else {
                    requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE},
                            REQUEST_PERMISSION_CALL_PHONE);
                    return;
                }
            } else {
                Toast.makeText(this, "Llamando", Toast.LENGTH_SHORT).show();
            }

            startActivity(callIntent);
        } catch (ActivityNotFoundException e) {

        }
    }
}
