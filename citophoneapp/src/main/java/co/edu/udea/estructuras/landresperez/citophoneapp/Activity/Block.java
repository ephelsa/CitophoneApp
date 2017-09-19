package co.edu.udea.estructuras.landresperez.citophoneapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.estructuras.landresperez.citophoneapp.Adapter.BlockAdapter;
import co.edu.udea.estructuras.landresperez.citophoneapp.Data.BlockData;
import co.edu.udea.estructuras.landresperez.citophoneapp.R;

/**
 * Created by landres.perez on 18/09/17.
 */

public class Block extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<BlockData> blockDataList;

    // Firebase methods
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        iniciar();
    }

    @Override
    protected void onStart() {
        super.onStart();

        blockDataList = new ArrayList<>();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Block", "Value is: " + value);


                blockDataList.add(new BlockData("Bloque " + String.valueOf(+ 1)));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Block", "Failed to read value.", error.toException());
            }
        });

        BlockAdapter blockAdapter = new BlockAdapter(blockDataList);

        recyclerView.setAdapter(blockAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.logout_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();

            logOut();
        }

        return super.onOptionsItemSelected(item);
    }

    private void iniciar() {
        // Delete this method when join with Firebase

    }

    /*
    *   Private Methods
     */

    private void logOut() {
        Intent intent = new Intent(Block.this, Login.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}
