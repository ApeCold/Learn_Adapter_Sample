package cn.bsd.learn.adapter.library.manager;

import android.support.v4.util.SparseArrayCompat;

import cn.bsd.learn.adapter.library.holder.RViewHolder;
import cn.bsd.learn.adapter.library.listener.RViewItem;

/**
 * 多类型、多样式item管理器
 */
public class RViewItemManager<T>{

    //key:viewType value:RViewItem
    private SparseArrayCompat<RViewItem<T>> styles = new SparseArrayCompat<>();

    //增加一种新的item样式
    public void addStyles(RViewItem<T> item) {
        if(item!=null){
            styles.put(styles.size(),item);
        }
    }

    //有没有多样式呢？
    public int getItemViewStylesCount(){
        return styles.size();
    }

    //根据显示的viewType返回RViewItem对象（集合的value）
    public RViewItem getRViewItem(int viewType) {
        return styles.get(viewType);
    }

    //根据数据源和位置返回某个item类型的viewType（集合的key）
    public int getItemViewType(T entity,int position){
        for(int i=styles.size()-1;i>=0;i--){//样式倒序循环，防止增删集合抛出异常
            //比如第1个位置（索引0），拿到的是第一类的条目样式
            RViewItem<T> item = styles.valueAt(i);
            if(item.isItemView(entity,position)){//是否为当前的样式显示，有开发者外面实现
                //获取集合key，也就是viewType
                return styles.keyAt(i);
            }
        }
        throw new IllegalArgumentException("该位置没有匹配的条目样式");
    }

    //数据与数据源绑定显示
    public void convert(RViewHolder holder, T entity, int position) {
        for (int i = 0; i < styles.size(); i++) {
            RViewItem<T> item = styles.valueAt(i);
            if(item.isItemView(entity,position)){//是否为当前的样式显示，有开发者外面实现
                item.convert(holder,entity,position);
                return;
            }
        }
        throw new IllegalArgumentException("该位置没有匹配的条目样式");
    }
}
