package cn.bsd.learn.adapter.library.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.bsd.learn.adapter.library.holder.RViewHolder;
import cn.bsd.learn.adapter.library.listener.ItemListener;
import cn.bsd.learn.adapter.library.listener.RViewItem;
import cn.bsd.learn.adapter.library.manager.RViewItemManager;

/**
 * RecyclerView多item样式适配器
 */
public class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder>  {
    private RViewItemManager<T> itemStyle;//item类型管理
    private ItemListener<T> itemListener;//item点击监听
    private List<T> datas;//数据源

    //数据操作
    public void addDatas(List<T> datas){
        if(datas==null) return;
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    //数据操作
    public void updateDatas(List<T> datas){
        if(datas==null) return;
        this.datas = datas;
        notifyDataSetChanged();
    }

    //单样式
    public RViewAdapter(List<T> datas) {
        if(datas==null) this.datas = new ArrayList<>();
        this.datas = datas;
        itemStyle = new RViewItemManager<>();
    }

    //多样式
    public RViewAdapter(List<T> datas, RViewItem<T> item) {
        if(datas==null) this.datas = new ArrayList<>();
        this.datas = datas;
        itemStyle = new RViewItemManager<>();
        //将Item类型加入到管理器
        addItemStyle(item);
    }


    //根据布局类型创建不同Item的ViewHolder
    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RViewItem item = itemStyle.getRViewItem(viewType);
        int layoutId = item.getItemLayout();
        RViewHolder holder = RViewHolder.createViewHolder(parent.getContext(),parent,layoutId);
        //判断点击开启
        if(item.openClick()) setListener(holder);
        return holder;
    }


    //将数据绑定到Item的ViewHolder控件
    @Override
    public void onBindViewHolder(@NonNull RViewHolder holder, int position) {
        convert(holder,datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    //根据position获取当前Item的布局类型
    @Override
    public int getItemViewType(int position) {
        if(hasMultiStyle()) return itemStyle.getItemViewType(datas.get(position),position);
        return super.getItemViewType(position);
    }

    //是否有多样式RViewItem
    private boolean hasMultiStyle(){
        return itemStyle.getItemViewStylesCount()>0;
    }

    private void setListener(RViewHolder holder) {
        holder.getmConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemListener!=null){
                    int position = holder.getAdapterPosition();
                    itemListener.onItemClick(v,datas.get(position),position);
                }
            }
        });

        holder.getmConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(itemListener!=null){
                    int position = holder.getAdapterPosition();
                    return itemListener.onItemLongClick(v,datas.get(position),position);
                }
                return false;
            }
        });
    }

    //增加一种新的item样式
    public void addItemStyle(RViewItem<T> item) {
        itemStyle.addStyles(item);
    }

    //监听赋值
    public void setItemListener(ItemListener<T> itemListener) {
        this.itemListener = itemListener;
    }

    private void convert(RViewHolder holder, T entity) {
        itemStyle.convert(holder,entity,holder.getAdapterPosition());
    }
}
