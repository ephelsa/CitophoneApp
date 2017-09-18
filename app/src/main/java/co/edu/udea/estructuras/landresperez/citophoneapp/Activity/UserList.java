package co.edu.udea.estructuras.landresperez.citophoneapp.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
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

    private void iniciar() {
        dataList = new ArrayList<>();

        for(int i = 1; i < 20; i++) {
            dataList.add(new UserListData(String.valueOf(100 + i), "ENCARGADO DEL APARTAMENTO " + i,
                    30178650 + i));
        }

        UserListAdapter userListAdapter = new UserListAdapter(dataList);
        recyclerView.setAdapter(userListAdapter);
    }
}
