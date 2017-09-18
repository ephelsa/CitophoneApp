package co.edu.udea.estructuras.landresperez.citophoneapp.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentContainer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.edu.udea.estructuras.landresperez.citophoneapp.Data.UserListData;
import co.edu.udea.estructuras.landresperez.citophoneapp.R;

/**
 * Created by landres.perez on 18/08/17.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<UserListData> userListDataList;
    private Context context = null;

    public UserListAdapter(List<UserListData> userListDataList) {
        this.userListDataList = userListDataList;
    }


    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserListAdapter.ViewHolder holder, final int position) {
        holder.nameApartment.setText(userListDataList.get(position).getNombreApartamento());
        holder.numberApartment.setText(userListDataList.get(position).getNumeroApartamento());

        /*
        * Aquí están definidos los clics en cada card.
        * */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Nombre" + userListDataList.get(position)
                        .getNombreApartamento(), Toast.LENGTH_SHORT).show();

                // Continuacion del DialogFragment

                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();

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

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView card;
        TextView numberApartment, nameApartment;

        public ViewHolder(View itemView) {
            super(itemView);

            card = (CardView) itemView.findViewById(R.id.cardView1);
            numberApartment = (TextView) itemView.findViewById(R.id.apartment_number);
            nameApartment = (TextView) itemView.findViewById(R.id.apartment_represent);
        }
    }
}
