package com.titinkurniati.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by Titin Kurniati on 11-Jun-16.
 */
public class MachineModel implements Parcelable {
    private int machine_id;
    private int machine_barcode_number;
    private String machine_name;
    private String machine_type;
    private String last_maintenance;

    public MachineModel() {

    }
    protected MachineModel(Parcel in) {
        machine_id = in.readInt();
        machine_barcode_number = in.readInt();
        machine_name = in.readString();
        machine_type = in.readString();
        last_maintenance = in.readString();
    }

    public static final Creator<MachineModel> CREATOR = new Creator<MachineModel>() {
        @Override
        public MachineModel createFromParcel(Parcel in) {
            return new MachineModel(in);
        }

        @Override
        public MachineModel[] newArray(int size) {
            return new MachineModel[size];
        }
    };

    @Override
    public String toString() {
        return "MachineModel{" +
                "machine_id=" + machine_id +
                ", machine_barcode_number=" + machine_barcode_number +
                ", machine_name='" + machine_name + '\'' +
                ", machine_type='" + machine_type + '\'' +
                ", last_maintenance='" + last_maintenance + '\'' +
                '}';
    }


    public void MachineModel(int machine_id, int machine_barcode_number, String machine_name, String machine_type, String last_maintenance) {
        this.machine_id = machine_id;
        this.machine_barcode_number = machine_barcode_number;
        this.machine_name = machine_name;
        this.machine_type = machine_type;
        this.last_maintenance = last_maintenance;
    }

    public int getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(int machine_id) {
        this.machine_id = machine_id;
    }

    public int getMachine_barcode_number() {
        return machine_barcode_number;
    }

    public void setMachine_barcode_number(int machine_barcode_number) {
        this.machine_barcode_number = machine_barcode_number;
    }

    public String getMachine_name() {
        return machine_name;
    }

    public void setMachine_name(String machine_name) {
        this.machine_name = machine_name;
    }

    public String getMachine_type() {
        return machine_type;
    }

    public void setMachine_type(String machine_type) {
        this.machine_type = machine_type;
    }

    public String getLast_maintenance() {
        return last_maintenance;
    }

    public void setLast_maintenance(String last_maintenance) {
        this.last_maintenance = last_maintenance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(machine_id);
        dest.writeInt(machine_barcode_number);
        dest.writeString(machine_name);
        dest.writeString(machine_type);
        dest.writeString(last_maintenance);
    }

}