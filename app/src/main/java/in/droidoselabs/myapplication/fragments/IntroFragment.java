package in.droidoselabs.myapplication.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import in.droidoselabs.myapplication.R;
import in.droidoselabs.myapplication.activity.ProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment extends Fragment implements View.OnClickListener,Animation.AnimationListener {

    Button getStarted;
    LinearLayout view;
    Animation getStart,viewAnim;
    ProfileActivity profileActivity;
    public IntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView=inflater.inflate(R.layout.fragment_intro, container, false);
        profileActivity=(ProfileActivity)getActivity();
        getStarted = (Button) mView.findViewById(R.id.startBtn);
        view =(LinearLayout) mView.findViewById(R.id.view);
        getStarted.setOnClickListener(this);
        view.setOnClickListener(this);
        getStart = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.zoom);
        getStart.setAnimationListener(this);
        viewAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.zoom2);
        viewAnim.setAnimationListener(this);
        return mView;
    }

    @Override
    public void onClick(View v) {
        getStarted.setText("");
        getStarted.startAnimation(getStart);
        view.startAnimation(viewAnim);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                profileActivity.changePage();
            }
        },400);

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    public interface ChangePage{
        public void changePage();
    }
}
