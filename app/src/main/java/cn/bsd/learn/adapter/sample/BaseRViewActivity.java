package cn.bsd.learn.adapter.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.netease.ioc.library.InjectManager;

import java.util.List;

import cn.bsd.learn.adapter.library.RViewHelper;
import cn.bsd.learn.adapter.library.SwipeRefreshHelper;
import cn.bsd.learn.adapter.library.listener.RViewCreate;

public abstract class BaseRViewActivity extends AppCompatActivity implements RViewCreate, SwipeRefreshHelper.SwipeRefreshListener {

    private RViewHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectManager.inject(this);
        helper = new RViewHelper.Builder(this,this).build();
    }

    @Override
    public SwipeRefreshLayout createSwipeRefresh() {
        return findViewById(R.id.swipeRefreshLayout);
    }

    @Override
    public int[] colorRes() {
        return new int[0];
    }

    @Override
    public RecyclerView createRecyclerView() {
        return findViewById(R.id.recyclerView);
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public RecyclerView.ItemDecoration createItemDecoration() {
        return new DividerItemDecoration(this,LinearLayoutManager.VERTICAL);
    }

    @Override
    public int startPageNumber() {
        return 1;
    }

    @Override
    public boolean isSupportPaging() {
        return false;
    }

    public void notifyAdapterDataSetChanged(List datas){
        helper.notifyAdapterDataSetChanged(datas);
    }
}
