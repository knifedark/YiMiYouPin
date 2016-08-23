package com.wangbo.www.yimiyoupin.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wangbo.www.yimiyoupin.R;
import com.wangbo.www.yimiyoupin.androidbean.MagnizeTitleBean;
import com.wangbo.www.yimiyoupin.androidbean.MazagineDetailBean;
import com.wangbo.www.yimiyoupin.helper.OkHttpClientHelper;
import com.wangbo.www.yimiyoupin.helper.ParserObjectFromJson;
import com.wangbo.www.yimiyoupin.helper.SwipeBackToolBarActivity;
import com.wangbo.www.yimiyoupin.interfacepak.MyInterface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private String id;//声明拼凑地址 需要的id
    private String url;
    private String webView_url;//webview地址
    private String stringHtml;
    private String subtitle;
    private String designersName;
    private String designersCity;
    private String designersIamgeurl;
    private ScrollView scrollView_imageInfo;
    private RelativeLayout layout_desginerinfotop;
    private boolean flag = false;
    private TextView textview_collectionNum;
    private TextView textview_commentNum;
    private ImageView  imageview_comment;
    private TextView textview_saysomething;
    private int desginerurlid ;//声明设计师id
    private int commentNum;
    private int collectionNum;
    private MagnizeTitleBean magnizeTitleBean =new MagnizeTitleBean();//声明画报对象
    private LinearLayout linearlayout_refer_desginer;
    private List<MazagineDetailBean.DataBean.CommentsBean> list_comments  = new ArrayList<>();
    private List<MazagineDetailBean.DataBean.ReferProductsBean> refer_products=new ArrayList<>();


    String CSS_STYPE = "<head><style>*{font-family:'微软雅黑';font-size:16px;line-height:20px;} p {color:#333;} a {color:#3E62A6;} h2{text-align:center;} img {max-width:100%;display:block;margin-top:16px;}pre {font-size:9pt;line-height:12pt;font-family:Courier New,Arial;border:1px solid #ddd;border-left:5px solid #6CE26C;background:#f6f6f6;padding:5px;}</style></head>";
    private static final String STARTURL = "http://design.zuimeia.com/api/v1/article/";//拼凑地址前半部分
    //拼凑地址后半部分
    private static final String ENDTURL = "/?device_id=867905022979506&platform=android&lang=zh&appVersion=1.1.7&appVersionCode=10107&systemVersion=22&countryCode=CN&user_id=39146&token=4el-0ada01cde1909345cb58&package_name=com.zuiapps.zuiworld";
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    textviewTitleMagazineDetail.setText(magnizeTitleBean.getTitle());
                    textviewSubtitleMagazineDetail.setText(magnizeTitleBean.getSub_title());
                    simpleDraweeViewTitle.setImageURI(Uri.parse(magnizeTitleBean.getImage_url()));
                    textViewDessignNameMagnizeDetail.setText(designersName);
                    textviewNameInfoDetail.setText(designersName);
                    textViewCityMagnizeDetail.setText(designersCity);
                    simpleDraweeViewDesginerInfo.setImageURI(Uri.parse(designersIamgeurl));
                    simpleDraweeViewDesginerInfoInfor.setImageURI(Uri.parse(designersIamgeurl));
                    textviewIntroduceInfoDetail.setText(designerslabel);
                    webviewMagazineinfo.loadDataWithBaseURL(null, CSS_STYPE + stringHtml, "text/html", "utf_8", null);
                    textview_collectionNum.setText(collectionNum+"");
                    textview_commentNum.setText(commentNum+"");

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
        imageview_comment = (ImageView) findViewById(R.id.imageview_comment);
        scrollView_imageInfo = (ScrollView) findViewById(R.id.scrollView_imageInfo);
        layout_desginerinfotop = (RelativeLayout) findViewById(R.id.layout_desginerinfotop);
        textview_collectionNum = (TextView) findViewById(R.id.textview_collectionNum);
        textview_commentNum = (TextView) findViewById(R.id.textview_commentNum);
        textview_saysomething= (TextView) findViewById(R.id.textview_saysomething);
        linearlayout_refer_desginer= (LinearLayout) findViewById(R.id.linearlayout_refer_desginer);
        linearlayout_refer_desginer.setOnClickListener(this);
        imagebuttonSharedSina.setOnClickListener(this);
        imagebuttonSharedQQ.setOnClickListener(this);
        imagebuttonSharedWeixin.setOnClickListener(this);
        imageview_comment.setOnClickListener(this);
        textview_saysomething.setOnClickListener(this);




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
        id = intent.getStringExtra("id");//获取跳转过来传的值，此处为id
        magnizeTitleBean.setId(id);//将获取的地址设置到对象中
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
                    refer_products= data.getRefer_products();//获取产品地址
                    magnizeTitleBean.setTitle(data.getTitle());//初始化要传递的画报对象id
                    magnizeTitleBean.setSub_title(data.getSub_title());
                    magnizeTitleBean.setImage_url(data.getImage_url());
                    MazagineDetailBean.DataBean.DesignersBean designers = data.getDesigners().get(0);
                    designersName = designers.getName();
                    designersCity = designers.getCity();
                    designersIamgeurl = designers.getAvatar_url();
                    designerslabel = designers.getLabel();
                    desginerurlid = designers.getId();
                    commentNum=data.getComment_num();
                    collectionNum=data.getLike_user_num();
                    list_comments=data.getComments();



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
            case R.id.textview_saysomething:
            case R.id.imageview_comment:
                Intent intent = new Intent();
                intent.setClass(mContext,CommentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) list_comments);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.relative_desginer:
            case R.id.linearlayout_refer_desginer://跳转至设计师页面
                Intent intent_desginer = new Intent();
                intent_desginer.setClass(mContext,DesginerInfomationActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("desginerurlid",desginerurlid);
                bundle1.putParcelable("MagnizeTitleBean",magnizeTitleBean);
                bundle1.putParcelableArrayList("ReferProductsBean", (ArrayList<? extends Parcelable>) refer_products);
                intent_desginer.putExtras(bundle1);
                startActivity(intent_desginer);
                break;


        }
    }
}
