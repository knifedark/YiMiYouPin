package com.wangbo.www.yimiyoupin.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wangbo.www.yimiyoupin.R;
import com.wangbo.www.yimiyoupin.androidbean.PictorialBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by My on 2016/8/20.
 */
public class MyCardAdapter extends CardAdapter<PictorialBean.DataBean.ArticlesBean> {


    private List<PictorialBean.DataBean.ArticlesBean> mData;
    private Context context;
    private  List<Map<String,String>> list=new ArrayList<>();
    private Map<String,String> map;

    public MyCardAdapter(Context context, List<PictorialBean.DataBean.ArticlesBean> mData) {
        super(context, mData);
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    protected View getCardView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_layout, parent, false);
            mHolder = new ViewHolder(convertView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder)  convertView.getTag();
        }
        if(mData.size()>0&&mData.size()<=30){
            PictorialBean.DataBean.ArticlesBean item = getItem(position % mData.size());
            mHolder.textView_pictorial_subtitle.setText(item.getSub_title());
            mHolder.textView_pictorial_title.setText(item.getTitle());
            mHolder.image_pictorial_content.setImageURI(Uri.parse(item.getImage_url()));
        }else if (mData.size()>30){
        }
        return convertView;
    }
    class ViewHolder {
        private TextView textView_pictorial_title;
        private TextView textView_pictorial_subtitle;
        private SimpleDraweeView image_pictorial_content;
        public ViewHolder(View convertView) {
            textView_pictorial_subtitle = (TextView) convertView.findViewById(R.id.textView_pictorial_subtitle);
            textView_pictorial_title = (TextView) convertView.findViewById(R.id.textView_pictorial_title);
            image_pictorial_content = (SimpleDraweeView) convertView.findViewById(R.id.image_pictorial_content);
        }
    }

    // 将json字符串 转换成Javabean
    public PictorialBean parseJsonToHotBean(String jsonString) {
        Gson gson = new Gson();
        PictorialBean bean = gson.fromJson(jsonString, new TypeToken<PictorialBean>() {
        }.getType());
        return bean;
    }
    // 向list中添加数据 并刷新
    public void reloadGirdViewPage(List<PictorialBean.DataBean.ArticlesBean> _list, boolean isClear) {
        if (isClear) {
            mData.clear();
        }
        mData.addAll(_list);
        notifyDataSetChanged();
    }
}
