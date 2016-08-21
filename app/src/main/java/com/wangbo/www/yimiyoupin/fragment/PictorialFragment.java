package com.wangbo.www.yimiyoupin.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangbo.www.yimiyoupin.R;
import com.wangbo.www.yimiyoupin.adapter.MyCardAdapter;
import com.wangbo.www.yimiyoupin.androidbean.PictorialBean;
import com.wangbo.www.yimiyoupin.cusview.CardView;
import com.wangbo.www.yimiyoupin.helper.OkHttpClientHelper;
import com.wangbo.www.yimiyoupin.helper.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My on 2016/8/20.
 */
public class PictorialFragment extends Fragment implements CardView.OnCardClickListener {
    private CardView cardView;
    private Context context;
    private MyCardAdapter adapter;
    private List<PictorialBean.DataBean.ArticlesBean> list = new ArrayList<>();

    public PictorialFragment() {
    }
    public PictorialFragment (List<PictorialBean.DataBean.ArticlesBean> list, MyCardAdapter adapter){
        super();
     this.list=list;
     this.adapter=adapter;
 }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pictorial, container, false);
        cardView = (CardView) view.findViewById(R.id.cardView1_fragment_pictorial);
        cardView.setOnCardClickListener(this);
        cardView.setItemSpace(Utils.convertDpToPixelInt(context, 40));

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cardView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 销毁
        OkHttpClientHelper.cancelCall("dema");
    }

    @Override
    public void onCardClick(View view, int position) {

    }
}
