package com.wangbo.www.yimiyoupin.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wangbo.www.yimiyoupin.R;
import com.wangbo.www.yimiyoupin.adapter.RecyclerAdapterDesginerProducts;
import com.wangbo.www.yimiyoupin.adapter.ViewPagerAdapterDesginerrefor;
import com.wangbo.www.yimiyoupin.adapter.ViewpagerAdapterDesginerinforIntroduce;
import com.wangbo.www.yimiyoupin.androidbean.DesginerBean;
import com.wangbo.www.yimiyoupin.androidbean.DesginerMagnizeBean;
import com.wangbo.www.yimiyoupin.androidbean.DesginerProductsBean;
import com.wangbo.www.yimiyoupin.androidbean.DesginerShopOnLineBean;
import com.wangbo.www.yimiyoupin.androidbean.MagnizeTitleBean;
import com.wangbo.www.yimiyoupin.androidbean.MazagineDetailBean;
import com.wangbo.www.yimiyoupin.helper.OkHttpClientHelper;
import com.wangbo.www.yimiyoupin.helper.ParserObjectFromJson;
import com.wangbo.www.yimiyoupin.helper.SwipeBackToolBarActivity;
import com.wangbo.www.yimiyoupin.interfacepak.MyInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.Inflater;

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
    TabLayout tablayoutDescriptionDesginerinfo;
    @BindView(R.id.tollbar_desginer_info)
    Toolbar tollbarDesginerInfo;
    @BindView(R.id.viewpager_description_desginerinfo)
    ViewPager viewpagerDescriptionDesginerinfo;
    private Context mContext = this;
    private Unbinder bind;
    private int desginerurlid;
    private List<String> introduce_images;
    private DesginerBean bean;
    private String concept;
    private String name;
    private String label;
    private String avatar_url;
    private String description;
    private List<DesginerProductsBean.DataBean.ProductsBean> productList;//产品数据
    private List<DesginerMagnizeBean.DataBean.ArticlesBean> magnizeList;//画报数据
    private DesginerShopOnLineBean.DataBean onlineMode;//在线数据
    private List<View> totalist = new ArrayList<>();
    private boolean productFlag = false;
    private boolean onlineFlag = false;
    private boolean mangnizeFlag = false;
    private LayoutInflater inflater ;
    private List<String> list ;//tabLayout数据源
    private static final String STARTDESGINERURL = "http://design.zuimeia.com/api/v1/designer/";//设计师详情接口
    private static final String ENDDESGINERURL = "/?device_id=862845021555839&platform=android&lang=zh&appVersion=1.0.6&appVersionCode=10006&systemVersion=21&countryCode=CN&user_id=39420&token=4em-71642f70e4e359f6ba5a&package_name=com.zuiapps.zuiworld";
    private static final String STARTPRODUSURL = "http://design.zuimeia.com/api/v1/products/designer/";//产品接口
    private static final String ENDPRODUSURL = "/?page=1&page_size=30&user_id=0&device_id=862845021555839&platform=android&lang=zh&appVersion=1.0.6&appVersionCode=10006&systemVersion=21&countryCode=CN&user_id=39420&token=4em-71642f70e4e359f6ba5a&package_name=com.zuiapps.zuiworld";
    private static final String STARTIMAGEURL = "http://design.zuimeia.com/api/v1/articles/designer/";//画报接口
    private static final String ENDIMAGEURL = "/?page=1&page_size=30&user_id=39420&device_id=862845021555839&platform=android&lang=zh&appVersion=1.0.6&appVersionCode=10006&systemVersion=21&countryCode=CN&user_id=39420&token=4en-0f7478d4ca4425af44d1&package_name=com.zuiapps.zuiworld";//
    private static final String STARTONLINEURL = "http://design.zuimeia.com/api/v1/shops/designer/";//旗舰门店和线上
    private static final String ENDONLINEURL = "/?device_id=862845021555839&platform=android&lang=zh&appVersion=1.0.6&appVersionCode=10006&systemVersion=21&countryCode=CN&user_id=39420&token=4em-71642f70e4e359f6ba5a&package_name=com.zuiapps.zuiworld";
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0://获取数据，设置设计师介绍
                    ViewpagerAdapterDesginerinforIntroduce adapter = new ViewpagerAdapterDesginerinforIntroduce(mContext, introduce_images);
                    viewpagerImage.setAdapter(adapter);
                    textviewDescriptionDesginerinfo.setText(description);
                    textviewDesginerinfoConcept.setText(concept);
                    textviewIntroduceInfoDetail.setText(label);
                    textviewNameInfoDetail.setText(name);
                    simpleDraweeViewDesginerInfoInfor.setImageURI(Uri.parse(avatar_url));
                    break;
                case 1://获取viewPager产品-海报-门店信息的数据源

                    if (productFlag && onlineFlag && mangnizeFlag) {//如果数据源都获取完毕
                        //判定数据元是否存在空数据,如果不为空
                        if (productList == null || productList.isEmpty()) {
                        } else {
                            List<String> listproduce = new ArrayList<>();//初始化产品地址
                            //初始化图片地址
                            for (int i = 0; i < productList.size(); i++) {
                                listproduce.add(productList.get(i).getCover_images().get(0) + "");
                                Log.i("================", "handleMessage: "+productList.get(i).getCover_images().get(0) + "");
                            }
                            RecyclerAdapterDesginerProducts recycleradapter = new RecyclerAdapterDesginerProducts(R.layout.item_recyclerview, listproduce);
                            RecyclerView recyclerView = new RecyclerView(mContext);
                            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
                            recyclerView.setAdapter(recycleradapter);
                            totalist.add(recyclerView);
                            list.add("产品");
                        }
                        if (magnizeList == null || magnizeList.isEmpty()) {
                        } else {
                            //填充View，设置数据
                            View view = inflater.inflate(R.layout.item_magnize_descripe, null);
                            TextView textview_title_Magazine_detail = (TextView) view.findViewById(R.id.textview_title_Magazine_detail);
                            TextView textview_subtitle_Magazine_detail = (TextView) view.findViewById(R.id.textview_subtitle_Magazine_detail);
                            SimpleDraweeView simpleDraweeView_Title = (SimpleDraweeView) view.findViewById(R.id.simpleDraweeView_Title);
                            textview_title_Magazine_detail.setText(magnizeList.get(0).getTitle() + "");
                            textview_subtitle_Magazine_detail.setText(magnizeList.get(0).getSub_title() + "");
                            simpleDraweeView_Title.setImageURI(Uri.parse(magnizeList.get(0).getImage_url() + ""));
                            totalist.add(view);
                            list.add("画报");

                        }
                        String shop_image = onlineMode.getShop_image();
                        if (TextUtils.isEmpty(shop_image)) {
                        } else {
                            View view = inflater.inflate(R.layout.item_shop_desginerviewpager, null);
                            ImageView imageview_shop_viewpager = (ImageView) view.findViewById(R.id.imageview_shop_viewpager);
                            TextView textview_cityShop_viewpager = (TextView) findViewById(R.id.textview_cityShop_viewpager);
                            TextView textview_ShopName_viewpager = (TextView) findViewById(R.id.textview_ShopName_viewpager);
                            TextView textview_ShopAddress_viewpager = (TextView) findViewById(R.id.textview_ShopAddress_viewpager);
                            Glide.with(mContext).load(onlineMode.getShop_image()).into(imageview_shop_viewpager);
                            textview_cityShop_viewpager.setText(onlineMode.getShops().get(0).getCity() + "");
                            textview_ShopName_viewpager.setText(onlineMode.getShops().get(0).getName() + "");
                            textview_ShopAddress_viewpager.setText(onlineMode.getShops().get(0).getAddress() + "");
                            list.add("旗舰门店");
                            totalist.add(view);

                        }
                        String onlineshop_image = onlineMode.getOnline_shop_image();
                        if (TextUtils.isEmpty(onlineshop_image)) {
                        } else {
                            View view = inflater.inflate(R.layout.item_onlineshop_viewpager, null);
                            ImageView imageview_onlineshop_viewpager = (ImageView) view.findViewById(R.id.imageview_onlineshop_viewpager);
                            TextView textview_onlineshopAddress_viewpager = (TextView) view.findViewById(R.id.textview_onlineshopAddress_viewpager);
                            Glide.with(mContext).load(onlineMode.getOnline_shop_image()).into(imageview_onlineshop_viewpager);
                            textview_onlineshopAddress_viewpager.setText(onlineMode.getOnline_shops().get(0).getName());
                            list.add("线上购物");
                            totalist.add(view);

                        }
                        ViewPagerAdapterDesginerrefor adapter_viewpager = new ViewPagerAdapterDesginerrefor(totalist,list);
                        viewpagerDescriptionDesginerinfo.setAdapter(adapter_viewpager);
                        tablayoutDescriptionDesginerinfo.setupWithViewPager(viewpagerDescriptionDesginerinfo);

                    }


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
        inflater=getLayoutInflater();
        list = new ArrayList<>();

    }

    @Override
    public void initView() {


    }

    @Override
    public void initData() {

        Intent intent = getIntent();//获取跳转传过来的值
        desginerurlid = intent.getIntExtra("desginerurlid", -1);//拼凑网络地址，获取DesginerBean
        final String url_desginer = STARTDESGINERURL + desginerurlid + ENDDESGINERURL;//获取设计师详细地址
        new Thread(new Runnable() {//子线程获取数据
            @Override
            public void run() {
                try {
                    String jsonDesginerBean = OkHttpClientHelper.getStringFromURL(mContext, url_desginer, "DesginerBean");
                    Object object = ParserObjectFromJson.jsonStringToModel(jsonDesginerBean, ParserObjectFromJson.DESGINERBEAN);
                    bean = (DesginerBean) object;
                    introduce_images = bean.getData().getIntroduce_images();//获取介绍页面的viewpager图片地址
                    concept = bean.getData().getConcept();//获取个人介绍
                    name = bean.getData().getName();//获取设计师姓名
                    label = bean.getData().getLabel();//获取设计师标签
                    avatar_url = bean.getData().getAvatar_url();//获取设计师地址图片
                    description = bean.getData().getDescription();//获取设计师描述
                    mhandler.sendEmptyMessage(0);//发送给Handler数据
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();
        //获取产品数据
        final String desginer_producets = STARTPRODUSURL + desginerurlid + ENDPRODUSURL;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json1 = OkHttpClientHelper.getStringFromURL(mContext, desginer_producets, "");
                    Object jsonMode1 = ParserObjectFromJson.jsonStringToModel(json1, ParserObjectFromJson.DESGINERPRODUCTSBEAN);
                    DesginerProductsBean bean = (DesginerProductsBean) jsonMode1;
                    productList = bean.getData().getProducts();
                    productFlag = true;
                    mhandler.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //获取画报信息
        final String desginer_image = STARTIMAGEURL + desginerurlid + ENDIMAGEURL;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = OkHttpClientHelper.getStringFromURL(mContext, desginer_image, "");
                    Object jsonMode = ParserObjectFromJson.jsonStringToModel(json, ParserObjectFromJson.DESGINERMAGNIZEBEAN);
                    DesginerMagnizeBean bean = (DesginerMagnizeBean) jsonMode;
                    magnizeList = bean.getData().getArticles();
                    mangnizeFlag = true;
                    mhandler.sendEmptyMessage(1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //获取门店数据
        final String onlineurl = STARTONLINEURL + desginerurlid + ENDONLINEURL;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = OkHttpClientHelper.getStringFromURL(mContext, onlineurl, "");
                    Object jsonMode = ParserObjectFromJson.jsonStringToModel(json, ParserObjectFromJson.DESGINERSHOPONLINEBEAN);
                    DesginerShopOnLineBean bean = (DesginerShopOnLineBean) jsonMode;
                    onlineMode = bean.getData();
                    onlineFlag = true;
                    mhandler.sendEmptyMessage(1);

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
        productFlag = false;//当前页面销毁时，将标志位重新恢复为false,等待下一次加载数据
        onlineFlag = false;
        mangnizeFlag = false;
    }

}
