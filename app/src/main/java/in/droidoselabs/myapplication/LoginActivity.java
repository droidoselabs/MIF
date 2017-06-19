package in.droidoselabs.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    String eMail, pass;
    private TextView loginText, signupText;
    private EditText email, password;
    private AppCompatButton login;
    private boolean isValidated = false;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mProgressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        loginText = (TextView) findViewById(R.id.toolbarText);
        signupText = (TextView) findViewById(R.id.signupBtn);
        login = (AppCompatButton) findViewById(R.id.login);
        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        loginText.setText("Login Here !");
        signupText.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signupBtn:
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                finish();
                break;
            case R.id.login:
                eMail = email.getText().toString().trim();
                pass = password.getText().toString().trim();
                if (isValidated()) {
                    mProgressDialog.setMessage("Signing In ...");
                    mProgressDialog.show();
                    signInwithFirefase();
                }
                break;
            default:
                break;
        }
    }

    private void signInwithFirefase() {
        mAuth.signInWithEmailAndPassword(eMail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mProgressDialog.dismiss();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user.isEmailVerified()) {
                                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                            } else {
                                startActivity(new Intent(LoginActivity.this, VerifyEmailActivity.class));
                            }
                            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                            finish();
                        } else {
                            if (!task.isSuccessful()) {
                                mProgressDialog.dismiss();
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    showSnackbar("Weak Password !");
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    showSnackbar("Invalid Email !");
                                } catch (FirebaseAuthUserCollisionException e) {
                                    showSnackbar("User Already Exists !");
                                } catch (Exception e) {
                                    Log.e(TAG, e.getMessage());
                                }
                            }
                        }
                    }
                });
    }

    public boolean isValidated() {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(eMail).matches()) {
            email.requestFocus();
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            showSnackbar("Enter a valid email address !");
            isValidated = false;
        } else if (pass.length() < 8) {
            password.requestFocus();
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            showSnackbar("Enter password of atleast 8 alpha-numerics !");
            isValidated = false;
        } else {
            isValidated = true;
        }
        return isValidated;
    }

    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar
                .make(loginText, message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }
}
