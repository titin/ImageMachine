package com.titinkurniati.imagemachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

// cara pada umumnya, pertama
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Intent intent;
//        switch (item.getItemId()) {
//            case R.id.machine_data:
//                intent = new Intent(MainActivity.this, MachineDataActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.code_reader:
//                intent = new Intent(MainActivity.this, CodeReaderActivity.class);
//                startActivity(intent);
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

// cara yang disarankan dari situs android developer, kedua
    public void machineData(MenuItem item){
        Intent  intent = new Intent(MainActivity.this, MachineDataActivity.class);
        startActivity(intent);
    }

    public void CodeReader(MenuItem item){
        Intent intent = new Intent(MainActivity.this, CodeReaderActivity.class);
                startActivity(intent);
    }


}
