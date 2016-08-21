package com.wangbo.www.yimiyoupin.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by My on 2016/8/21.
 */
public class HavethingAdapter extends FragmentPagerAdapter {
    private List<Fragment> list = null;
    private String[] arrTitles = null;
    public HavethingAdapter(FragmentManager fm, List<Fragment> list , String[] arrTitles) {
        super(fm);
        this.list=list;
        this.arrTitles=arrTitles;
    }


    // 根据position获取 item
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
    // 根据position 获取长度
    @Override
    public int getCount() {
        return list.size();
    }
    // 设置tablayout 的标签
    @Override
    public CharSequence getPageTitle(int position) {
        return arrTitles[position];
    }
}
