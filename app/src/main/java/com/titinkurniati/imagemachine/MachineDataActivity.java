package com.titinkurniati.imagemachine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.titinkurniati.adapter.ListMachineDataAdapter;
import com.titinkurniati.model.MachineModel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Titin Kurniati on 11-Jun-16.
 */
public class MachineDataActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public static TextView textView_selected_date;
    List<MachineModel> machineModels;
    List<MachineModel> machineModelListFromDb;
    ListMachineDataAdapter adapter;
    SQLiteDatabase db;
    private TextView textView_id;
    private EditText editText_barcode;
    private EditText editText_name;
    private EditText editText_type;
    private Button btn_sel_date;
    private Button btn_add;
    private Spinner btn_sort;
    private ListView list_data;
    private String getText_id = "";
    private String getText_bn = "";
    private String getText_name = "";
    private String getText_type = "";
    private String getText_date= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_data);
        initView();
        init();
    }

    public void initView(){
        textView_id = (TextView) findViewById(R.id.edit_text_m_id);
        editText_barcode = (EditText) findViewById(R.id.edit_text_m_bn);
        editText_name = (EditText) findViewById(R.id.edit_text_m_name);
        editText_type = (EditText) findViewById(R.id.edit_text_m_type);
        textView_selected_date = (TextView) findViewById(R.id.selected_date);
        btn_sel_date = (Button) findViewById(R.id.button_date);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_sort = (Spinner) findViewById(R.id.btn_spinner);
        list_data = (ListView) findViewById(R.id.list_data);

        List<String> list = new ArrayList<String>();
        list.add("MACHINE NAME");
        list.add("MACHINE TYPE");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        btn_sort.setAdapter(dataAdapter);
        btn_sort.setOnItemSelectedListener(this);

    }

    public void init() {
        int generate_id = Utility.generateNumber();
        textView_id.setText("" + generate_id);

        try {
            String x = "";
            InputStream is = this.getResources().openRawResource(R.raw.jsonfile);
            byte[] buffer = new byte[is.available()];
            while (is.read(buffer) != -1) ;
            String jsontext = new String(buffer);

            Gson gson = new Gson();
            machineModels = gson.fromJson(jsontext.toString(),
                    new TypeToken<List<MachineModel>>() {
                    }.getType());

            db = new SQLiteDatabase(this);
            List<MachineModel> mm_first = db.getAllData();

            //add to database
            if (mm_first.size() == 0) {
                for (MachineModel mm : machineModels) {
                    db.addEntry(mm.getMachine_id(), mm.getMachine_barcode_number(), mm.getMachine_name(), mm.getMachine_type(), mm.getLast_maintenance());
                }
            }

            machineModelListFromDb = db.getAllData();

            Collections.sort(machineModelListFromDb, new Comparator<MachineModel>() {
                @Override
                public int compare(MachineModel lhs, MachineModel rhs) {
                    return (lhs.getMachine_name().toLowerCase()).compareTo((rhs.getMachine_name().toLowerCase()));
                }
            });
            adapter = new ListMachineDataAdapter(MachineDataActivity.this, machineModelListFromDb);
            list_data.setAdapter(adapter);

        } catch (Exception e) {

        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                getText_id = textView_id.getText().toString();
                getText_bn = editText_barcode.getText().toString();
                getText_name = editText_name.getText().toString();
                getText_type = editText_type.getText().toString();
                getText_date = textView_selected_date.getText().toString();

                if (!getText_date.equals("") && !getText_bn.equals("") && !getText_name.equals("") && !getText_type.equals("")
                        && !getText_date.equals("")){
                    //add to database
                    db.addEntry(Integer.parseInt(getText_id),Integer.parseInt(getText_bn), getText_name, getText_type, getText_date);
                    finish();
                    startActivity(getIntent());
                } else {

                    AlertDialog alertDialog = new AlertDialog.Builder(MachineDataActivity.this).create();
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

                break;
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(machineModelListFromDb != null) {
            String text = parent.getItemAtPosition(position).toString();
            if (text.equals("MACHINE TYPE")) {
                Collections.sort(machineModelListFromDb, new Comparator<MachineModel>() {
                    @Override
                    public int compare(MachineModel lhs, MachineModel rhs) {
                        return (lhs.getMachine_type().toLowerCase()).compareTo((rhs.getMachine_type().toLowerCase()));
                    }
                });


            } else {
                Collections.sort(machineModelListFromDb, new Comparator<MachineModel>() {
                    @Override
                    public int compare(MachineModel lhs, MachineModel rhs) {
                        return (lhs.getMachine_name().toLowerCase()).compareTo((rhs.getMachine_name().toLowerCase()));
                    }
                });

            }
            adapter = new ListMachineDataAdapter(MachineDataActivity.this, machineModelListFromDb);
            list_data.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void showDatePickerDialog(View v) {
        DatePickerCustom newFragment = new DatePickerCustom();
        newFragment.setTextView(textView_selected_date);
        newFragment.show(getSupportFragmentManager(),"datePicker");

    }

}
