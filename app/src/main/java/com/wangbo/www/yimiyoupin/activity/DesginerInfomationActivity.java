package com.wangbo.www.yimiyoupin.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wangbo.www.yimiyoupin.R;
import com.wangbo.www.yimiyoupin.adapter.ViewpagerAdapterDesginerinforIntroduce;
import com.wangbo.www.yimiyoupin.androidbean.DesginerBean;
import com.wangbo.www.yimiyoupin.androidbean.MagnizeTitleBean;
import com.wangbo.www.yimiyoupin.androidbean.MazagineDetailBean;
import com.wangbo.www.yimiyoupin.helper.OkHttpClientHelper;
import com.wangbo.www.yimiyoupin.helper.ParserObjectFromJson;
import com.wangbo.www.yimiyoupin.helper.SwipeBackToolBarActivity;
import com.wangbo.www.yimiyoupin.interfacepak.MyInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DesginerInfomationActivity extends SwipeBackToolBarActivity implements MyInterface {

    @BindView(R.id.viewpager_image)
    ViewPager viewpagerImage;
    @BindView(R.id.imagebutton_back)
    ImageButton imagebuttonBack;
    @BindView(R.id.simpleDraweeView_DesginerInfo_infor)
    SimpleDraweeView simpleDraweeViewDesginerInfoInfor;
    @BindView(R.id.textview_name_infoDetail)
    TextView textviewNameInfoDetail;
    @BindView(R.id.textview_introduce_infoDetail)
    TextView textviewIntroduceInfoDetail;
    @BindView(R.id.textview_desginerinfo_concept)
    TextView textviewDesginerinfoConcept;
    @BindView(R.id.button_desginerinfo_collection)
    Button buttonDesginerinfoCollection;
    @BindView(R.id.textview_description_desginerinfo)
    TextView textviewDescriptionDesginerinfo;
    @BindView(R.id.tablayout_description_desginerinfo)
    TableLayout tablayoutDescriptionDesginerinfo;
    @BindView(R.id.tollbar_desginer_info)
    Toolbar tollbarDesginerInfo;
    @BindView(R.id.viewpager_description_desginerinfo)
    ViewPager viewpagerDescriptionDesginerinfo;
    private Context mContext = this;
    private Unbinder bind;
    private int desginerurlid;
    private MagnizeTitleBean magnizeTitleBean;
    private List<MazagineDetailBean.DataBean.ReferProductsBean> refer_products;
    private List<String> introduce_images;
    private DesginerBean bean;
    private String concept;
    private String name;
    private String label;
    private String avatar_url;
    private String description;
    private List<View> totalist = new ArrayList<>();
    private static final String STARTDESGINERURL = "http://design.zuimeia.com/api/v1/designer/";
    private static final String ENDDESGINERURL = "/?device_id=862845021555839&platform=android&lang=zh&appVersion=1.0.6&appVersionCode=10006&systemVersion=21&countryCode=CN&user_id=39420&token=4em-71642f70e4e359f6ba5a&package_name=com.zuiapps.zuiworld";
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ViewpagerAdapterDesginerinforIntroduce adapter = new ViewpagerAdapterDesginerinforIntroduce(mContext,introduce_images);
                    viewpagerImage.setAdapter(adapter);
                    textviewDescriptionDesginerinfo.setText(description);
                    textviewDesginerinfoConcept.setText(concept);
                    textviewIntroduceInfoDetail.setText(label);
                    textviewNameInfoDetail.setText(name);
                    simpleDraweeViewDesginerInfoInfor.setImageURI(Uri.parse(avatar_url));
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desginer_infomation);
        bind = ButterKnife.bind(this);
        initData();

    }

    @Override
    public void initView() {


    }

    @Override
    public void initData() {

        Intent intent = getIntent();//获取跳转传过来的值
        final Bundle bundle = intent.getExtras();
        desginerurlid = bundle.getInt("desginerurlid");
        magnizeTitleBean = bundle.getParcelable("MagnizeTitleBean");
        refer_products = bundle.getParcelableArrayList("ReferProductsBean");
        final String url = STARTDESGINERURL + desginerurlid + ENDDESGINERURL;
        new Thread(new Runnable() {//子线程获取数据
            @Override
            public void run() {
                try {
//                    private String concept;
//                    private  String name;
//                    private String label;
//                    private String avatar_url;
//                    private String description;
                    String jsonDesginerBean = OkHttpClientHelper.getStringFromURL(mContext, url, "DesginerBean");
                    Object object = ParserObjectFromJson.jsonStringToModel(jsonDesginerBean, ParserObjectFromJson.DESGINERBEAN);
                    bean = (DesginerBean) object;
                    introduce_images = bean.getData().getIntroduce_images();//获取介绍页面的viewpager地址
                    concept = bean.getData().getConcept();
                    name = bean.getData().getName();
                    label = bean.getData().getLabel();
                    avatar_url = bean.getData().getAvatar_url();
                    description = bean.getData().getDescription();
                    mhandler.sendEmptyMessage(0);


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }

    @Override
    public void oprData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();//解除注册
    }
}
