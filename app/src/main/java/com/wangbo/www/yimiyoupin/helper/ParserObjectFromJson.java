package com.wangbo.www.yimiyoupin.helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wangbo.www.yimiyoupin.androidbean.DesginerBean;
import com.wangbo.www.yimiyoupin.androidbean.DesginerMagnizeBean;
import com.wangbo.www.yimiyoupin.androidbean.DesginerProductsBean;
import com.wangbo.www.yimiyoupin.androidbean.DesginerShopOnLineBean;
import com.wangbo.www.yimiyoupin.androidbean.MazagineDetailBean;

/**
 * Created by Administrator on 2016/8/20.
 */
public class ParserObjectFromJson  {
    static Gson gson = new Gson();
    public static final int MAZAGINEDETAILBEAN = 0;
    public static final int DESGINERBEAN = 1;
    public static final int DESGINERPRODUCTSBEAN = 2;
    public static final int DESGINERSHOPONLINEBEAN = 3;
    public static final int DESGINERMAGNIZEBEAN = 4;
    public static Object jsonStringToModel(String jsonString, int type) {
        //实例化Json对象
        switch (type) {
            case 0://画报数据解析
                //解析Json数据，返回对象
                MazagineDetailBean mode0 = gson.fromJson(jsonString, new TypeToken<MazagineDetailBean>() {
                }.getType());
                return  mode0;
            case 1://设计师详情解析
                //解析Json数据，返回对象
                DesginerBean mode1 = gson.fromJson(jsonString, new TypeToken<DesginerBean>() {
                }.getType());
                return  mode1;
            case 2://获取设计师产品数据
                //解析Json数据，返回对象
                DesginerProductsBean mode2 = gson.fromJson(jsonString, new TypeToken<DesginerProductsBean>() {
                }.getType());
                return  mode2;
            case 3://获取设计师在线商店数据
                //解析Json数据，返回对象
                DesginerShopOnLineBean mode3 = gson.fromJson(jsonString, new TypeToken<DesginerShopOnLineBean>() {
                }.getType());
                return  mode3;
            case 4://获取设计师在线商店数据
                //解析Json数据，返回对象
                DesginerMagnizeBean mode4 = gson.fromJson(jsonString, new TypeToken<DesginerMagnizeBean>() {
                }.getType());
                return  mode4;

        }
        return  null;
    }
}
