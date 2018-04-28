package com.tifone.ui.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tifone.ui.R;

/**
 * Created by tongkao.chen on 2018/4/28.
 */

public class NotificationActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1000;
    private static final String CHANNEL_ID = "test_channel";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity_layout);
        Button sendBtn = findViewById(R.id.notification_send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postNotification(NOTIFICATION_ID);
            }
        });
    }

    private Notification postNotification(int id) {
        // 1. 获取NotificationManager服务
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android O版本对通知做了限制，必须提供频道信息， 否则无法发送通知
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "test", NotificationManager.IMPORTANCE_DEFAULT);
            nm.createNotificationChannel(channel);
        }
        // 2. 创建Notification.Builder，通过建造者模式配置通知的相应信息
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        Bitmap bitMap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_account, options);
        getResources().openRawResource(R.drawable.ic_account);
        logger(bitMap + "");
        Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle("Notification title")
                .setContentText("This is a notification test")
                // 在状态栏显示的图标，标题图标
                .setSmallIcon(R.drawable.ic_notif_test1)
                // LargeIcon为右侧的头像
                //.setLargeIcon(Icon.createWithResource(this, R.drawable.ic_account));
                .setLargeIcon(bitMap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 为Android O版本的通知配置频道ID， 否则无法发送通知
            builder = builder.setChannelId(CHANNEL_ID);
        }
        // 3. 调用builder.build方法获取Notification对象
        Notification notification = builder.build();
        // 4. 调用NotificationManager的notify方法向系统发送通知
        nm.notify(id, notification);
        return notification;
    }

    private static void logger(String msg) {
        Log.e("tifone", msg);
    }
}
