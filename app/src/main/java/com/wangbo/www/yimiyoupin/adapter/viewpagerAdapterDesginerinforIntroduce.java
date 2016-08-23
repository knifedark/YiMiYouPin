package com.wangbo.www.yimiyoupin.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wangbo.www.yimiyoupin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class ViewpagerAdapterDesginerinforIntroduce extends PagerAdapter {
    private List<String> list = null;//初始化数据源 网络地址
    private Context mContext;
    private List<ImageView> list_view = new ArrayList<>();//初始化控件imageview

    public ViewpagerAdapterDesginerinforIntroduce(Context mContext, List<String> list) {
        this.list = list;
        this.mContext = mContext;
        for (int i = 0; i < list.size(); i++) {//初始化控件
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            list_view.add(imageView);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Glide.with(mContext).load(list.get(position)).into(list_view.get(position));
        container.addView(list_view.get(position));
        return list_view.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
