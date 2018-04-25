package com.tifone.ui.xmlparser;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tifone.ui.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by tongkao.chen on 2018/4/24.
 */

public class XmlParserActivity extends AppCompatActivity {
    private ListView mListView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xml_parser_layout);
        mListView = findViewById(R.id.xml_items_list);

        List<XmlBean> list = null;
        try {
            list = new XmlUtils().parseXmlWithDom(getAssets().open("sample.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (XmlBean bean : list) {
            Log.e("tifone", "onCreate: " + bean);
        }
        try {
            List<XmlBean> beans = new XmlUtils().parseXmlWithPull(getAssets().open("sample.xml"));
            for (XmlBean bean : beans) {
                Log.e("tifone", "PUll way: " + bean);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mListView.setAdapter(new MyListAdapter(this, 0, list));
    }

    class MyListAdapter extends ArrayAdapter<XmlBean> {
        List<XmlBean> mDataSet;

        public MyListAdapter(@NonNull Context context, int resource, List<XmlBean> dataSet) {
            super(context, resource);
            mDataSet = dataSet;
        }

        @Nullable
        @Override
        public XmlBean getItem(int position) {
            return mDataSet.get(position);
        }

        @Override
        public int getCount() {
            return mDataSet.size();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.text_item, parent, false);
            } else {
                view = convertView;
            }
            TextView textView = view.findViewById(R.id.text_item_tv);
            textView.setText(getItem(position).toString());
            return view;
        }
    }

}
