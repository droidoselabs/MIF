package in.droidoselabs.myapplication.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import in.droidoselabs.myapplication.R;
import in.droidoselabs.myapplication.fragments.AgeFragment;
import in.droidoselabs.myapplication.fragments.GenderFragment;
import in.droidoselabs.myapplication.fragments.HeightFragment;
import in.droidoselabs.myapplication.fragments.ProfileImageFragment;
import in.droidoselabs.myapplication.fragments.WeightFragment;

public class ProfileActivity extends BaseActivity implements Animation.AnimationListener {
    ViewPager mViewPager;
    View pageOne, pageTwo, pageThree, pageFour, pageFive;
    LinearLayout nextBtn, backBtn;
    Animation zoomOut, zoomIn;
    ImageView lastPage;
    boolean isAnimationVisible = true, isAnimationInvisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mViewPager = (ViewPager) findViewById(R.id.pager);
//        pageOne = findViewById(R.id.page_one);
//        pageTwo = findViewById(R.id.page_two);
//        pageThree = findViewById(R.id.page_three);
//        pageFour = findViewById(R.id.page_four);
//        pageFive = findViewById(R.id.page_five);
        nextBtn = (LinearLayout) findViewById(R.id.nextBtn);
        backBtn = (LinearLayout) findViewById(R.id.backBtn);
        lastPage=(ImageView)findViewById(R.id.lastPage);
        backBtn.setVisibility(View.INVISIBLE);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = mViewPager.getCurrentItem();
                if (page > 0) {
                    mViewPager.setCurrentItem(page - 1);
                } else if (page == 0) {
                    backBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = mViewPager.getCurrentItem();
                if (page < 4) {
                    mViewPager.setCurrentItem(page + 1);
                }
            }
        });

        mViewPager.setAdapter(new SamplePagerAdapter(
                getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position == 0) {
//                    pageOne.setBackgroundResource(R.drawable.circle_view_filled);
//                    pageTwo.setBackgroundResource(R.drawable.circular_view_light);
//                    pageThree.setBackgroundResource(R.drawable.circular_view_light);
//                    pageFour.setBackgroundResource(R.drawable.circular_view_light);
//                    pageFive.setBackgroundResource(R.drawable.circular_view_light);
                    backBtn.setVisibility(View.INVISIBLE);
                    if (isAnimationInvisible) {
                        zoomIn = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zoom_in);

                        zoomIn.setAnimationListener(ProfileActivity.this);
                        backBtn.setAnimation(zoomIn);
                        isAnimationInvisible = false;
                        isAnimationVisible = true;
                    }
                } else if (position == 1) {
//                    pageOne.setBackgroundResource(R.drawable.circular_view_light);
//                    pageTwo.setBackgroundResource(R.drawable.circle_view_filled);
//                    pageThree.setBackgroundResource(R.drawable.circular_view_light);
//                    pageFour.setBackgroundResource(R.drawable.circular_view_light);
//                    pageFive.setBackgroundResource(R.drawable.circular_view_light);
                    backBtn.setVisibility(View.VISIBLE);
                    if (isAnimationVisible) {
                        zoomOut = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.zoom_out);
                        backBtn.setAnimation(zoomOut);
                        zoomOut.setAnimationListener(ProfileActivity.this);
                        isAnimationVisible = false;
                        isAnimationInvisible = true;
                    }
                } else if (position == 2) {
//                    pageOne.setBackgroundResource(R.drawable.circular_view_light);
//                    pageTwo.setBackgroundResource(R.drawable.circular_view_light);
//                    pageThree.setBackgroundResource(R.drawable.circle_view_filled);
//                    pageFour.setBackgroundResource(R.drawable.circular_view_light);
//                    pageFive.setBackgroundResource(R.drawable.circular_view_light);
                } else if (position == 3) {
//                    pageOne.setBackgroundResource(R.drawable.circular_view_light);
//                    pageTwo.setBackgroundResource(R.drawable.circular_view_light);
//                    pageThree.setBackgroundResource(R.drawable.circular_view_light);
//                    pageFour.setBackgroundResource(R.drawable.circle_view_filled);
//                    pageFive.setBackgroundResource(R.drawable.circular_view_light);
                    lastPage.setImageResource(R.drawable.ic_arrow_forward_black_24dp);
                } else if (position == 4) {
//                    pageOne.setBackgroundResource(R.drawable.circular_view_light);
//                    pageTwo.setBackgroundResource(R.drawable.circular_view_light);
//                    pageThree.setBackgroundResource(R.drawable.circular_view_light);
//                    pageFour.setBackgroundResource(R.drawable.circular_view_light);
//                    pageFive.setBackgroundResource(R.drawable.circle_view_filled);
                    lastPage.setImageResource(R.drawable.ic_done_all_black_24dp);
                }

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


    public class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            /** Show a Fragment based on the position of the current screen */
            if (position == 0) {
                return new ProfileImageFragment();
            } else if (position == 1) {
                return new GenderFragment();
            } else if (position == 2) {
                return new AgeFragment();
            } else if (position == 3) {
                return new WeightFragment();
            } else if (position == 4) {
                return new HeightFragment();
            } else {
                return new GenderFragment();
            }
        }


        @Override
        public int getCount() {
            return 5;
        }

    }
}
