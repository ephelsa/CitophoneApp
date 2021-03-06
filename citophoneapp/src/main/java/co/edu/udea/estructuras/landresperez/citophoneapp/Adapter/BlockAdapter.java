package co.edu.udea.estructuras.landresperez.citophoneapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.estructuras.landresperez.citophoneapp.Activity.UserListView;
import co.edu.udea.estructuras.landresperez.citophoneapp.Model.BlockData;
import co.edu.udea.estructuras.landresperez.citophoneapp.R;

/**
 * Created by landres.perez on 18/09/17.
 */

public class BlockAdapter extends RecyclerView.Adapter<BlockAdapter.ViewHolder> {

    private List<BlockData> blockDataList;
    private Context context = null;

    public BlockAdapter(List<BlockData> blockDataList) {
        this.blockDataList = blockDataList;
    }

    @Override
    public BlockAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_blocks, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BlockAdapter.ViewHolder holder, final int position) {
        holder.block.setText(blockDataList.get(position).getBlockNumber());

        /*
        *   OnClic methods
        */

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextView(position);
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return blockDataList.size();
    }

    // Private Methods
    private void nextView(int position) {
        String blockNumber = blockDataList.get(position).getBlockNumber()
                .replaceAll(context.getResources().getString(R.string.block) + ' ', "");

        Intent intent = new Intent(context, UserListView.class);
        intent.putExtra("BLOCK_NUMBER", blockNumber);

        context.startActivity(intent);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView block;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_view_blocks);
            block = itemView.findViewById(R.id.block_number);
        }
    }
}
