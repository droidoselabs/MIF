package in.droidoselabs.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerifyEmailActivity extends BaseActivity implements View.OnClickListener {
    private AppCompatButton verifyEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);
        verifyEmail = (AppCompatButton) findViewById(R.id.verifyEmail);
        verifyEmail.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(VerifyEmailActivity.this, LoginActivity.class));
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verifyEmail:
                sendVerificationEmail();
                break;
            default:
                break;
        }
    }

    private void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            showSnackbar("Verification email has been sent to your mail id.");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(VerifyEmailActivity.this, LoginActivity.class));
                                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                                    finish();
                                }
                            }, 2000);

                        }
                    }
                });
    }

    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar
                .make(verifyEmail, message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }
}
