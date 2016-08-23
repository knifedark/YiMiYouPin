package com.wangbo.www.yimiyoupin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wangbo.www.yimiyoupin.MainActivity;
import com.wangbo.www.yimiyoupin.R;
import com.wangbo.www.yimiyoupin.helper.ParserObjectFromJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class RecyclerAdapterDesginerProducts extends BaseQuickAdapter{

    public RecyclerAdapterDesginerProducts(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Object o) {
        String a = (String) o;
        Glide.with(mContext).load(a).into((ImageView) baseViewHolder.getView(R.id.imageView_recyclerView_item));
    }
}
