package com.tifone.learn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.tifone.learn.photoswall.PhotosPermissionActivity;
import com.tifone.learn.utils.SortUtils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView resultTv = findViewById(R.id.result_tv);

        resultTv.setText(new SortUtils().sort());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            // Photos wall
            case R.id.menu_photos_wall:
                intent.setClass(this, PhotosPermissionActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, getString(R.string.toast_no_available), Toast.LENGTH_SHORT).show();
        }
        return super.onMenuItemSelected(featureId, item);
    }


}
