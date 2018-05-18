package com.tifone.ui.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tifone.ui.R;
import com.tifone.ui.recyclerview.adapter.CardViewAdapter;
import com.tifone.ui.recyclerview.decoration.ListItemDividerDecoration;
import com.tifone.ui.recyclerview.decoration.StaggeredDividerDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tongkao.chen on 2018/3/22.
 */

public class MyRecyclerViewActivity extends AppCompatActivity implements CardViewAdapter.OnItemClickListener {

    private PullRefreshRecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView.LayoutManager mLayoutManger;
    private ListItemDividerDecoration mItemDivider;
    private GridLayoutManager mGridLayoutMager;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private StaggeredDividerDecoration mStaggeredDivider;
    private static final String[] mDataSet = new String[]{
            "用于项目定位的布局管理器",
            "用于通用项目操作（例如删除或添加项目）的默认动画",
            "LinearLayoutManager 以垂直或水平滚动列表方式显示项目。 ",
            "GridLayoutManager 在网格中显示项目。",
            "StaggeredGridLayoutManager 在分散对齐网格中显示项目。",
            "如果要创建一个自定义布局管理器，请扩展 RecyclerView.LayoutManager 类别。",
            "mRecyclerView.setHasFixedSize(true);",
            "mLayoutManager = new LinearLayoutManager(this);",
            "mRecyclerView.setLayoutManager(mLayoutManager);",
            "mAdapter = new MyAdapter(myDataset);",
            "mAdapter = new MyAdapter(myDataset);",
            "用于项目定位的布局管理器",
            "用于通用项目操作（例如删除或添加项目）的默认动画",
            "LinearLayoutManager 以垂直或水平滚动列表方式显示项目。 ",
            "GridLayoutManager 在网格中显示项目。",
            "StaggeredGridLayoutManager 在分散对齐网格中显示项目。",
            "如果要创建一个自定义布局管理器，请扩展 RecyclerView.LayoutManager 类别。",
            "mRecyclerView.setHasFixedSize(true);",
            "mLayoutManager = new LinearLayoutManager(this);",
            "mRecyclerView.setLayoutManager(mLayoutManager);",
            "mAdapter = new MyAdapter(myDataset);",
            "mAdapter = new MyAdapter(myDataset);",
            "用于项目定位的布局管理器",
            "用于通用项目操作（例如删除或添加项目）的默认动画",
            "LinearLayoutManager 以垂直或水平滚动列表方式显示项目。 ",
            "GridLayoutManager 在网格中显示项目。",
            "StaggeredGridLayoutManager 在分散对齐网格中显示项目。",
            "如果要创建一个自定义布局管理器，请扩展 RecyclerView.LayoutManager 类别。",
            "mRecyclerView.setHasFixedSize(true);",
            "mLayoutManager = new LinearLayoutManager(this);",
            "mRecyclerView.setLayoutManager(mLayoutManager);",
            "mAdapter = new MyAdapter(myDataset);",
            "mAdapter = new MyAdapter(myDataset);",
            "用于项目定位的布局管理器",
            "用于通用项目操作（例如删除或添加项目）的默认动画",
            "LinearLayoutManager 以垂直或水平滚动列表方式显示项目。 ",
            "GridLayoutManager 在网格中显示项目。",
            "StaggeredGridLayoutManager 在分散对齐网格中显示项目。",
            "如果要创建一个自定义布局管理器，请扩展 RecyclerView.LayoutManager 类别。",
            "mRecyclerView.setHasFixedSize(true);",
            "mLayoutManager = new LinearLayoutManager(this);",
            "mRecyclerView.setLayoutManager(mLayoutManager);",
            "mAdapter = new MyAdapter(myDataset);",
            "mAdapter = new MyAdapter(myDataset);",
            "用于项目定位的布局管理器",
            "用于通用项目操作（例如删除或添加项目）的默认动画",
            "LinearLayoutManager 以垂直或水平滚动列表方式显示项目。 ",
            "GridLayoutManager 在网格中显示项目。",
            "StaggeredGridLayoutManager 在分散对齐网格中显示项目。",
            "如果要创建一个自定义布局管理器，请扩展 RecyclerView.LayoutManager 类别。",
            "mRecyclerView.setHasFixedSize(true);",
            "mLayoutManager = new LinearLayoutManager(this);",
            "mRecyclerView.setLayoutManager(mLayoutManager);",
            "mAdapter = new MyAdapter(myDataset);",
            "mAdapter = new MyAdapter(myDataset);",
            "用于项目定位的布局管理器",
            "用于通用项目操作（例如删除或添加项目）的默认动画",
            "LinearLayoutManager 以垂直或水平滚动列表方式显示项目。 ",
            "GridLayoutManager 在网格中显示项目。",
            "StaggeredGridLayoutManager 在分散对齐网格中显示项目。",
            "如果要创建一个自定义布局管理器，请扩展 RecyclerView.LayoutManager 类别。",
            "mRecyclerView.setHasFixedSize(true);",
            "mLayoutManager = new LinearLayoutManager(this);",
            "mRecyclerView.setLayoutManager(mLayoutManager);",
            "mAdapter = new MyAdapter(myDataset);",
            "mAdapter = new MyAdapter(myDataset);",
            "用于项目定位的布局管理器",
            "用于通用项目操作（例如删除或添加项目）的默认动画",
            "LinearLayoutManager 以垂直或水平滚动列表方式显示项目。 ",
            "GridLayoutManager 在网格中显示项目。",
            "StaggeredGridLayoutManager 在分散对齐网格中显示项目。",
            "如果要创建一个自定义布局管理器，请扩展 RecyclerView.LayoutManager 类别。",
            "mRecyclerView.setHasFixedSize(true);",
            "mLayoutManager = new LinearLayoutManager(this);",
            "mRecyclerView.setLayoutManager(mLayoutManager);",
            "mAdapter = new MyAdapter(myDataset);",
            "mAdapter = new MyAdapter(myDataset);"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activity);
        mRecyclerView =  findViewById(R.id.my_recycler_view);
        // 如果适配器的内容不会影响RecyclerView的大小时，使用这个配置可以达到优化的效果
        //mRecyclerView.setHasFixedSize(true);

        // 使用线性布局管理器
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mGridLayoutMager = new GridLayoutManager(this, 2);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);


        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        //mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        // 指定适配器
        //TextLineAdapter adapter = new TextLineAdapter(mDataSet);
        List<String> mDataSetList = new ArrayList<>(Arrays.asList(mDataSet));
        CardViewAdapter adapter = new CardViewAdapter(this, mDataSetList);
        adapter.setItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
        View headerView = LayoutInflater.from(this).inflate(R.layout.recycler_view_refresh_header, null);
        View footerView = LayoutInflater.from(this).inflate(R.layout.recycler_view_refresh_footer, null);


        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mItemDivider = new ListItemDividerDecoration(this, ListItemDividerDecoration.VERTICAL);
        mStaggeredDivider = new StaggeredDividerDecoration(this, StaggeredDividerDecoration.HORIZONTAL);
        //mRecyclerView.addItemDecoration(mItemDivider);
        //mRecyclerView.setAdapter(new QuickCardViewAdapter(mDataSetList));
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "click " + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(this, "long click " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recycler_view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.linear_menu:
                if (mLinearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                    mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    mItemDivider.setOrientation(ListItemDividerDecoration.HORIZONTAL);
                } else {
                    mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mItemDivider.setOrientation(ListItemDividerDecoration.VERTICAL);
                }
                mLayoutManger = mLinearLayoutManager;
                mRecyclerView.addItemDecoration(mItemDivider);
                break;
            case R.id.grid_menu:
                if (mGridLayoutMager.getOrientation() == GridLayoutManager.VERTICAL) {
                    mGridLayoutMager.setOrientation(GridLayoutManager.HORIZONTAL);
                    mItemDivider.setOrientation(ListItemDividerDecoration.HORIZONTAL);
                } else {
                    mGridLayoutMager.setOrientation(GridLayoutManager.VERTICAL);
                    mItemDivider.setOrientation(ListItemDividerDecoration.VERTICAL);
                }
                mLayoutManger = mGridLayoutMager;
                mRecyclerView.addItemDecoration(mItemDivider);
                break;
            case R.id.staggered_grid_menu:

                if (mStaggeredGridLayoutManager.getOrientation() == StaggeredGridLayoutManager.VERTICAL) {
                    mStaggeredGridLayoutManager.setOrientation(StaggeredGridLayoutManager.HORIZONTAL);
                    mStaggeredDivider.setOrientation(StaggeredDividerDecoration.HORIZONTAL);
                } else {
                    mStaggeredGridLayoutManager.setOrientation(StaggeredGridLayoutManager.VERTICAL);
                    mStaggeredDivider.setOrientation(StaggeredDividerDecoration.VERTICAL);
                }
                mLayoutManger = mStaggeredGridLayoutManager;
                mRecyclerView.addItemDecoration(mStaggeredDivider);
                break;
            default:
                break;
        }
        mRecyclerView.setLayoutManager(mLayoutManger);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        return true;
    }
}
