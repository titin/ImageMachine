package com.titinkurniati.imagemachine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/**
 * Created by Titin Kurniati on 12-Jun-16.
 */
public class Utility {

    public static int generateNumber(){
        int minNumber = 1;
        int maxNumber = 100;

        Random random = new Random();
        int randomNumber = random.nextInt((maxNumber - minNumber) + minNumber) + minNumber;

        return randomNumber;
    }

    public static boolean isInteger( String input )
    {
        try
        {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e)
        {
            return false;
        }
    }

    public static ImageLoader initImageLoader(Context context) {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
                context).defaultDisplayImageOptions(defaultOptions).memoryCache(
                new WeakMemoryCache());

        ImageLoaderConfiguration config = builder.build();
        ImageLoader imageLoader;
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);

        return imageLoader;
    }

}
