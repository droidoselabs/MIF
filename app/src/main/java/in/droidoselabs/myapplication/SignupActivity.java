package in.droidoselabs.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {
    private TextView signupText;
    private AppCompatButton nextButtonOne, nextButtonTwo;
    private LinearLayout signup1ll, signup2ll;
    private View rectangleOne, circleOne, rectangleTwo, circleTwo;
    private Animation animFadein, animFadeoutImage, animFadeoutImage2, animFadeout, animFadeinImage;
    private ImageView signupImageOne, signupImageTwo, signupImageThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupText = (TextView) findViewById(R.id.toolbarText);
        nextButtonOne = (AppCompatButton) findViewById(R.id.nextButtonOne);
        nextButtonTwo = (AppCompatButton) findViewById(R.id.nextButtonTwo);
        signup1ll = (LinearLayout) findViewById(R.id.step1ll);
        signup2ll = (LinearLayout) findViewById(R.id.step2ll);
        rectangleOne = findViewById(R.id.rectangle_one);
        rectangleTwo = findViewById(R.id.rectangle_two);
        circleOne = findViewById(R.id.circle_one);
        circleTwo = findViewById(R.id.circle_two);
        signupImageOne = (ImageView) findViewById(R.id.signupImageOne);
        signupImageTwo = (ImageView) findViewById(R.id.signupImageTwo);
        signupImageThree = (ImageView) findViewById(R.id.signupImageThree);
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

        animFadein.setAnimationListener(this);
        animFadeout.setAnimationListener(this);
        animFadeoutImage.setAnimationListener(this);
        animFadeoutImage2.setAnimationListener(this);
        nextButtonOne.setOnClickListener(this);
        nextButtonTwo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextButtonOne:
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
                break;
            case R.id.nextButtonTwo:
                signupImageOne.setVisibility(View.GONE);
                signupImageTwo.setVisibility(View.GONE);
                signupImageThree.setVisibility(View.VISIBLE);
                signupImageTwo.startAnimation(animFadeoutImage2);
                signupImageThree.startAnimation(animFadeinImage);
                rectangleTwo.setBackgroundResource(R.drawable.rectangle_view_filled);
                circleTwo.setBackgroundResource(R.drawable.circle_view_filled);

                break;

        }
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
}
