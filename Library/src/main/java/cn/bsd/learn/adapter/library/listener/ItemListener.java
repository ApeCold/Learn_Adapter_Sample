package cn.bsd.learn.adapter.library.listener;

import android.view.View;

/**
 * item点击、长按监听接口
 */
public interface ItemListener<T> {
    void onItemClick(View view,T entity,int position);

    boolean onItemLongClick(View view,T entity,int position);
}
