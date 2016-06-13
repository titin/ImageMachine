package com.titinkurniati.imagemachine;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.titinkurniati.model.MachineModel;

import java.util.List;

/**
 * Created by Titin Kurniati on 13-Jun-16.
 */
public class EditDataActivity extends Activity implements View.OnClickListener{

    private EditText editText_id;
    private EditText editText_barcode;
    private EditText editText_name;
    private EditText editText_type;
    private TextView textView_selected_date;
    private Button btn_ok;
    private List<MachineModel> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        Intent intent = getIntent();
        list_data = intent.getParcelableArrayListExtra(IntenKey.MODEL_SINGLE);

        editText_id = (EditText) findViewById(R.id.edit_m_id);
        editText_barcode = (EditText) findViewById(R.id.edit_m_bn);
        editText_name = (EditText) findViewById(R.id.edit_m_name);
        editText_type = (EditText) findViewById(R.id.edit_m_type);
        textView_selected_date = (TextView) findViewById(R.id.edit_selected_date);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);

        editText_id.setText("" + list_data.get(0).getMachine_id());
        editText_barcode.setText("" + list_data.get(0).getMachine_barcode_number());
        editText_name.setText(list_data.get(0).getMachine_name());
        editText_type.setText(list_data.get(0).getMachine_type());
        textView_selected_date.setText(list_data.get(0).getLast_maintenance());

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_ok){
            String getText_id = editText_id.getText().toString();
            String getText_bn = editText_barcode.getText().toString();
            String getText_name = editText_name.getText().toString();
            String getText_type = editText_type.getText().toString();
            String getText_date= textView_selected_date.getText().toString();

            com.titinkurniati.imagemachine.SQLiteDatabase db = new com.titinkurniati.imagemachine.SQLiteDatabase(this);
            if (!getText_date.equals("") && !getText_bn.equals("") && !getText_name.equals("") && !getText_type.equals("")
                    && !getText_date.equals("")) {
                db.updatedata(Integer.parseInt(getText_id), Integer.parseInt(getText_bn), getText_name, getText_type, getText_date);

                Intent i = new Intent(EditDataActivity.this, MachineDataActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                finish();
                startActivity(i);

            } else {
                AlertDialog alertDialog = new AlertDialog.Builder(EditDataActivity.this).create();
                alertDialog.setTitle(getResources().getString(R.string.alert));
                alertDialog.setMessage(getResources().getString(R.string.empty));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getResources().getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
