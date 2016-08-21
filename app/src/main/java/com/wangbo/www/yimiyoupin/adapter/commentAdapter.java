package com.wangbo.www.yimiyoupin.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.wangbo.www.yimiyoupin.R;
import com.wangbo.www.yimiyoupin.androidbean.MazagineDetailBean;
import com.wangbo.www.yimiyoupin.helper.RecyclerViewAdapterHelper;
import com.wangbo.www.yimiyoupin.helper.TimeHelper;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/8/21.
 */
public class commentAdapter extends RecyclerViewAdapterHelper<MazagineDetailBean.DataBean.CommentsBean> {


    public commentAdapter(Context mContext, List<MazagineDetailBean.DataBean.CommentsBean> list) {
        super(mContext, list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_commenactiviy_recycler, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder mViewHolder = (MyViewHolder) holder;
        MazagineDetailBean.DataBean.CommentsBean commentsBean = list.get(position);
        String content = commentsBean.getContent();//获取内容
        long created_at = commentsBean.getCreated_at();//获取时间戳
        MazagineDetailBean.DataBean.CommentsBean.AuthorBean author = commentsBean.getAuthor();
        String username = author.getUsername();//获取姓名
        String avatar_url = author.getAvatar_url();//获取图片地址
        //实现渐进式加载的问题
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(avatar_url))
                .setProgressiveRenderingEnabled(true).build();
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder().setImageRequest(request)
                .setOldController( mViewHolder.simpleDraweeViewItemCommentactivityRecycler.getController()).build();
        mViewHolder.simpleDraweeViewItemCommentactivityRecycler.setController(controller);
        mViewHolder.simpleDraweeViewItemCommentactivityRecycler.setImageURI(Uri.parse(avatar_url));
        mViewHolder.textviewCommentContent.setText(content);
        mViewHolder.textviewCommentusername.setText(username);
        String standardDate = TimeHelper.getStandardDate(created_at + "");
        mViewHolder.textviewCommentupdatatime.setText(standardDate);


    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView simpleDraweeViewItemCommentactivityRecycler;
        private TextView textviewCommentusername;
        private TextView textviewCommentContent;
        private TextView textviewCommentupdatatime;

        public MyViewHolder(View itemView) {
            super(itemView);
            simpleDraweeViewItemCommentactivityRecycler = (SimpleDraweeView) itemView.findViewById(R.id.simpleDraweeView_item_commentactivity_recycler);
            textviewCommentusername = (TextView) itemView.findViewById(R.id.textview_commentusername);
            textviewCommentContent = (TextView) itemView.findViewById(R.id.textview_commentContent);
            textviewCommentupdatatime = (TextView) itemView.findViewById(R.id.textview_commentupdatatime);


        }
    }


}
