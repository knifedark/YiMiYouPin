package com.wangbo.www.yimiyoupin.helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wangbo.www.yimiyoupin.androidbean.DesginerBean;
import com.wangbo.www.yimiyoupin.androidbean.MazagineDetailBean;

/**
 * Created by Administrator on 2016/8/20.
 */
public class ParserObjectFromJson  {
    static Gson gson = new Gson();
    public static final int MAZAGINEDETAILBEAN = 0;
    public static final int DESGINERBEAN = 1;
    public static Object jsonStringToModel(String jsonString, int type) {
        //实例化Json对象
        switch (type) {
            case 0:
                //解析Json数据，返回对象
                MazagineDetailBean mode0 = gson.fromJson(jsonString, new TypeToken<MazagineDetailBean>() {
                }.getType());
                return  mode0;
            case 1:
                //解析Json数据，返回对象
                DesginerBean mode1 = gson.fromJson(jsonString, new TypeToken<DesginerBean>() {
                }.getType());
                return  mode1;

        }
        return  null;
    }
}
