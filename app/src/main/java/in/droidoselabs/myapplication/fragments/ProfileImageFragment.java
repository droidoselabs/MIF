package in.droidoselabs.myapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import in.droidoselabs.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileImageFragment extends Fragment implements Animation.AnimationListener, View.OnClickListener {

    View male_view, centre_view, female_view, black_view;
    Animation animMale, animFemale, animcentre, animBlack;
    ImageView male, female;
    boolean isMaleClicked = false, isFemaleClicked = false, isMaleFirstClick = true, isFemaleFirstClick = true;

    public ProfileImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_profile_image, container, false);
        init(mView);

        male.setOnClickListener(this);
        female.setOnClickListener(this);
        return mView;
    }

    private void init(View mView) {
        //Views
        male_view = mView.findViewById(R.id.view_male);
        female_view = mView.findViewById(R.id.view_female);
        centre_view = mView.findViewById(R.id.view_center);
        black_view = mView.findViewById(R.id.view_center_black);
        //ImageView
        male = (ImageView) mView.findViewById(R.id.male);
        female = (ImageView) mView.findViewById(R.id.female);


    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.male:
                if (!isMaleClicked) {
                    if (isMaleFirstClick) {
                        male_view.clearAnimation();

                        male_view.setVisibility(View.VISIBLE);
                        animMale = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                                R.anim.zi_male_view);
                        animMale.setAnimationListener(this);
                        male_view.setAnimation(animMale);
                        isMaleFirstClick = false;
                        isFemaleFirstClick=false;
                    } else {
                        male_view.clearAnimation();
                        female_view.clearAnimation();
                        centre_view.clearAnimation();
                        black_view.clearAnimation();
                        male_view.setVisibility(View.VISIBLE);
                        centre_view.setVisibility(View.VISIBLE);
                        black_view.setVisibility(View.VISIBLE);
                        female_view.setVisibility(View.VISIBLE);
                        animMale = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                                R.anim.zi_male_view2);
                        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF,1.0f, Animation.RELATIVE_TO_SELF, 0.5f);
                        anim.setDuration(1300);
                        ScaleAnimation anim2 = new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF,1.0f, Animation.RELATIVE_TO_SELF, 0.5f);
                        anim2.setDuration(1000);
                        anim2.setStartOffset(1300);
                        centre_view.startAnimation(anim);
                        black_view.startAnimation(anim2);
                        animFemale = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                                R.anim.zo_female_view);
//                        animBlack = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
//                                R.anim.slideb_centre_view);
                        animMale.setAnimationListener(this);
                        animFemale.setAnimationListener(this);
//                        animcentre.setAnimationListener(this);
                        male_view.setAnimation(animMale);
//                        centre_view.setAnimation(animcentre);
                        female_view.setAnimation(animFemale);
//                        black_view.setAnimation(animBlack);
                    }
                    isMaleClicked = true;
                    isFemaleClicked=false;
                }
                break;
            case R.id.female:
                if (!isFemaleClicked) {
                    if (isFemaleFirstClick) {
                        female_view.clearAnimation();
                        female_view.setVisibility(View.VISIBLE);
                        animFemale = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                                R.anim.zi_female_view2);
                        animFemale.setAnimationListener(this);
                        female_view.setAnimation(animFemale);
                        isFemaleFirstClick = false;
                        isMaleFirstClick=false;
                    } else {
                        male_view.clearAnimation();
                        female_view.clearAnimation();
                        centre_view.clearAnimation();
                        black_view.clearAnimation();
                        male_view.setVisibility(View.VISIBLE);
                        centre_view.setVisibility(View.VISIBLE);
                        black_view.setVisibility(View.VISIBLE);
                        female_view.setVisibility(View.VISIBLE);
                        animMale = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                                R.anim.zo_male_view);
                        animcentre = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                                R.anim.slide_centre_view);
                        animFemale = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                                R.anim.zi_female_view);
                        animBlack = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                                R.anim.slideb_centre_view);
                        animMale.setAnimationListener(this);
                        animFemale.setAnimationListener(this);
                        animcentre.setAnimationListener(this);
                        male_view.setAnimation(animMale);
                        centre_view.setAnimation(animcentre);
                        female_view.setAnimation(animFemale);
                        black_view.setAnimation(animBlack);
                    }
                    isFemaleClicked=true;
                    isMaleClicked=false;
                }
                break;
            default:
                break;
        }
    }
}
