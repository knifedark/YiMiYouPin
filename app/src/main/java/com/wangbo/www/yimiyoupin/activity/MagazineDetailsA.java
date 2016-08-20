package com.wangbo.www.yimiyoupin.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wangbo.www.yimiyoupin.R;
import com.wangbo.www.yimiyoupin.androidbean.MazagineDetailBean;
import com.wangbo.www.yimiyoupin.helper.OkHttpClientHelper;
import com.wangbo.www.yimiyoupin.helper.ParserObjectFromJson;
import com.wangbo.www.yimiyoupin.helper.SwipeBackToolBarActivity;
import com.wangbo.www.yimiyoupin.interfacepak.MyInterface;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MagazineDetailsA extends SwipeBackToolBarActivity implements MyInterface, View.OnClickListener {
    private TextView textviewTitleMagazineDetail;
    private TextView textviewSubtitleMagazineDetail;
    private SimpleDraweeView simpleDraweeViewTitle;
    private ImageButton imagebuttonSharedSina;
    private ImageButton imagebuttonSharedQQ;
    private ImageButton imagebuttonSharedWeixin;
    private SimpleDraweeView simpleDraweeViewDesginerInfoInfor;
    private TextView textviewNameInfoDetail;
    private TextView textviewIntroduceInfoDetail;
    private ImageButton imagebuttonBack;
    private SimpleDraweeView simpleDraweeViewDesginerInfo;
    private TextView textViewDessignNameMagnizeDetail;
    private TextView textViewCityMagnizeDetail;
    private WebView webviewMagazineinfo;
    private String designerslabel;
    private Context mContext = this;
    private int id;//声明拼凑地址 需要的id
    private String url;
    private String webView_url;//webview地址
    private String stringHtml;
    private String title;
    private String subtitle;
    private String titleimageurl;
    private String designersName;
    private String designersCity;
    private String designersIamgeurl;
    private ScrollView scrollView_imageInfo;
    private RelativeLayout layout_desginerinfotop;
    private boolean flag = false;


    String CSS_STYPE = "<head><style>* {font-size:16px;line-height:20px;} p {color:#333;} a {color:#3E62A6;} img {max-width:100%;}pre {font-size:9pt;line-height:12pt;font-family:Courier New,Arial;border:1px solid #ddd;border-left:5px solid #6CE26C;background:#f6f6f6;padding:5px;}</style></head>";
    private static final String STARTURL = "http://design.zuimeia.com/api/v1/article/";//拼凑地址前半部分
    //拼凑地址后半部分
    private static final String ENDTURL = "/?device_id=867905022979506&platform=android&lang=zh&appVersion=1.1.7&appVersionCode=10107&systemVersion=22&countryCode=CN&user_id=39146&token=4el-0ada01cde1909345cb58&package_name=com.zuiapps.zuiworld";
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    textviewTitleMagazineDetail.setText(title);
                    textviewSubtitleMagazineDetail.setText(subtitle);
                    simpleDraweeViewTitle.setImageURI(Uri.parse(titleimageurl));
                    textViewDessignNameMagnizeDetail.setText(designersName);
                    textviewNameInfoDetail.setText(designersName);
                    textViewCityMagnizeDetail.setText(designersCity);
                    simpleDraweeViewDesginerInfo.setImageURI(Uri.parse(designersIamgeurl));
                    simpleDraweeViewDesginerInfoInfor.setImageURI(Uri.parse(designersIamgeurl));
                    textviewIntroduceInfoDetail.setText(designerslabel);
                    webviewMagazineinfo.loadDataWithBaseURL(null, CSS_STYPE + stringHtml, "text/html", "utf_8", null);

                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);//初始化Frecso
        setContentView(R.layout.activity_magazine_details);//填充数据
        initView();
        initData();//调用初始化数据
    }

    @Override
    public void initView() {
        initwebView();
        textviewTitleMagazineDetail = (TextView) findViewById(R.id.textview_title_Magazine_detail);
        textviewSubtitleMagazineDetail = (TextView) findViewById(R.id.textview_subtitle_Magazine_detail);
        simpleDraweeViewTitle = (SimpleDraweeView) findViewById(R.id.simpleDraweeView_Title);
        imagebuttonSharedSina = (ImageButton) findViewById(R.id.imagebutton_shared_sina);
        imagebuttonSharedQQ = (ImageButton) findViewById(R.id.imagebutton_shared_QQ);
        imagebuttonSharedWeixin = (ImageButton) findViewById(R.id.imagebutton_shared_weixin);
        simpleDraweeViewDesginerInfoInfor = (SimpleDraweeView) findViewById(R.id.simpleDraweeView_DesginerInfo_infor);
        textviewNameInfoDetail = (TextView) findViewById(R.id.textview_name_infoDetail);
        textviewIntroduceInfoDetail = (TextView) findViewById(R.id.textview_introduce_infoDetail);
        imagebuttonBack = (ImageButton) findViewById(R.id.imagebutton_back);
        simpleDraweeViewDesginerInfo = (SimpleDraweeView) findViewById(R.id.simpleDraweeView_DesginerInfo);
        textViewDessignNameMagnizeDetail = (TextView) findViewById(R.id.textView_DessignName_MagnizeDetail);
        textViewCityMagnizeDetail = (TextView) findViewById(R.id.textView_City_MagnizeDetail);
        imagebuttonSharedSina.setOnClickListener(this);
        imagebuttonSharedQQ.setOnClickListener(this);
        imagebuttonSharedWeixin.setOnClickListener(this);
        scrollView_imageInfo = (ScrollView) findViewById(R.id.scrollView_imageInfo);
        layout_desginerinfotop = (RelativeLayout) findViewById(R.id.layout_desginerinfotop);
        scrollView_imageInfo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float lastY = 0;

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        lastY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:

                        float disY = event.getY() - lastY;

                        //垂直方向滑动
                        if (disY > 0) {
                            layout_desginerinfotop.setVisibility(View.GONE);
                        }else if(disY < 0){
                            layout_desginerinfotop.setVisibility(View.VISIBLE);
                        }
                        //是否向上滑动


                        break;
                }

                return false;
            }
        });


    }

    private void initwebView() {
        webviewMagazineinfo = (WebView) findViewById(R.id.webview_magazineinfo);
        //初始化webview;
        webviewMagazineinfo.setWebViewClient(new WebViewClient());
        WebSettings settings = webviewMagazineinfo.getSettings();
        settings.setJavaScriptEnabled(true);
        webviewMagazineinfo.setWebChromeClient(new WebChromeClient());
        settings.setTextSize(WebSettings.TextSize.NORMAL);
    }


    @Override
    public void initData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);//获取跳转过来传的值，此处为id
        url = STARTURL + id + ENDTURL;//初始化网址
        new Thread(new Runnable() {//启动子线程获取数据
            @Override
            public void run() {
                try {
                    String jsonString = OkHttpClientHelper.getStringFromURL(mContext, url, "");
                    Object mode = ParserObjectFromJson.jsonStringToModel(jsonString, ParserObjectFromJson.MAZAGINEDETAILBEAN);
                    MazagineDetailBean androidbean = (MazagineDetailBean) mode;
                    MazagineDetailBean.DataBean data = androidbean.getData();
                    webView_url = data.getWeb_url() + "";
                    stringHtml = data.getContent();
                    title = data.getTitle();
                    subtitle = data.getSub_title();
                    titleimageurl = data.getImage_url();
                    MazagineDetailBean.DataBean.DesignersBean designers = data.getDesigners().get(0);
                    designersName = designers.getName();
                    designersCity = designers.getCity();
                    designersIamgeurl = designers.getAvatar_url();
                    designerslabel = designers.getLabel();


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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imagebutton_shared_sina:
                Toast.makeText(MagazineDetailsA.this, "新浪微博", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imagebutton_shared_QQ:
                break;
            case R.id.imagebutton_shared_weixin:
                break;
            case R.id.imagebutton_back:
                this.finish();//点击销毁当前页面
                break;
        }
    }
}
