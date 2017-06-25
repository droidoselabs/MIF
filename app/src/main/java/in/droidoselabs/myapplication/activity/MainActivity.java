package in.droidoselabs.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import in.droidoselabs.myapplication.R;

public class MainActivity extends AppCompatActivity implements WheelPicker.OnItemSelectedListener {

    private WheelPicker wheelLeft;
    private WheelPicker wheelCenter;
    private WheelPicker wheelRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_preview);
        wheelLeft = (WheelPicker) findViewById(R.id.main_wheel_left);
        wheelLeft.setOnItemSelectedListener(this);
//        wheelCenter = (WheelPicker) findViewById(R.id.main_wheel_center);
//        wheelCenter.setOnItemSelectedListener(this);
//        wheelRight = (WheelPicker) findViewById(R.id.main_wheel_right);
//        wheelRight.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        String text = "";
        switch (picker.getId()) {
            case R.id.main_wheel_left:
                text = "Left:";
                break;
//            case R.id.main_wheel_center:
//                text = "Center:";
//                break;
//            case R.id.main_wheel_right:
//                text = "Right:";
//                break;
        }
        Toast.makeText(this, text + String.valueOf(data), Toast.LENGTH_SHORT).show();
    }


}
