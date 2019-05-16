package cn.bsd.learn.adapter.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.netease.ioc.library.annotations.ContentView;

import java.util.ArrayList;
import java.util.List;

import cn.bsd.learn.adapter.library.base.RViewAdapter;
import cn.bsd.learn.adapter.library.listener.ItemListener;
import cn.bsd.learn.adapter.sample.bean.UserInfo;
import cn.bsd.learn.adapter.sample.multi.MultiAdapter;

@ContentView(R.layout.activity_recyclerview)
public class MultiActivity extends BaseRViewActivity {
    private List<UserInfo> datas = new ArrayList<>();
    private MultiAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        initDatas();
        listener();
    }

    private void listener() {
        adapter.setItemListener(new ItemListener<UserInfo>() {
            @Override
            public void onItemClick(View view, UserInfo entity, int position) {
                Toast.makeText(context,"条目点击 >>> "+position,Toast.LENGTH_SHORT).show();
                Log.e("NetEase >>> ","有效点击");
            }

            @Override
            public boolean onItemLongClick(View view, UserInfo entity, int position) {
                Toast.makeText(context,"条目长按 >>> "+position,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void initDatas() {
        new Thread(() -> {
            if (datas.isEmpty()) {
                for (int i = 0; i < 15; i++) {
                    for (int j = 1; j <= 15; j++) {
                        UserInfo user = new UserInfo();
                        if (j % 15 == 1) {
                            user.setType(1);
                            user.setAccount("Learn >>>>>>>>> 11111 >>>>>>>>>");
                        } else if (j % 15 == 2 || j % 15 == 3) {
                            user.setType(2);
                            user.setAccount("Learn >>>>>>> 22222 >>>>>>>");
                        } else if (j % 15 == 4 || j % 15 == 5 || j % 15 == 6) {
                            user.setType(3);
                            user.setAccount("Learn >>>>> 33333 >>>>>");
                        } else if (j % 15 == 7 || j % 15 == 8 || j % 13 == 9 || j % 15 == 10) {
                            user.setType(4);
                            user.setAccount("Learn >>> 44444 >>>");
                        } else {
                            user.setType(5);
                            user.setAccount("Learn > 55555 >");
                        }
                        datas.add(user);
                    }
                }
            }
            notifyAdapterDataSetChanged(datas);
        }).start();
    }

    @Override
    public void onRefresh() {
        initDatas();
    }

    @Override
    public RViewAdapter createRecycleViewAdapter() {
        adapter = new MultiAdapter(datas);
        return adapter;
    }
}
