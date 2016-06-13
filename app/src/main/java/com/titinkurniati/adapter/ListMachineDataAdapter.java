package com.titinkurniati.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.titinkurniati.imagemachine.EditDataActivity;
import com.titinkurniati.imagemachine.IntenKey;
import com.titinkurniati.imagemachine.MachineDataActivity;
import com.titinkurniati.imagemachine.MachineDatadetailActivity;
import com.titinkurniati.imagemachine.R;
import com.titinkurniati.model.MachineModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Titin Kurniati on 12-Jun-16.
 */
public class ListMachineDataAdapter extends BaseAdapter {
    private Context context;
    private List<MachineModel> listMachineModel;
    private ItemClick listener = null;

    public ListMachineDataAdapter(Context context, List<MachineModel> machineModelList) {
        this.context = context;
        this.listMachineModel = machineModelList;
        this.listener = listener;
    }

    public interface ItemClick {
        public void btnEditClicked(int position, MachineModel mm);

        public void btnDeleteClicked(int position, MachineModel mm);

    }

    public void listener(ItemClick listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return listMachineModel.size();
    }

    @Override
    public Object getItem(int position) {
        return listMachineModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listMachineModel.get(position).getMachine_id();
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view;
        final ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_data, null);

            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.type = (TextView) view.findViewById(R.id.type);
            viewHolder.btn_edit = (Button) view.findViewById(R.id.btn_edit);
            viewHolder.btn_delete = (Button) view.findViewById(R.id.btn_delete);

            viewHolder.name.setText(listMachineModel.get(position).getMachine_name());
            viewHolder.type.setText(listMachineModel.get(position).getMachine_type());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<MachineModel> arrayList = new ArrayList<MachineModel>(listMachineModel);
                    Intent intent = new Intent(context, MachineDatadetailActivity.class);
                    intent.putExtra(IntenKey.POSITION, position);
                    intent.putParcelableArrayListExtra(IntenKey.MODEL_LIST, arrayList);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(intent);

                }
            });

            viewHolder.btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MachineModel modelEdit = listMachineModel.get(position);
                    List<MachineModel> list = new ArrayList<MachineModel>();
                    list.add(modelEdit);
                    ArrayList<MachineModel> mmArray = new ArrayList<MachineModel>(list);

                    Intent intent = new Intent(context, EditDataActivity.class);
                    intent.putParcelableArrayListExtra(IntenKey.MODEL_SINGLE, mmArray);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ((Activity) context).finish();
                    context.startActivity(intent);

                }
            });

            viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    com.titinkurniati.imagemachine.SQLiteDatabase db = new com.titinkurniati.imagemachine.SQLiteDatabase(context);
                    db.deleteData(listMachineModel.get(position));
                    Intent intent = new Intent(context, MachineDataActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ((Activity) context).finish();
                    context.startActivity(intent);
                }
            });

            notifyDataSetChanged();
        } else {
            view = (View) convertView;
        }

        return view;
    }

    static class ViewHolder {
        TextView name;
        TextView type;
        Button btn_edit;
        Button btn_delete;
    }


}