package in.droidoselabs.myapplication;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class SignupActivity extends BaseActivity implements View.OnClickListener, Animation.AnimationListener {
    private TextView signupText;
    private AppCompatButton nextButtonOne, finish;
    private LinearLayout signup1ll, signup2ll;
    private View rectangleOne, circleOne, rectangleTwo, circleTwo;
    private Animation animFadein, animFadeoutImage, animFadeoutImage2, animFadeout, animFadeinImage;
    private ImageView signupImageOne, signupImageTwo, checkOne, checkTwo, checkThree, bodyOne, bodyTwo, bodyThree,avatarOne,avatarTwo,avatarThree,avatarFour;
    private EditText height, weight, fname, lname, email, password, cpassword, age;
    private RadioGroup radioGroupGender;
    private boolean isValidated=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        animFadein.setAnimationListener(this);
        animFadeout.setAnimationListener(this);
        animFadeoutImage.setAnimationListener(this);
        animFadeoutImage2.setAnimationListener(this);
        nextButtonOne.setOnClickListener(this);
        finish.setOnClickListener(this);
        height.setOnClickListener(this);
        height.setFocusable(false);
        weight.setFocusable(false);
        weight.setOnClickListener(this);
        bodyOne.setOnClickListener(this);
        bodyTwo.setOnClickListener(this);
        bodyThree.setOnClickListener(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void init() {
        signupText = (TextView) findViewById(R.id.toolbarText);
        nextButtonOne = (AppCompatButton) findViewById(R.id.nextButtonOne);
        finish = (AppCompatButton) findViewById(R.id.finish);
        signup1ll = (LinearLayout) findViewById(R.id.step1ll);
        signup2ll = (LinearLayout) findViewById(R.id.step2ll);
        rectangleOne = findViewById(R.id.rectangle_one);
        rectangleTwo = findViewById(R.id.rectangle_two);
        circleOne = findViewById(R.id.circle_one);
        circleTwo = findViewById(R.id.circle_two);
        signupImageOne = (ImageView) findViewById(R.id.signupImageOne);
        signupImageTwo = (ImageView) findViewById(R.id.signupImageTwo);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        fname = (EditText) findViewById(R.id.et_fname);
        lname = (EditText) findViewById(R.id.et_lname);
        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        cpassword = (EditText) findViewById(R.id.et_cpassword);
        age = (EditText) findViewById(R.id.et_age);
        checkOne = (ImageView) findViewById(R.id.chech_one);
        checkTwo = (ImageView) findViewById(R.id.check_two);
        checkThree = (ImageView) findViewById(R.id.check_three);
        bodyOne = (ImageView) findViewById(R.id.body_one);
        bodyTwo = (ImageView) findViewById(R.id.body_two);
        bodyThree = (ImageView) findViewById(R.id.body_three);
;

        radioGroupGender = (RadioGroup) findViewById(R.id.radioGroup);


        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.male) {
                    bodyOne.setImageResource(R.drawable.boy_one);
                    bodyTwo.setImageResource(R.drawable.boy_two);
                    bodyThree.setImageResource(R.drawable.boy_three);
                } else {
                    bodyOne.setImageResource(R.drawable.girl_one);
                    bodyTwo.setImageResource(R.drawable.girl_twoo);
                    bodyThree.setImageResource(R.drawable.girl_thre);
                }
            }
        });

        signupText.setText("SignUp Here !");
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        animFadeoutImage = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out_image);
        animFadeoutImage2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out_image);
        animFadeout = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);
        animFadeinImage = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in_image);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextButtonOne:
                if (isStepOneVerified()){
                    makeStepTwoVisible();
                }
                break;
            case R.id.finish:
                break;
            case R.id.height:
                openHeightSelectorPopup();
                break;
            case R.id.weight:
                openWeightSelectorPopup();
                break;
            case R.id.body_one:
                selectBodyType("Ectomorph");
                break;
            case R.id.body_two:
                selectBodyType("Mesomorph");
                break;
            case R.id.body_three:
                selectBodyType("Endomorph");
                break;
            default:
                break;
        }
    }

    private void selectBodyType(String bodyType) {
        if (bodyType.equals("Ectomorph")) {
            checkOne.setVisibility(View.VISIBLE);
            checkTwo.setVisibility(View.INVISIBLE);
            checkThree.setVisibility(View.INVISIBLE);
        } else if (bodyType.equals("Mesomorph")) {
            checkOne.setVisibility(View.INVISIBLE);
            checkTwo.setVisibility(View.VISIBLE);
            checkThree.setVisibility(View.INVISIBLE);
        } else {
            checkOne.setVisibility(View.INVISIBLE);
            checkTwo.setVisibility(View.INVISIBLE);
            checkThree.setVisibility(View.VISIBLE);
        }

    }

    private void openWeightSelectorPopup() {
        final Dialog dialog = new Dialog(SignupActivity.this);
        dialog.setContentView(R.layout.number_picker);
        final NumberPicker feet = (NumberPicker) dialog.findViewById(R.id.feet);
        final NumberPicker inches = (NumberPicker) dialog.findViewById(R.id.inches);
        final NumberPicker cms = (NumberPicker) dialog.findViewById(R.id.cms);
        final LinearLayout feetPicker = (LinearLayout) dialog.findViewById(R.id.feetPicker);
        final Switch switchs = (Switch) dialog.findViewById(R.id.change_height);
        final TextView heightText = (TextView) dialog.findViewById(R.id.height_text);
        AppCompatButton closeDialog = (AppCompatButton) dialog.findViewById(R.id.closeDialog);
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (feetPicker.getVisibility() == View.VISIBLE) {
                    weight.setText(feet.getValue() + "." + inches.getValue() + " Kgs");
                } else {
                    weight.setText(cms.getValue() + " Pounds");
                }
                dialog.dismiss();
            }
        });

        final Spannable text = new SpannableString("Kgs / Pounds");
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 6, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        heightText.setText(text);
        switchs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cms.setVisibility(View.VISIBLE);
                    feetPicker.setVisibility(View.GONE);
                    text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)), 6, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    heightText.setText(text);
                } else {
                    cms.setVisibility(View.GONE);
                    feetPicker.setVisibility(View.VISIBLE);
                    text.setSpan(new ForegroundColorSpan(Color.WHITE), 6, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    heightText.setText(text);
                }
            }
        });

        feet.setMaxValue(200);
        inches.setMaxValue(9);
        feet.setMinValue(1);
        feet.setMinValue(1);
        cms.setMaxValue(440);
        cms.setMinValue(1);
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();
    }

    private void openHeightSelectorPopup() {
        final Dialog dialog = new Dialog(SignupActivity.this);
        dialog.setContentView(R.layout.number_picker);
        final NumberPicker feet = (NumberPicker) dialog.findViewById(R.id.feet);
        final NumberPicker inches = (NumberPicker) dialog.findViewById(R.id.inches);
        final NumberPicker cms = (NumberPicker) dialog.findViewById(R.id.cms);
        final LinearLayout feetPicker = (LinearLayout) dialog.findViewById(R.id.feetPicker);
        final Switch switchs = (Switch) dialog.findViewById(R.id.change_height);
        final TextView heightText = (TextView) dialog.findViewById(R.id.height_text);
        AppCompatButton closeDialog = (AppCompatButton) dialog.findViewById(R.id.closeDialog);
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (feetPicker.getVisibility() == View.VISIBLE) {
                    height.setText(feet.getValue() + "' " + inches.getValue() + "\"");
                } else {
                    height.setText(cms.getValue() + " Cms");
                }
                dialog.dismiss();
            }
        });

        final Spannable text = new SpannableString("Feet / Cms");
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 7, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        heightText.setText(text);
        switchs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cms.setVisibility(View.VISIBLE);
                    feetPicker.setVisibility(View.GONE);
                    text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)), 7, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    heightText.setText(text);
                } else {
                    cms.setVisibility(View.GONE);
                    feetPicker.setVisibility(View.VISIBLE);
                    text.setSpan(new ForegroundColorSpan(Color.WHITE), 7, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    heightText.setText(text);
                }
            }
        });

        feet.setMaxValue(7);
        inches.setMaxValue(11);
        feet.setMinValue(1);
        feet.setMinValue(1);
        cms.setMaxValue(244);
        cms.setMinValue(1);
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();
    }

//    private void makeStepThreeVisible() {
//        signupImageOne.setVisibility(View.GONE);
//        signupImageTwo.setVisibility(View.GONE);
//        signupImageThree.setVisibility(View.VISIBLE);
//        signupImageTwo.startAnimation(animFadeoutImage2);
//        signupImageThree.startAnimation(animFadeinImage);
//        rectangleTwo.setBackgroundResource(R.drawable.rectangle_view_filled);
//        circleTwo.setBackgroundResource(R.drawable.circle_view_filled);
//    }

    private void makeStepTwoVisible() {
        signup1ll.setVisibility(View.GONE);
        signup2ll.setVisibility(View.VISIBLE);
        signup1ll.startAnimation(animFadeout);
        signup2ll.startAnimation(animFadein);
        signupImageOne.startAnimation(animFadeoutImage);
        signupImageOne.setVisibility(View.GONE);
        signupImageTwo.setVisibility(View.VISIBLE);
        signupImageTwo.startAnimation(animFadeinImage);
        rectangleOne.setBackgroundResource(R.drawable.rectangle_view_filled);
        circleOne.setBackgroundResource(R.drawable.circle_view_filled);
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

    public boolean isStepOneVerified(){

        return isValidated;

    }

    public void onSnackbarClicked(String message) {
        Snackbar snackbar = Snackbar
                .make(signup1ll, message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

}
