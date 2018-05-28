package com.tifone.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tifone.ui.animation.AnimationActivity;
import com.tifone.ui.annotation.AnnotationActivity;
import com.tifone.ui.calculator.CalculatorActivity;
import com.tifone.ui.coordinator.CoordinatorActivity;
import com.tifone.ui.customview.CustomViewActivity;
import com.tifone.ui.glide.GlideActivity;
import com.tifone.ui.json.JsonActivity;
import com.tifone.ui.notification.NotificationActivity;
import com.tifone.ui.okhttp.OkhttpActivity;
import com.tifone.ui.recyclerview.MyPullLoadActivity;
import com.tifone.ui.retrofit.RetrofitActivity;
import com.tifone.ui.rxjava.RxActivity;
import com.tifone.ui.surfaceview.ViewActivity;
import com.tifone.ui.viewpager.ViewPagerActivity;
import com.tifone.ui.xmlparser.XmlParserActivity;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button mOpenRecyclerViewBtn;
    private Button mOpenCoordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOpenRecyclerViewBtn = (Button) findViewById(R.id.open_recycler_view_btn);
        mOpenRecyclerViewBtn.setOnClickListener(this);
        //
        mOpenCoordinator = (Button) findViewById(R.id.open_coordinator_btn);
        mOpenCoordinator.setOnClickListener(this);

        Button surfaceViewBtn = (Button) findViewById(R.id.open_surfaceview_btn);
        surfaceViewBtn.setOnClickListener(this);

        findViewById(R.id.open_animation_btn).setOnClickListener(this);
        findViewById(R.id.open_calculator).setOnClickListener(this);
        findViewById(R.id.open_json).setOnClickListener(this);
        findViewById(R.id.open_view_pager_btn).setOnClickListener(this);
        findViewById(R.id.open_okhttp_btn).setOnClickListener(this);
        findViewById(R.id.open_rxjava_btn).setOnClickListener(this);
        findViewById(R.id.open_custom_view_btn).setOnClickListener(this);
        findViewById(R.id.open_xml_parser_btn).setOnClickListener(this);
        findViewById(R.id.open_notification_btn).setOnClickListener(this);
        findViewById(R.id.open_retrofit_btn).setOnClickListener(this);
        findViewById(R.id.open_annotation_btn).setOnClickListener(this);
        findViewById(R.id.open_glide_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.open_coordinator_btn:
                intent.setClass(MainActivity.this, CoordinatorActivity.class);
                break;
            case R.id.open_recycler_view_btn:
                intent.setClass(MainActivity.this, MyPullLoadActivity.class);
                break;
            case R.id.open_surfaceview_btn:
                intent.setClass(MainActivity.this, ViewActivity.class);
                break;
            case R.id.open_animation_btn:
                intent.setClass(MainActivity.this, AnimationActivity.class);
                break;
            case R.id.open_calculator:
                intent.setClass(MainActivity.this, CalculatorActivity.class);
                break;
            case R.id.open_json:
                intent.setClass(MainActivity.this, JsonActivity.class);
                break;
            case R.id.open_view_pager_btn:
                intent.setClass(MainActivity.this, ViewPagerActivity.class);
                break;
            case R.id.open_okhttp_btn:
                intent.setClass(MainActivity.this, OkhttpActivity.class);
                break;
            case R.id.open_rxjava_btn:
                intent.setClass(MainActivity.this, RxActivity.class);
                break;
            case R.id.open_custom_view_btn:
                intent.setClass(MainActivity.this, CustomViewActivity.class);
                break;
            case R.id.open_xml_parser_btn:
                intent.setClass(MainActivity.this, XmlParserActivity.class);
                break;
            case R.id.open_notification_btn:
                intent.setClass(MainActivity.this, NotificationActivity.class);
                break;
            case R.id.open_retrofit_btn:
                intent.setClass(MainActivity.this, RetrofitActivity.class);
                break;
            case R.id.open_annotation_btn:
                intent.setClass(MainActivity.this, AnnotationActivity.class);
                break;
            case R.id.open_glide_btn:
                intent.setClass(MainActivity.this, GlideActivity.class);
                break;
            default:
                return;

        }
        startActivity(intent);
    }
}
