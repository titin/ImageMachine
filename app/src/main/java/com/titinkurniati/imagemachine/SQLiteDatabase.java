package com.titinkurniati.imagemachine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;
import android.util.Log;

import com.titinkurniati.model.MachineModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Titin Kurniati on 11-Jun-16.
 */
public class SQLiteDatabase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ImageMachine.db";
    public static final String CREATE_TABLE_IMAGEMACHINE = "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" + FeedEntry.COLUMN_MACHINE_ID
            + " INTEGER PRIMARY KEY," + FeedEntry.COLUMN_MACHINE_BARCODE_NUMBER + " INTEGER," + FeedEntry.COLUMN_MACHINE_NAME + " TEXT," + FeedEntry.COLUMN_MACHINE_TYPE + " TEXT,"
            + FeedEntry.COLUMN_MAINTENANCE + " TEXT);";

    public SQLiteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_IMAGEMACHINE);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME);
        onCreate(db);
    }

    //add into table
    public void addEntry(int machine_id, int machine_barcode_number, String machine_name, String machine_type, String last_maintenance) throws SQLException {
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedEntry.COLUMN_MACHINE_ID, machine_id);
        contentValues.put(FeedEntry.COLUMN_MACHINE_BARCODE_NUMBER, machine_barcode_number);
        contentValues.put(FeedEntry.COLUMN_MACHINE_NAME, machine_name);
        contentValues.put(FeedEntry.COLUMN_MACHINE_TYPE, machine_type);
        contentValues.put(FeedEntry.COLUMN_MAINTENANCE, last_maintenance);
        db.insert(FeedEntry.TABLE_NAME, null, contentValues);
    }

    // get all data from table
    public List<MachineModel> getAllData() {
        List<MachineModel> machineModelsList = new LinkedList<MachineModel>();

        String query = "SELECT * FROM " + FeedEntry.TABLE_NAME;

        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        MachineModel machineModel = null;
        if (cursor.moveToFirst()) {
            do {
                machineModel = new MachineModel();
                machineModel.setMachine_id(Integer.parseInt(cursor.getString(0)));
                machineModel.setMachine_barcode_number(Integer.parseInt(cursor.getString(1)));
                machineModel.setMachine_name(cursor.getString(2));
                machineModel.setMachine_type(cursor.getString(3));
                machineModel.setLast_maintenance(cursor.getString(4));

                machineModelsList.add(machineModel);
            } while (cursor.moveToNext());
        }

        return machineModelsList;
    }

    public void deleteData(MachineModel machineModel) {

        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FeedEntry.TABLE_NAME, FeedEntry.COLUMN_MACHINE_ID + " = ?", new String[]{String.valueOf(machineModel.getMachine_id())});
        db.close();
    }

    public int updatedata(int machine_id, int machine_barcode_number, String machine_name, String machine_type, String last_maintenance) {

        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedEntry.COLUMN_MACHINE_ID, machine_id);
        contentValues.put(FeedEntry.COLUMN_MACHINE_BARCODE_NUMBER, machine_barcode_number);
        contentValues.put(FeedEntry.COLUMN_MACHINE_NAME, machine_name);
        contentValues.put(FeedEntry.COLUMN_MACHINE_TYPE, machine_type);
        contentValues.put(FeedEntry.COLUMN_MAINTENANCE, last_maintenance);

        int i = db.update(FeedEntry.TABLE_NAME,
                contentValues,
                FeedEntry.COLUMN_MACHINE_ID + " = ?",
                new String[]{String.valueOf(machine_id)});

        db.close();

        return i;

    }

    public MachineModel find(int data) {
        String query = "Select * FROM " + FeedEntry.TABLE_NAME + " WHERE " + FeedEntry.COLUMN_MACHINE_ID + " =  \"" + data + "\"";

        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        Log.wtf("","cursor == " + cursor);

        MachineModel machineModel = new MachineModel();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();

            machineModel = new MachineModel();
            machineModel.setMachine_id(Integer.parseInt(cursor.getString(0)));
            machineModel.setMachine_barcode_number(Integer.parseInt(cursor.getString(1)));
            machineModel.setMachine_name(cursor.getString(2));
            machineModel.setMachine_type(cursor.getString(3));
            machineModel.setLast_maintenance(cursor.getString(4));
            cursor.close();
        } else {
            machineModel = null;
        }
        db.close();
        return machineModel;
    }

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_MACHINE_ID = "machine_id";
        public static final String COLUMN_MACHINE_BARCODE_NUMBER = "machine_barcode_number";
        public static final String COLUMN_MACHINE_NAME = "machine_name";
        public static final String COLUMN_MACHINE_TYPE = "machine_type";
        public static final String COLUMN_MAINTENANCE = "last_maintenance";

    }

}
