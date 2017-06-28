package in.droidoselabs.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.droidoselabs.myapplication.R;

/**
 * Created by android on 6/28/17.
 */

public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.InchiTapeVH> {
    private int size;

    public WeightAdapter(int size) {
        this.size = size;
    }


    @Override
    public InchiTapeVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weight_row, parent, false);
        return new InchiTapeVH(view);
    }

    @Override
    public void onBindViewHolder(InchiTapeVH holder, int position) {
        if (((position + 1) % 5) == 0) {
            holder.viewLarge.setVisibility(View.GONE);
            holder.viewLarge.setVisibility(View.VISIBLE);
        } else {
            holder.viewLarge.setVisibility(View.VISIBLE);
            holder.viewLarge.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return size;
    }

    class InchiTapeVH extends RecyclerView.ViewHolder {
        View viewSmall, viewLarge;

        public InchiTapeVH(View itemView) {
            super(itemView);
            viewSmall = itemView.findViewById(R.id.viewSmall);
            viewLarge = itemView.findViewById(R.id.viewLarge);
        }
    }


}
