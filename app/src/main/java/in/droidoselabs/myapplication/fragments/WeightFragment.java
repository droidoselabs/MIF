package in.droidoselabs.myapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

import in.droidoselabs.myapplication.R;
import in.droidoselabs.myapplication.adapter.WeightAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeightFragment extends Fragment {

    RecyclerView weightRV;
    TextView kgWeight,lbsWeight;

    public WeightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_weight, container, false);
        weightRV = (RecyclerView) mView.findViewById(R.id.weight_rv);
        kgWeight=(TextView)mView.findViewById(R.id.kgWeight);
        lbsWeight=(TextView)mView.findViewById(R.id.lbsWeight);
        weightRV.setAdapter(new WeightAdapter(215));
        weightRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        weightRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
               // Log.e(weightRV.computeHorizontalScrollExtent() + "", "onCreateView: ");
                int offset = weightRV.computeHorizontalScrollOffset();
                int extent = weightRV.computeHorizontalScrollExtent();
                int range = weightRV.computeHorizontalScrollRange();
                float percentage = (float) (100.0 * offset / (float) (range - extent));
                DecimalFormat twoDForm = new DecimalFormat("#.#");
                String weight = String.valueOf(Double.valueOf(twoDForm.format(percentage*2)));
                String weightlbs = String.valueOf(Double.valueOf(twoDForm.format(percentage*2*2.2)));
                String[] parts = weight.split(".");

                    Log.i("RecyclerView", "scroll percentage:" + weight + "%");
                kgWeight.setText(weight+" kg");
                lbsWeight.setText(weightlbs+" Lbs");

            }
        });

        return mView;
    }
}
