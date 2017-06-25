package in.droidoselabs.myapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.droidoselabs.myapplication.R;
import in.droidoselabs.myapplication.activity.WheelPicker;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgeFragment extends Fragment implements WheelPicker.OnItemSelectedListener {
    private WheelPicker wheelLeft;

    public AgeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView=inflater.inflate(R.layout.fragment_age, container, false);;
        wheelLeft = (WheelPicker)mView. findViewById(R.id.main_wheel_left);
        wheelLeft.setOnItemSelectedListener(this);
        return mView;
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {

    }
}
