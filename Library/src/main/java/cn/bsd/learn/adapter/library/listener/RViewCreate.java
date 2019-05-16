package cn.bsd.learn.adapter.library.listener;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import cn.bsd.learn.adapter.library.base.RViewAdapter;

/**
 * 创建RViewHelper所需要的数据，它的实现类很方便创建RViewHelper对象
 */
public interface RViewCreate<T> {
    /**
     * 创建SwiperRefresh下拉
     */
    SwipeRefreshLayout createSwipeRefresh();

    /**
     * SwiperRefresh下拉颜色
     */
    int[] colorRes();

    /**
     * 创建RecycleView
     */
    RecyclerView createRecyclerView();

    /**
     * 创建RecycleView.Adapter
     */
    RViewAdapter<T> createRecycleViewAdapter();

    /**
     * 创建RecycleView
     */
    RecyclerView.LayoutManager createLayoutManager();

    /**
     * 创建RecycleView分割线
     */
    RecyclerView.ItemDecoration createItemDecoration();

    /**
     * 开始页码
     */
    int startPageNumber();

    /**
     * 是否支持分页
     */
    boolean isSupportPaging();
}
