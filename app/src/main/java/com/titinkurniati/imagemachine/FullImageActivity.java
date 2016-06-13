package com.titinkurniati.imagemachine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

/**
 * Created by Titin Kurniati on 13-Jun-16.
 */
public class FullImageActivity extends Activity {
    ImageLoader imageLoader;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        // get intent data
        Intent i = getIntent();

        // Selected image id
        String position = i.getStringExtra("image");
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);

    }

}
