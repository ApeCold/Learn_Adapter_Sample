package cn.bsd.learn.adapter.library.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;//View控件集合
    private View mConvertView;//当前的View对象

    private RViewHolder(@NonNull View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }


    //创建RViewHolder对象
    public static RViewHolder createViewHolder(Context context, ViewGroup parent,int layoutId){
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new RViewHolder(itemView);
    }

    //通过ViewId获取控件
    public<T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if(view==null){
            view= mConvertView.findViewById(viewId);
            //key: R.id.XXX value:TextView
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    public View getmConvertView(){
        return mConvertView;
    }
}
