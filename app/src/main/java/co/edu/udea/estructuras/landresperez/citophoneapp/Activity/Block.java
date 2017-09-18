package co.edu.udea.estructuras.landresperez.citophoneapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    private void iniciar() {

        blockDataList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            blockDataList.add(new BlockData("Bloque " + String.valueOf(i + 1)));
            Log.w("block_1", "Iniciar");
        }

        BlockAdapter blockAdapter = new BlockAdapter(blockDataList);

        recyclerView.setAdapter(blockAdapter);
    }
}
