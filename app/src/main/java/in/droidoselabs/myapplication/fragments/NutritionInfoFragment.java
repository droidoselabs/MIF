package in.droidoselabs.myapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.droidoselabs.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NutritionInfoFragment extends Fragment {


    public NutritionInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.feed_item_row, container, false);
    }

}
