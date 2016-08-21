package com.wangbo.www.yimiyoupin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangbo.www.yimiyoupin.R;
import com.wangbo.www.yimiyoupin.adapter.HavethingAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My on 2016/8/21.
 */
public class Fragment_havething extends Fragment {
    private List<Fragment> list =new ArrayList<>();
    private TabLayout tabLayout_havething;
    private ViewPager viewPage_havething;
    private HavethingAdapter adapter;
    private String[] arrTitles = null;
    private FragmentManager manager;



    public void  setManager(  FragmentManager manager){
        this.manager=manager;
    }
    public FragmentManager getManager(){
        return manager;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrTitles=getResources().getStringArray(R.array.arrTitles);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_havething,container,false);
        tabLayout_havething= (TabLayout) view.findViewById(R.id.tabLayout_havething);
        viewPage_havething= (ViewPager) view.findViewById(R.id.viewPage_havething);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter=new HavethingAdapter(manager,list,arrTitles);
        viewPage_havething.setAdapter(adapter);

        tabLayout_havething.setupWithViewPager(viewPage_havething);
    }
}
