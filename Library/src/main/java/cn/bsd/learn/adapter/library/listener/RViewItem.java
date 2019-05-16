package cn.bsd.learn.adapter.library.listener;

import cn.bsd.learn.adapter.library.holder.RViewHolder;

/**
 * javabean对象
 * 某一类item的对象接口
 */
public interface RViewItem<T> {
    //获取item布局
    int getItemLayout();

    //是否开启点击
    boolean openClick();

    //是否为当前的item布局
    boolean isItemView(T entity,int position);

    //item控件和数据绑定
    void convert(RViewHolder holder,T entity, int position);
}
