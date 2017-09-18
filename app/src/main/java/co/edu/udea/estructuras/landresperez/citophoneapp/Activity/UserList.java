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

    private final int REQUEST_PERMISSION_CALL_PHONE = 1;

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

    @TargetApi(23)
    private void callMethod() {
        try {
            int permissionCheck = ContextCompat.
                    checkSelfPermission(this, Manifest.permission.CALL_PHONE);

            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 31504093));
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CALL_PHONE)) {
                    return;
                } else {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PERMISSION_CALL_PHONE);
                }
            } else {
                Toast.makeText(this, "Permission (already) Granted!", Toast.LENGTH_SHORT).show();
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
