package co.edu.udea.estructuras.landresperez.citophoneapp.Activity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.estructuras.landresperez.citophoneapp.Adapter.UserListAdapter;
import co.edu.udea.estructuras.landresperez.citophoneapp.Data.UserListData;
import co.edu.udea.estructuras.landresperez.citophoneapp.R;

public class UserList extends AppCompatActivity {

    private Button callButton;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<UserListData> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        callButton = (Button) findViewById(R.id.call);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        iniciar();

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserList.this, "Llamando", Toast.LENGTH_SHORT).show();

                callMethod();
            }
        });
    }

    private void callMethod() {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 31504093));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(callIntent);
        } catch (ActivityNotFoundException e) {
            Log.e("Llamada", "Call failed", e);
        }
    }

    private void iniciar() {
        dataList = new ArrayList<>();

        for(int i = 1; i < 20; i++) {
            dataList.add(new UserListData(String.valueOf(100 + i), "ENCARGADO DEL APARTAMENTO " + i));
        }

        UserListAdapter userListAdapter = new UserListAdapter(dataList);
        recyclerView.setAdapter(userListAdapter);
    }
}
