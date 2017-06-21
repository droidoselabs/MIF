package in.droidoselabs.myapplication.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gun0912.tedpicker.Config;
import com.gun0912.tedpicker.ImagePickerActivity;

import java.util.ArrayList;

import in.droidoselabs.myapplication.R;

/**
 * Created by android on 6/22/17.
 */

public class AddFeedDialogFragment extends DialogFragment {
    private static final String TAG = "TedPicker";
    private static final int INTENT_REQUEST_GET_IMAGES = 13;
    ArrayList<Uri> image_uris = new ArrayList<Uri>();
    private LinearLayout getImage, parentLayout, childLayoutOne, childLayoutTwo;
    private ImageView ivOne, ivTwo, ivThree, ivFour, ivFive;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.add_feed_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        init(view);
        builder.setView(view);
        return builder.create();
    }

    private void init(View view) {
        getImage = (LinearLayout) view.findViewById(R.id.getImages);
        getImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Config config = new Config();
                config.setSelectionMin(1);
                config.setSelectionLimit(5);
                config.setFlashOn(true);
                getImages(config);
            }
        });
        parentLayout = (LinearLayout) view.findViewById(R.id.parent_layout);
        childLayoutOne = (LinearLayout) view.findViewById(R.id.layout_two);
        childLayoutTwo = (LinearLayout) view.findViewById(R.id.layout_three);
        //image Views
        ivTwo = (ImageView) view.findViewById(R.id.image_two);
        ivThree = (ImageView) view.findViewById(R.id.image_three);
        ivFour = (ImageView) view.findViewById(R.id.image_four);
        ivFive = (ImageView) view.findViewById(R.id.image_five);
        ivOne = (ImageView) view.findViewById(R.id.image_one);


    }

    private void getImages(Config config) {


        ImagePickerActivity.setConfig(config);

        Intent intent = new Intent(getActivity(), ImagePickerActivity.class);

        if (image_uris != null) {
            intent.putParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS, image_uris);
        }
        startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);


        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == INTENT_REQUEST_GET_IMAGES) {

                image_uris = intent.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);

                if (image_uris != null) {
                    Log.d(TAG, "onActivityResult: " + image_uris);
                    setImages(image_uris.size());
                }


            }
        }
    }

    private void setImages(int size) {
        if (size > 0) {
            parentLayout.setVisibility(View.VISIBLE);
            if (size == 1) {
                ivOne.setVisibility(View.VISIBLE);
                ivOne.setImageURI(image_uris.get(0));
            } else if (size == 2) {
                childLayoutOne.setVisibility(View.VISIBLE);
                ivOne.setVisibility(View.VISIBLE);
                ivTwo.setVisibility(View.VISIBLE);
                ivOne.setImageURI(image_uris.get(0));
                ivTwo.setImageURI(image_uris.get(1));
            } else if (size == 3) {
                childLayoutOne.setVisibility(View.VISIBLE);
                ivOne.setVisibility(View.VISIBLE);
                ivTwo.setVisibility(View.VISIBLE);
                ivThree.setVisibility(View.VISIBLE);
                ivOne.setImageURI(image_uris.get(0));
                ivTwo.setImageURI(image_uris.get(1));
                ivThree.setImageURI(image_uris.get(2));
            } else if (size == 4) {
                childLayoutOne.setVisibility(View.VISIBLE);
                childLayoutTwo.setVisibility(View.VISIBLE);
                ivOne.setVisibility(View.VISIBLE);
                ivTwo.setVisibility(View.VISIBLE);
                ivThree.setVisibility(View.VISIBLE);
                ivFour.setVisibility(View.VISIBLE);
                ivOne.setImageURI(image_uris.get(0));
                ivTwo.setImageURI(image_uris.get(1));
                ivThree.setImageURI(image_uris.get(2));
                ivFour.setImageURI(image_uris.get(3));
            } else if (size == 5) {
                childLayoutOne.setVisibility(View.VISIBLE);
                childLayoutTwo.setVisibility(View.VISIBLE);
                ivOne.setVisibility(View.VISIBLE);
                ivTwo.setVisibility(View.VISIBLE);
                ivThree.setVisibility(View.VISIBLE);
                ivFour.setVisibility(View.VISIBLE);
                ivFive.setVisibility(View.VISIBLE);
                ivOne.setImageURI(image_uris.get(0));
                ivTwo.setImageURI(image_uris.get(1));
                ivThree.setImageURI(image_uris.get(2));
                ivFour.setImageURI(image_uris.get(3));
                ivFive.setImageURI(image_uris.get(4));
            }

        }
    }
}

