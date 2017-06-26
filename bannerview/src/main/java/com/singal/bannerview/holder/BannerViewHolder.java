package com.singal.bannerview.holder;

import android.content.Context;
import android.view.View;

/**
 * viewHolder
 *
 * Created by li on 2017/6/14.
 */

public interface BannerViewHolder<T> {

    /**
     *  创建View
     * @param context
     * @return
     */
    View createView(Context context);

    /**
     * 绑定数据
     * @param context
     * @param position
     * @param data
     */
    void onBind(Context context, int position, T data);

}
