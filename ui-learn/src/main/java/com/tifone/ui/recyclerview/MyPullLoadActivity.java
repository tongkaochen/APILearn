package com.tifone.ui.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.tifone.mfrv.pullload.PullLoadRecyclerView;
import com.tifone.mfrv.pullload.adapter.CommonViewHolder;
import com.tifone.mfrv.pullload.adapter.MultiItemsAdapter;
import com.tifone.ui.R;

import java.util.ArrayList;
import java.util.List;

public class MyPullLoadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_pull_load_activity_main);
        List<String> testData = new ArrayList<>();
        testData.add("a");
        testData.add("b");
        testData.add("c");
        testData.add("d");
        testData.add("e");
        testData.add("f");
        testData.add("g");
        testData.add("h");
        testData.add("i");
        final List<String> data = new ArrayList<>();
        data.add("1111");
        data.add("1111");
        data.add("1111");
        data.add("1111");
        data.add("1111");
        data.add("1111");
        data.add("1111");
        final PullLoadRecyclerView recyclerView = findViewById(R.id.pull_load_rcv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final MyMultiItemAdapter adapter = new MyMultiItemAdapter(this);
        adapter.setDataSet(testData);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnRefreshListener(new PullLoadRecyclerView.OnRefreshListener() {
            @Override
            public void onRefreshStarted() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addDataSet(data);
                        recyclerView.notifyRefreshCompleted();
                    }
                }, 2000);
            }
        });
        recyclerView.setOnLoadListener(new PullLoadRecyclerView.OnLoadListener() {
            @Override
            public void onLoadStarted() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addDataSet(data);
                        recyclerView.notifyLoadComplete();
                    }
                }, 2000);
            }
        });
    }

    class MyMultiItemAdapter extends MultiItemsAdapter<String> {

        public MyMultiItemAdapter(Context context) {
            super(context);
        }

        @Override
        public int setupItemViewType(int position) {
            if (position % 2 == 0) {
                return 1;
            }
            return 0;
        }

        @Override
        public int getLayoutId(int viewType) {
            if (viewType == 1) {
                return R.layout.card_view_item;
            }
            return R.layout.text_item;
        }

        @Override
        public void convertContent(CommonViewHolder commonViewHolder, String target) {
            int viewType = commonViewHolder.viewType;
            if (viewType == 1) {
                commonViewHolder.setText(R.id.card_item_tv, target);
            } else {
                commonViewHolder.setText(R.id.text_item_tv, target);
            }
        }
    }
}
