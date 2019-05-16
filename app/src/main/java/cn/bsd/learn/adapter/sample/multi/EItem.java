package cn.bsd.learn.adapter.sample.multi;

import android.widget.TextView;

import cn.bsd.learn.adapter.library.holder.RViewHolder;
import cn.bsd.learn.adapter.library.listener.RViewItem;
import cn.bsd.learn.adapter.sample.R;
import cn.bsd.learn.adapter.sample.bean.UserInfo;

public class EItem implements RViewItem<UserInfo> {

    @Override
    public int getItemLayout() {
        return R.layout.item_e;
    }

    @Override
    public boolean openClick() {
        return false;
    }

    @Override
    public boolean isItemView(UserInfo entity, int position) {
        return entity.getType() == 5;
    }

    @Override
    public void convert(RViewHolder holder, UserInfo entity, int position) {
        TextView textView = holder.getView(R.id.mtv);
        textView.setText(entity.getAccount());
    }
}
