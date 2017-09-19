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
        blockDataList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            blockDataList.add(new BlockData("Bloque " + String.valueOf(i + 1)));
            Log.w("block_1", "Iniciar");
        }

        BlockAdapter blockAdapter = new BlockAdapter(blockDataList);

        recyclerView.setAdapter(blockAdapter);
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
