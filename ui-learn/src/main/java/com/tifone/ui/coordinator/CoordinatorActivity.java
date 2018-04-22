package com.tifone.ui.coordinator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.tifone.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tongkao.chen on 2018/3/27.
 */

public class CoordinatorActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private List<String> mList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator_layout_activity);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        mList = makeDatas();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapter(mList));
        mRecyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_INDICATOR_BOTTOM) {
                    Intent intent = new Intent();
                    intent.setClass(CoordinatorActivity.this, MdStyleActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private List<String> makeDatas() {
        List<String> list = new ArrayList<String>();
        list.clear();
        for (int i = 0; i < 50; i++) {
            list.add("Item " + i);
        }
        return list;
    }


    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private List<String> mDataSet;
        public MyAdapter(List<String> dataSet) {
            mDataSet = dataSet;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_item, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.textView.setText(mDataSet.get(position));

        }

        @Override
        public int getItemCount() {
            return mDataSet.size();
        }

        static class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textView = null;
            public MyViewHolder(View view) {
                super(view);
                textView = (TextView) view.findViewById(R.id.text_item_tv);
            }
        }
    }
}
