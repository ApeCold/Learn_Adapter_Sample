package cn.bsd.learn.adapter.sample.multi;

import java.util.List;

import cn.bsd.learn.adapter.library.base.RViewAdapter;
import cn.bsd.learn.adapter.sample.bean.UserInfo;

public class MultiAdapter extends RViewAdapter<UserInfo> {

    public MultiAdapter(List<UserInfo> datas) {
        super(datas);
        addItemStyle(new AItem());
        addItemStyle(new BItem());
        addItemStyle(new CItem());
        addItemStyle(new DItem());
        addItemStyle(new EItem());
    }
}
