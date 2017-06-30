package in.droidoselabs.myapplication.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import in.droidoselabs.myapplication.R;
import in.droidoselabs.myapplication.fragments.AgeFragment;
import in.droidoselabs.myapplication.fragments.GenderFragment;
import in.droidoselabs.myapplication.fragments.HeightFragment;
import in.droidoselabs.myapplication.fragments.IntroFragment;
import in.droidoselabs.myapplication.fragments.ProfileImageFragment;
import in.droidoselabs.myapplication.fragments.WeightFragment;

public class ProfileActivity extends BaseActivity implements Animation.AnimationListener, IntroFragment.ChangePage, View.OnClickListener {
    private ViewPager mViewPager;
    private LinearLayout nextBtn, backBtn;
    private Animation zoomOut, zoomIn;
    private ImageView lastPage;
    private boolean isAnimationVisible = true, isAnimationInvisible = false;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        nextBtn = (LinearLayout) findViewById(R.id.nextBtn);
        backBtn = (LinearLayout) findViewById(R.id.backBtn);
        lastPage = (ImageView) findViewById(R.id.lastPage);
        backBtn.setVisibility(View.INVISIBLE);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new SamplePagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position == 1) {
                    backBtn.setClickable(false);
                    backBtn.setVisibility(View.INVISIBLE);
                    if (isAnimationInvisible) {
                        zoomIn = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zoom_in);
                        zoomIn.setAnimationListener(ProfileActivity.this);
                        backBtn.setAnimation(zoomIn);
                        isAnimationInvisible = false;
                        isAnimationVisible = true;
                    }
                } else if (position == 2) {
                    backBtn.setClickable(true);
                    backBtn.setVisibility(View.VISIBLE);
                    if (isAnimationVisible) {
                        zoomOut = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zoom_out);
                        backBtn.setAnimation(zoomOut);
                        zoomOut.setAnimationListener(ProfileActivity.this);
                        isAnimationVisible = false;
                        isAnimationInvisible = true;
                    }
                } else if (position == 3)
                    lastPage.setImageResource(R.drawable.ic_arrow_forward_black_24dp);
                  else if (position == 4)
                    lastPage.setImageResource(R.drawable.ic_done_all_black_24dp);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
    public void changePage() {
        mViewPager.setCurrentItem(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                page = mViewPager.getCurrentItem();
                if (page > 0)
                    mViewPager.setCurrentItem(page - 1);
                else if (page == 0)
                    backBtn.setVisibility(View.INVISIBLE);
                break;
            case R.id.nextBtn:
                page = mViewPager.getCurrentItem();
                if (page < 4)
                    mViewPager.setCurrentItem(page + 1);
                break;
        }
    }


    public class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new IntroFragment();
            } else if (position == 1) {
                return new GenderFragment();
            } else if (position == 2) {
                return new AgeFragment();
            } else if (position == 3) {
                return new WeightFragment();
            } else if (position == 4) {
                return new HeightFragment();
            } else {
                return new ProfileImageFragment();
            }
        }


        @Override
        public int getCount() {
            return 5;
        }

    }
}
