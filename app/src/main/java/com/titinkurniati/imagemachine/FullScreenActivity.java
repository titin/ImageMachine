package com.titinkurniati.imagemachine;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by Titin Kurniati on 14-Jun-16.
 */
public class FullScreenActivity extends Activity {

    ImageLoader imageLoader;
    private ImageView img_full;
    private CustomGallery cg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_full_screen);
        imageLoader = Utility.initImageLoader(FullScreenActivity.this);
        img_full = (ImageView) findViewById(R.id.img_full);
        String filepath = getIntent().getStringExtra(IntenKey.IMAGE);

        imageLoader.displayImage(filepath,
                img_full, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        img_full.setImageResource(R.mipmap.ic_launcher);
                        super.onLoadingStarted(imageUri, view);
                    }
                });
    }

}
