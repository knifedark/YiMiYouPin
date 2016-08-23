package com.wangbo.www.yimiyoupin.androidbean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * 新建对象，用来传递画报所需要的值
 */
public class MagnizeTitleBean implements Parcelable{
    private String id;//声明画报id，用于跳转传值
    private String title;//画报标题
    private String sub_title;//画报子标题
    private String image_url;//画报图片地址

    protected MagnizeTitleBean(Parcel in) {
        id = in.readString();
        title = in.readString();
        sub_title = in.readString();
        image_url = in.readString();
    }
    public MagnizeTitleBean(){

    }

    public static final Creator<MagnizeTitleBean> CREATOR = new Creator<MagnizeTitleBean>() {
        @Override
        public MagnizeTitleBean createFromParcel(Parcel in) {
            return new MagnizeTitleBean(in);
        }

        @Override
        public MagnizeTitleBean[] newArray(int size) {
            return new MagnizeTitleBean[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(sub_title);
        dest.writeString(image_url);
    }
}
