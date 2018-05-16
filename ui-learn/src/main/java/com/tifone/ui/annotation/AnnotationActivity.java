package com.tifone.ui.annotation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/5/7.
 */

public class AnnotationActivity extends AppCompatActivity {
    TextView mTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annotation_activity_layout);
        mTextView = findViewById(R.id.annotation_info_show_tv);
        String[] results = FruitUtils.setupFruitInfo(Apple.class);
        String showText = "";
        for (String result : results) {
            showText += result + "\n";
        }
        mTextView.setText(showText);
    }
}
