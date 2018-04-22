package com.tifone.ui.calculator;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.tifone.ui.calculator.GridViewAdapter.OnGridItemClickListener;

import com.tifone.ui.R;

import org.w3c.dom.Text;


/**
 * Created by tongkao.chen on 2018/4/17.
 */

public class CalculatorActivity extends AppCompatActivity implements OnGridItemClickListener {
    private static final String[] DATA_SET = {
            "7", "8", "9", "÷", "X",
            "4", "5", "6", "x", "C",
            "1", "2", "3", "-", "",
            ".", "0", "", "+", "=",
    };

    private String numberValue = "-1";
    private StringBuilder builder;
    private String operator = "+,-,x,X,C,÷,=";
    private String mOperator;
    private TextView mTextView;
    private StringBuilder mOperationProcess;
    private String mResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);
        Toolbar toolbar = findViewById(R.id.widget_tool_bar);
        if (getSupportActionBar() == null) {
            setSupportActionBar(toolbar);
        }
        mTextView = findViewById(R.id.show_result_tv);
        GridView gridView = findViewById(R.id.num_grid_view);
        GridViewAdapter adapter = new  GridViewAdapter(this, DATA_SET);
        adapter.setItemClickListener(this);
        gridView.setAdapter(adapter);
        builder = new StringBuilder("");
        mOperationProcess = new StringBuilder("");
        mResult = "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calculator_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.operation:
                Toast.makeText(this, "operation", Toast.LENGTH_SHORT).show();
                break;
            case R.id.country:
                Toast.makeText(this, "country", Toast.LENGTH_SHORT).show();
                break;
            case R.id.other:
                Toast.makeText(this, "other", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void onItemClicked(View view, int position) {
        TextView textView = (TextView) view;
        String content = textView.getText().toString();
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
        mOperationProcess.append(content);
        String result = builder.toString();
        resolveValue(content);
        showResult(result);

    }
    private String resolveValue(final String src) {

        Log.d("tifone", "src = " + src);
        boolean isOperator = operator.indexOf(src) != -1;
        String result = src;
        if (isOperator) {

        }
        if (TextUtils.isEmpty(src) || TextUtils.isEmpty(operator) || TextUtils.isEmpty(mOperator)) {
            return result;
        }
        String target = "";
        String srcValue = "";
        int operatorIndex = src.lastIndexOf(operator);
        int oldIndex = src.indexOf(mOperator);
        if (oldIndex != operatorIndex && oldIndex != -1) {
            srcValue = src.substring(0, oldIndex);
            target = src.substring(oldIndex + 1, operatorIndex);
        } else {
            srcValue = src.substring(0, operatorIndex - 1);
            return result;
        }
        mOperator = "";
        Log.d("tifone", "target = " + target);
        Log.d("tifone", "srcValue = " + srcValue);
        switch (operator) {
            case "+":
                result = addOperator(srcValue, target);
                if (!TextUtils.isEmpty(result)) {
                    builder.setLength(0);
                    builder.append(result+"+");
                    Log.d("tifone", "builder = " + builder.toString());
                }
                Log.d("tifone", "result = " + result);
                Log.d("tifone", "builder = " + builder.toString());
                break;
            case "-":
                break;
            case "x":
                break;
            case "÷":
                break;
            case "X":
                break;
            case "C":
                break;
            case "=":
                break;
        }
        return result;
    }
    private void showResult(String result) {
        mTextView.setText(mOperationProcess + "\n" + result);
    }
    private String addOperator(String valueSrc, String valueTarget) {
        if (TextUtils.isEmpty(valueSrc)) {
            return null;
        }
        if (TextUtils.isEmpty(valueTarget)) {
            return valueSrc + "+";
        }
        if (valueSrc.indexOf(".") != -1) {
            double src = Double.valueOf(valueSrc);
            if (valueTarget.indexOf(".") != -1) {
                return String.valueOf(src + Double.valueOf(valueTarget));
            }
            return String.valueOf(src + Integer.valueOf(valueTarget));
        }
        long result =  Long.valueOf(valueSrc) + Long.valueOf(valueTarget);
        return String.valueOf(result);
    }
}

class GridViewAdapter extends ArrayAdapter<String> {
    String[] dataSet;
    Context context;
    private OnGridItemClickListener mListener;

    public interface OnGridItemClickListener {
        void onItemClicked(View view, int position);
    }

    public GridViewAdapter(@NonNull Context context, String[] dataSet) {
        super(context, 0, dataSet);
        this.dataSet = dataSet;
        this.context = context;
    }

    public void setItemClickListener(OnGridItemClickListener listener) {
        mListener = listener;
    }

    public void removeItemClickListener() {
        mListener = null;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return dataSet[position];
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.text_item, null);
        }
        TextView textView = view.findViewById(R.id.text_item_tv);
        textView.setHeight(parent.getHeight() / (dataSet.length / 5));
        textView.setText(dataSet[position]);
        if (!TextUtils.isEmpty(dataSet[position])) {
            setupClickListener(textView, position);
        }
        return view;
    }

    private void setupClickListener(TextView textView, final int position) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClicked(view, position);
                }
            }
        });
    }
}
