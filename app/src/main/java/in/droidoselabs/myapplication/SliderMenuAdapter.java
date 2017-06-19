package in.droidoselabs.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

import in.droidoselabs.myapplication.fragments.RecipeFragment;
import in.droidoselabs.myapplication.model.SliderItemModel;



public class SliderMenuAdapter extends RecyclerView.Adapter<SliderMenuAdapter.ViewHolder> {

    private ArrayList<SliderItemModel> data;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivIcon;
        public TextView tvItem;
        public LinearLayout parentRow;

        public ViewHolder(View v) {
            super(v);
            ivIcon = (ImageView) v.findViewById(R.id.ivIcon);
            tvItem = (TextView) v.findViewById(R.id.tvItem);
            parentRow = (LinearLayout) v.findViewById(R.id.parentRow);
        }
    }

    public SliderMenuAdapter(Context ctx, ArrayList<SliderItemModel> data) {
        mContext = ctx;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (position == 3) {
            holder.parentRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
            return;
        }

        SliderItemModel model = data.get(position);
        holder.tvItem.setText(model.getName());
        holder.tvItem.setCompoundDrawablesWithIntrinsicBounds(model.getIcon(), 0, 0, 0);

        holder.parentRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (position) {

                    case 0:
                        ((DashboardActivity) mContext).tvTitle.setText(data.get(position).getName().toUpperCase());
                        ((DashboardActivity) mContext).startFragment(new RecipeFragment(), position);
                        ((DashboardActivity) mContext).mSlideMenu.close(true);
                        break;


                    case 1:
                        ((DashboardActivity) mContext).performAction(position);
                        break;


                    case 2:

                        break;

                    case 3:

                        break;

                    case 4:

                        break;

                    case 5:
                        break;

                    default:
                }


            }
        });

    }
}
