package co.edu.udea.estructuras.landresperez.citophoneapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.estructuras.landresperez.citophoneapp.Adapter.UserListAdapter;
import co.edu.udea.estructuras.landresperez.citophoneapp.Model.UserListData;
import co.edu.udea.estructuras.landresperez.citophoneapp.R;

public class UserListView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private List<String> users;
    private List<UserListData> dataList;

    private UserListAdapter userListAdapter;

    // Firebase methods
    // Write a message to the database
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Bloque");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataList = new ArrayList<>();
        userListAdapter = new UserListAdapter(dataList);

        // Views
        setContentView(R.layout.recycler_view);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(userListAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataList.removeAll(dataList);

                for (DataSnapshot bloque : dataSnapshot.getChildren()) {
                    for (DataSnapshot numero : bloque.getChildren()) {
                        for (DataSnapshot nombre : numero.getChildren()) {
                            for (DataSnapshot telf : nombre.getChildren()) {
                                dataList.add(new UserListData(numero.getKey(), nombre.getKey(),
                                        telf.getValue().toString()));
                            }
                        }
                    }
                }

                userListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), R.string.fail_read,
                        Toast.LENGTH_SHORT).show();
            }
        });
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

        //dataList.add(new UserListData(String.valueOf(100 + i), "ENCARGADO DEL APARTAMENTO " + i,
            //        3000000 + i));
    }

        /*
    *   Private Methods
     */

    private void logOut() {
        Intent intent = new Intent(UserListView.this, LoginView.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}
