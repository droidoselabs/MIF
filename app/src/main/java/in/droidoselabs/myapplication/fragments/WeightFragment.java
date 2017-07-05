package in.droidoselabs.myapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.DecimalFormat;

import in.droidoselabs.myapplication.R;
import in.droidoselabs.myapplication.adapter.WeightAdapter;

public class WeightFragment extends Fragment {

    private RecyclerView weightRV;
    private TextView kgWeight, lbsWeight;
    private LinearLayoutManager linearLayoutManager;
    NumberPicker feetPicker,inchesPicker;

    public WeightFragment() {

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_weight, container, false);
        init(mView);
        weightRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                kgWeight.setText(linearLayoutManager.findFirstVisibleItemPosition() + 20 + " kg");
                DecimalFormat twoDForm = new DecimalFormat("#.#");
                String weight = String.valueOf(Double.valueOf(twoDForm.format((linearLayoutManager.findFirstVisibleItemPosition() + 20) * 2.2)));
                lbsWeight.setText(weight + " Lbs");
            }
        });

        return mView;
    }

    private void init(View mView) {
        weightRV = (RecyclerView) mView.findViewById(R.id.weight_rv);
        kgWeight = (TextView) mView.findViewById(R.id.kgWeight);
        lbsWeight = (TextView) mView.findViewById(R.id.lbsWeight);
        feetPicker=(NumberPicker)mView.findViewById(R.id.feetPicker);
        inchesPicker=(NumberPicker)mView.findViewById(R.id.inchesPicker);
        feetPicker.setMinValue(1);
        inchesPicker.setMinValue(1);
        feetPicker.setMaxValue(7);
        inchesPicker.setMaxValue(11);
        weightRV.setAdapter(new WeightAdapter(499));
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(weightRV);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        weightRV.setLayoutManager(linearLayoutManager);
    }
}
