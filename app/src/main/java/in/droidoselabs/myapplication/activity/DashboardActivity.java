package in.droidoselabs.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import in.droidoselabs.myapplication.R;
import in.droidoselabs.myapplication.fragments.AddFeedDialogFragment;
import in.droidoselabs.myapplication.fragments.AddRecipeFragment;
import in.droidoselabs.myapplication.fragments.FeedbackFragment;
import in.droidoselabs.myapplication.fragments.HomeFragment;
import in.droidoselabs.myapplication.fragments.NutritionInfoFragment;
import in.droidoselabs.myapplication.fragments.ProfileFragment;
import in.droidoselabs.myapplication.fragments.RecipeFragment;
import in.droidoselabs.myapplication.fragments.WorkoutFragment;
import in.droidoselabs.myapplication.model.SliderItemModel;
import in.droidoselabs.myapplication.utils.SlideMenu;


public class DashboardActivity extends BaseActivity implements SlideMenu.OnSlideStateChangeListener {

    /* Sliding menu */
    private final static String OFFSET_PERCENT = "OffsetPercent";
    private final static String SLIDE_STATE = "SlideState";
    /* Dashboard menu options */
    public SlideMenu mSlideMenu;
    public TextView tvTitle;
    private Context mContext;
    private Fragment currentFragment;
    private int mSlideState;
    private float mOffsetPercent;

    /* Views */
    private ImageView btMenu, btnCustom;
    private RecyclerView rvItems;
    private ArrayList<SliderItemModel> menuItems;
    private int fragmentCode;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_menu);
        mContext = this;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            initViews();
            setupMenu();
            btMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Boolean slideMenuCheck = true;

                    if (slideMenuCheck) {
                        slideMenuCheck = false;
                        mSlideMenu.open(false, true);
                    } else if (!slideMenuCheck) {
                        slideMenuCheck = true;
                        mSlideMenu.close(true);
                    }
                }
            });

            btnCustom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.app.FragmentManager fragmentManager = getFragmentManager();
                    if (fragmentCode == 0) {
                        AddFeedDialogFragment addFeedDialogFragment = new AddFeedDialogFragment();
                        addFeedDialogFragment.show(fragmentManager, "AddFeedDialog");
                    } else if (fragmentCode == 1) {
                        AddRecipeFragment addRecipeFragment = new AddRecipeFragment();
                        addRecipeFragment.show(fragmentManager, "AddRecipeDialog");
                    } else if (fragmentCode == 2) {
                        AddFeedDialogFragment newTermDialogFragment = new AddFeedDialogFragment();
                        newTermDialogFragment.show(fragmentManager, "Dialog");
                    } else if (fragmentCode == 3) {
                        AddFeedDialogFragment newTermDialogFragment = new AddFeedDialogFragment();
                        newTermDialogFragment.show(fragmentManager, "Dialog");
                    } else if (fragmentCode == 4) {
                        AddFeedDialogFragment newTermDialogFragment = new AddFeedDialogFragment();
                        newTermDialogFragment.show(fragmentManager, "Dialog");
                    }
                }
            });

            LinearLayout sliderMenuParent = (LinearLayout) findViewById(R.id.sliderMenuParent);
            DisplayMetrics dm = getResources().getDisplayMetrics();
            final int val1 = 80;
            final int val2 = 100;
            sliderMenuParent.setLayoutParams(new LinearLayout.LayoutParams((dm.widthPixels * val1) / val2, LinearLayout.LayoutParams.MATCH_PARENT));
            startFragment(new RecipeFragment(), 0);
        } else {
            startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
        }

    }

    private void initViews() {
        btMenu = (ImageView) findViewById(R.id.btnMenu);
        btnCustom = (ImageView) findViewById(R.id.btnCustom);
        rvItems = (RecyclerView) findViewById(R.id.rvItems);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
    }


    public void setSlideRole(int res) {
        if (null == mSlideMenu) {
            return;
        }

        getLayoutInflater().inflate(res, mSlideMenu, true);
    }

    public SlideMenu getSlideMenu() {
        return mSlideMenu;
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mSlideMenu = (SlideMenu) findViewById(R.id.slideMenu);
        setSlideRole(R.layout.slider_menu);
        setSlideRole(R.layout.activity_dashboard);
        mSlideMenu = getSlideMenu();
        getSlideMenu().setOnSlideStateChangeListener(this);

    }

    @Override
    public void onSlideStateChange(int slideState) {
        mSlideState = slideState;
    }

    @Override
    public void onSlideOffsetChange(float offsetPercent) {
        mOffsetPercent = offsetPercent;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mOffsetPercent = savedInstanceState.getFloat(OFFSET_PERCENT);
        mSlideState = savedInstanceState.getInt(SLIDE_STATE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat(OFFSET_PERCENT, mOffsetPercent);
        outState.putInt(SLIDE_STATE, mSlideState);
    }

    public void startFragment(Fragment fragment, int pos) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (currentFragment != null) {
            fragmentTransaction.remove(currentFragment);
            fragmentTransaction.replace(R.id.fragment, fragment);
        } else {
            fragmentTransaction.add(R.id.fragment, fragment);
        }
        fragmentTransaction.commit();
        currentFragment = fragment;

        for (int i = 0; i < menuItems.size(); i++) {
            menuItems.get(i).setSelected(false);
        }

        menuItems.get(pos).setSelected(true);

    }

    private void setupMenu() {
        String[] menuArr = new String[7];
        menuArr[0] = getString(R.string.home);
        menuArr[1] = getString(R.string.recipe);
        menuArr[2] = getString(R.string.workout);
        menuArr[3] = getString(R.string.nutritionInfo);
        menuArr[4] = getString(R.string.profile);
        menuArr[5] = getString(R.string.feedback);
        menuArr[6] = getString(R.string.logout);

        Integer[] iconArr = new Integer[7];
        iconArr[0] = R.drawable.ic_006_home;
        iconArr[1] = R.drawable.ic_005_chef;
        iconArr[2] = R.drawable.ic_004_dumbbell_with_weights_outline;
        iconArr[3] = R.drawable.ic_003_milk_products;
        iconArr[4] = R.drawable.ic_002_dumbbell;
        iconArr[5] = R.drawable.ic_message;
        iconArr[6] = R.drawable.ic_logout;

        menuItems = new ArrayList<>();

        for (int i = 0; i < menuArr.length; i++) {
            SliderItemModel model = new SliderItemModel();
            model.setName(menuArr[i]);
            model.setIcon(iconArr[i]);
            menuItems.add(model);
        }

        SliderMenuAdapter menuAdapter = new SliderMenuAdapter(mContext, menuItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvItems.setLayoutManager(layoutManager);
        rvItems.setItemAnimator(new DefaultItemAnimator());
        rvItems.setAdapter(menuAdapter);

    }


    public void performAction(int position) {
        fragmentCode = position;
        if (!menuItems.get(position).isSelected()) {
            if (position == 0) {
                tvTitle.setText(menuItems.get(position).getName().toUpperCase());
                startFragment(new HomeFragment(), position);
            } else if (position == 1) {
                tvTitle.setText(menuItems.get(position).getName().toUpperCase());
                startFragment(new RecipeFragment(), position);
                mSlideMenu.close(true);
            } else if (position == 2) {
                tvTitle.setText(menuItems.get(position).getName().toUpperCase());
                startFragment(new WorkoutFragment(), position);
                mSlideMenu.close(true);
            } else if (position == 3) {
                tvTitle.setText(menuItems.get(position).getName().toUpperCase());
                startFragment(new NutritionInfoFragment(), position);
                mSlideMenu.close(true);
            } else if (position == 4) {
                tvTitle.setText(menuItems.get(position).getName().toUpperCase());
                startFragment(new ProfileFragment(), position);
                mSlideMenu.close(true);
            } else if (position == 5) {
                tvTitle.setText(menuItems.get(position).getName().toUpperCase());
                startFragment(new FeedbackFragment(), position);
                mSlideMenu.close(true);
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
