package com.wangbo.www.yimiyoupin.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.wangbo.www.yimiyoupin.helper.ParserObjectFromJson;

import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class ViewPagerAdapterDesginerrefor extends PagerAdapter {
    private List<View> list = null;
    private List<String> list_string= null;

    public ViewPagerAdapterDesginerrefor(List<View> list, List<String> list_string) {
        this.list = list;
        this.list_string = list_string;
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
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_string.get(position);
    }
}
