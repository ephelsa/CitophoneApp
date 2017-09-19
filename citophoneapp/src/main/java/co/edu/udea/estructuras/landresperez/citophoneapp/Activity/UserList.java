package co.edu.udea.estructuras.landresperez.citophoneapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.estructuras.landresperez.citophoneapp.Adapter.UserListAdapter;
import co.edu.udea.estructuras.landresperez.citophoneapp.Data.UserListData;
import co.edu.udea.estructuras.landresperez.citophoneapp.R;

public class UserList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<UserListData> dataList;

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
        dataList = new ArrayList<>();

        for(int i = 1; i <= 20; i++) {
            dataList.add(new UserListData(String.valueOf(100 + i), "ENCARGADO DEL APARTAMENTO " + i,
                    3000000 + i));
        }

        UserListAdapter userListAdapter = new UserListAdapter(dataList);
        recyclerView.setAdapter(userListAdapter);
    }

        /*
    *   Private Methods
     */

    private void logOut() {
        Intent intent = new Intent(UserList.this, Login.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}
