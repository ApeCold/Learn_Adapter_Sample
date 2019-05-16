package cn.bsd.learn.adapter.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.netease.ioc.library.annotations.ContentView;
import com.netease.ioc.library.annotations.OnItemClick;

import java.util.ArrayList;
import java.util.List;

import cn.bsd.learn.adapter.library.base.RViewAdapter;
import cn.bsd.learn.adapter.library.holder.RViewHolder;
import cn.bsd.learn.adapter.library.listener.RViewItem;
import cn.bsd.learn.adapter.sample.bean.UserInfo;

@ContentView(R.layout.activity_recyclerview)
public class MainActivity extends BaseRViewActivity {

    private List<UserInfo> datas = new ArrayList<>();
    private RViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDatas();
    }

    @OnItemClick(R.id.recyclerView)
    private void abc (View v, UserInfo info, int position) {

    }

    private void initDatas() {
        if(datas.isEmpty()){
            for (int i = 0; i <=100; i++) {
                datas.add(new UserInfo("Learn","123456"));
            }
        }
        notifyAdapterDataSetChanged(datas);
    }

    @Override
    public void onRefresh() {
        initDatas();
    }

    @Override
    public RViewAdapter<UserInfo> createRecycleViewAdapter() {
        adapter = new RViewAdapter<>(datas, new RViewItem<UserInfo>() {
            @Override
            public int getItemLayout() {
                return R.layout.item_list;
            }

            @Override
            public boolean openClick() {
                return true;
            }

            @Override
            public boolean isItemView(UserInfo entity, int position) {
                return true;
            }

            @Override
            public void convert(RViewHolder holder, UserInfo entity, int position) {
                TextView textView = holder.getView(R.id.item_tv);
                textView.setText(entity.toString());
            }
        });
        return adapter;
    }
}
