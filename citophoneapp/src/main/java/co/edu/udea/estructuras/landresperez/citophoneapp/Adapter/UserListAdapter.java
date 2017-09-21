package co.edu.udea.estructuras.landresperez.citophoneapp.Adapter;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.edu.udea.estructuras.landresperez.citophoneapp.Model.UserListData;
import co.edu.udea.estructuras.landresperez.citophoneapp.R;

/**
 * Created by landres.perez on 18/08/17.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private final int REQUEST_PERMISSION_CALL_PHONE = 1;

    private List<UserListData> userListDataList;
    private Context context = null;

    public UserListAdapter(List<UserListData> userListDataList) {
        this.userListDataList = userListDataList;
    }

    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_users, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserListAdapter.ViewHolder holder, final int position) {
        holder.nameApartment.setText(userListDataList.get(position).getNombreApartamento());
        holder.numberApartment.setText(userListDataList.get(position).getNumeroApartamento());

        /*
        * Onclic methods.
        * */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callMethod(userListDataList.get(position).getNumeroTelefono());
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return userListDataList.size();
    }

    // Private Methods
    @TargetApi(23)
    private void callMethod(String number) {
        try {
            int permissionCheck = ContextCompat.
                    checkSelfPermission(context, Manifest.permission.CALL_PHONE);

            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                        Manifest.permission.CALL_PHONE)) {
                    return;
                } else {
                    ((Activity) context).requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                            REQUEST_PERMISSION_CALL_PHONE);
                    return;
                }
            } else {
                Toast.makeText(context, R.string.calling, Toast.LENGTH_SHORT).show();
            }

            context.startActivity(callIntent);
        } catch (ActivityNotFoundException e) {
            Log.e("Llamada", "Call failed", e);
        }
    }


    // Class
    protected class ViewHolder extends RecyclerView.ViewHolder {

        CardView card;
        TextView numberApartment, nameApartment;

        public ViewHolder(View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.card_view_users);
            numberApartment = itemView.findViewById(R.id.apartment_number);
            nameApartment = itemView.findViewById(R.id.apartment_represent);
        }
    }
}
