package com.titinkurniati.imagemachine;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.titinkurniati.adapter.GalleryAdapter;
import com.titinkurniati.adapter.GridViewAdapter;
import com.titinkurniati.model.MachineModel;

import java.util.ArrayList;

/**
 * Created by Titin Kurniati on 12-Jun-16.
 */
public class MachineDatadetailActivity extends Activity {
    GridView gridGallery;
    ImageLoader imageLoader;
    GridViewAdapter adapter;
    private TextView number;
    private TextView name;
    private TextView type;
    private TextView barcode_number;
    private TextView last_maintenance;
    private Button add_btn;
    private ArrayList<MachineModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_data_detail);
        number = (TextView) findViewById(R.id.angka);
        name = (TextView) findViewById(R.id.nama);
        type = (TextView) findViewById(R.id.tipe);
        barcode_number = (TextView) findViewById(R.id.barkode_angka);
        last_maintenance = (TextView) findViewById(R.id.last_mainten);
        add_btn = (Button) findViewById(R.id.btn_machine_image);
        gridGallery = (GridView) findViewById(R.id.grid);

        imageLoader = Utility.initImageLoader(MachineDatadetailActivity.this);
        init();
    }

    public void init() {

        arrayList = getIntent().getParcelableArrayListExtra(IntenKey.MODEL_LIST);
        int pos = getIntent().getIntExtra(IntenKey.POSITION, 0);
        number.setText("" + arrayList.get(pos).getMachine_id());
        name.setText(arrayList.get(pos).getMachine_name());
        type.setText(arrayList.get(pos).getMachine_type());
        barcode_number.setText("" + arrayList.get(pos).getMachine_barcode_number());
        last_maintenance.setText(arrayList.get(pos).getLast_maintenance());
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Action.ACTION_MULTIPLE_PICK);
                startActivityForResult(i, 100);

            }
        });
        adapter = new GridViewAdapter(getApplicationContext(), imageLoader);
        gridGallery.setAdapter(adapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            String[] all_path = data.getStringArrayExtra("all_path");
            ArrayList<CustomGallery> dataT = new ArrayList<CustomGallery>(10);

            for (String string : all_path) {
                CustomGallery item = new CustomGallery();
                item.sdcardPath = string;
                dataT.add(item);
            }

            adapter.addAll(dataT);
        }
    }
}
