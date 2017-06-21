package in.droidoselabs.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.jorgecastillo.FillableLoader;

import in.droidoselabs.myapplication.R;
import in.droidoselabs.myapplication.helpers.Paths;

public class SplashScreenActivity extends AppCompatActivity {
private FillableLoader fillableLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        fillableLoader=(FillableLoader)findViewById(R.id.fillableLoader);
        fillableLoader.setSvgPath(Paths.INDOMINUS_REX);
    }
}
