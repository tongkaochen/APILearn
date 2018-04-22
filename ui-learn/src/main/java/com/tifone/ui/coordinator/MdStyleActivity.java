package com.tifone.ui.coordinator;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/4/9.
 */

public class MdStyleActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator_detail_view);
    }
}
