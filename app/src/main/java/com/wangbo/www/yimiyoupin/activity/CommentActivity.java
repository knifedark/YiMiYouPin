package com.wangbo.www.yimiyoupin.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wangbo.www.yimiyoupin.R;
import com.wangbo.www.yimiyoupin.adapter.commentAdapter;
import com.wangbo.www.yimiyoupin.androidbean.MazagineDetailBean;
import com.wangbo.www.yimiyoupin.helper.SwipeBackToolBarActivity;
import com.wangbo.www.yimiyoupin.interfacepak.MyInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * 剩余功能：1、完成登录，更改头像，
 * 2、点击回复，获取数据，添加数据至数据库
 * 3、抓取接口，获取最新评论
 * 4、下拉刷新
 */
public class CommentActivity extends SwipeBackToolBarActivity implements MyInterface {
    private List<MazagineDetailBean.DataBean.CommentsBean> list;
    private Toolbar toolbar_commentactivity;
    private XRecyclerView xrecyclerview_comment;
    private SimpleDraweeView simpleDraweeView_comment_username;
    private commentAdapter adapter = null;
    private Context mContent = this;
    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case 0:
                   xrecyclerview_comment.refreshComplete();
                   break;
               case 1:
                   xrecyclerview_comment.loadMoreComplete();
                   break;
           }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initView();
        initData();
    }

    @Override
    public void initView() {
        toolbar_commentactivity = (Toolbar) findViewById(R.id.toolbar_commentactivity);
        xrecyclerview_comment = (XRecyclerView) findViewById(R.id.xrecyclerview_comment);
        simpleDraweeView_comment_username = (SimpleDraweeView) findViewById(R.id.simpleDraweeView_comment_username);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();//获取跳转的Intent
        Bundle bundle = intent.getExtras();//获取bundle
        list = bundle.getParcelableArrayList("list");//获取数据源
        adapter = new commentAdapter(mContent, list);//设置适配器
        xrecyclerview_comment.setLayoutManager(new LinearLayoutManager(mContent));//设置视图
        xrecyclerview_comment.setPullRefreshEnabled(true);//设置可以滑动
        xrecyclerview_comment.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mhandler.sendEmptyMessageDelayed(0,2000);

            }

            @Override
            public void onLoadMore() {;
                mhandler.sendEmptyMessageDelayed(1,2000);

            }
        });

        xrecyclerview_comment.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrecyclerview_comment.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xrecyclerview_comment.setAdapter(adapter);
        setSupportActionBar(toolbar_commentactivity);//支持toolBar
        //设置toolbar图标为白色
        final Drawable upArrow = getResources().getDrawable(R.drawable
                .abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.colorWhite),
                PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置图标显示
        toolbar_commentactivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentActivity.this.finish();
            }
        });   //设置点击返回键，销毁当前页面


    }

    @Override
    public void oprData() {

    }
}
