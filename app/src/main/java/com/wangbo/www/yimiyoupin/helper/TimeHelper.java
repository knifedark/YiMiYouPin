package com.wangbo.www.yimiyoupin.helper;

/**
 * Created by Administrator on 2016/8/12.
 */
public class TimeHelper {

    /**
     *该方法能够将事件戳转化为对应的几天，几小时，几分钟前
     * @param timeStr   从网络中获取的时间戳
     * @return  //返回处理好的数据
     */
    public static String getStandardDate(String timeStr) {

        StringBuffer sb = new StringBuffer();

        long t = Long.parseLong(timeStr);
        long time = System.currentTimeMillis() - (t*1000);
        long mill = (long) Math.ceil(time /1000);//秒前

        long minute = (long) Math.ceil(time/60/1000.0f);// 分钟前

        long hour = (long) Math.ceil(time/60/60/1000.0f);// 小时

        long day = (long) Math.ceil(time/24/60/60/1000.0f);// 天前

        if (day - 1 > 0) {//判定天数是否大于1
            sb.append(day + "天"); //将天数追加至字符串内
        } else if (hour - 1 > 0) {//判定小时是否大于1小时
            if (hour >= 24) {
                sb.append("1天");
            } else {
                sb.append(hour + "小时");//将小时追加至字符串内
            }
        } else if (minute - 1 > 0) {
            if (minute == 60) {
                sb.append("1小时");
            } else {
                sb.append(minute + "分钟");
            }
        } else if (mill - 1 > 0) {
            if (mill == 60) {
                sb.append("1分钟");
            } else {
                sb.append(mill + "秒");
            }
        } else {
            sb.append("刚刚");
        }
        if (!sb.toString().equals("刚刚")) {
            sb.append("前");
        }
        return sb.toString();
    }
}
