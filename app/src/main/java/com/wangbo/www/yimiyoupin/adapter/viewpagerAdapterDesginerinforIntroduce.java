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

import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class ViewpagerAdapterDesginerinforIntroduce extends PagerAdapter {
    private List<String> list = null;
    private Context mContext ;
    private LayoutInflater inflater ;

    public ViewpagerAdapterDesginerinforIntroduce(Context mContext,List<String> list) {
        this.list = list;
        this.mContext = mContext;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(list.get(position)).into(imageView);
//        SimpleDraweeView simpleview = (SimpleDraweeView) view.findViewById(R.id.simpleDraweeView);
//        simpleview.setImageURI(Uri.parse(list.get(position)));
        container.addView(imageView);
        return  imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
