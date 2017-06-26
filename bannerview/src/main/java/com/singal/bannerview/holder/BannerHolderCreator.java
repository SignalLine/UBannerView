package com.singal.bannerview.holder;

/**
 * BannerHolder 构造类
 *
 * Created by li on 2017/6/14.
 */

public interface BannerHolderCreator <VH extends BannerViewHolder>{

    /**
     * 创建ViewHolder
     * @return
     */
    public VH createViewHolder();

}
