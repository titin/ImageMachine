package com.titinkurniati.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.titinkurniati.imagemachine.CustomGallery;
import com.titinkurniati.imagemachine.FullScreenActivity;
import com.titinkurniati.imagemachine.IntenKey;
import com.titinkurniati.imagemachine.MachineDataActivity;
import com.titinkurniati.imagemachine.R;

import java.util.ArrayList;

/**
 * Created by Titin Kurniati on 14-Jun-16.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private ImageLoader imageLoader;
    private ArrayList<CustomGallery> data = new ArrayList<CustomGallery>();

    public GridViewAdapter(Context context, ImageLoader imageLoader) {
        this.context = context;
        this.imageLoader = imageLoader;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, null);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.imgQueue);

            imageLoader.displayImage("file://" + data.get(position).sdcardPath,
                    holder.img, new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {
                            holder.img.setImageResource(R.mipmap.ic_launcher);
                            super.onLoadingStarted(imageUri, view);
                        }
                    });

            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context.getApplicationContext(), FullScreenActivity.class);
                    i.putExtra(IntenKey.IMAGE, "file://" + data.get(position).sdcardPath);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });

        }
        return convertView;
    }

    public void addAll(ArrayList<CustomGallery> files) {

        try {
            this.data.clear();
            this.data.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();
    }


    public class ViewHolder {
        ImageView img;
    }
}
