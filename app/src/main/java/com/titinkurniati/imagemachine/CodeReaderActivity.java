package com.titinkurniati.imagemachine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.barcode.reader.IntentIntegrator;
import com.barcode.reader.IntentResult;
import com.titinkurniati.model.MachineModel;

import java.util.ArrayList;

/**
 * Created by Titin Kurniati on 11-Jun-16.
 */
public class CodeReaderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_reader);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            String resultString = "" + scanResult;
            boolean isInteger = Utility.isInteger(resultString);

            if (scanResult != null && (resultString.length() <= 10) && (isInteger == true)) {
                // handle scan result
                SQLiteDatabase ad = new SQLiteDatabase(this);
                MachineModel mm = null;
                mm = ad.find(Integer.valueOf(resultString));
                if (mm != null) {
                    ArrayList<MachineModel> arrayList = new ArrayList<MachineModel>();
                    arrayList.add(mm);

                    Intent in = new Intent(CodeReaderActivity.this, MachineDatadetailActivity.class);
                    in.putExtra(IntenKey.POSITION, 0);
                    in.putParcelableArrayListExtra(IntenKey.MODEL_LIST, arrayList);
                    finish();
                    startActivity(in);
                } else {
                    finish();
                }


            } else {
                onBackPressed();
            }

        }

    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
