package com.wangbo.www.yimiyoupin.androidbean;

import java.util.List;

/**
 * 设计师线上门店和旗舰店的对象
 */
public class DesginerShopOnLineBean {
    /**
     * shop_image : http://dstatic.zuimeia.com/brand/shop/2016/8/19/34cfa64d-a007-404c-90a2-de73189e1476.jpg
     * shops : [{"city":"波特兰","address":"522 N Thompson St #1, Portland, OR 97211, United States","id":146,"name":"Wood and Faulk "}]
     * online_shop_image : http://dstatic.zuimeia.com/brand/shop/2016/8/19/56e097a0-cc70-42dc-8337-c1e89e7c6553.jpg
     * online_shops : [{"link_url":"http://woodandfaulk.com/","id":138,"name":"Wood & Faulk 官方网店"}]
     */

    private DataBean data;
    /**
     * data : {"shop_image":"http://dstatic.zuimeia.com/brand/shop/2016/8/19/34cfa64d-a007-404c-90a2-de73189e1476.jpg","shops":[{"city":"波特兰","address":"522 N Thompson St #1, Portland, OR 97211, United States","id":146,"name":"Wood and Faulk "}],"online_shop_image":"http://dstatic.zuimeia.com/brand/shop/2016/8/19/56e097a0-cc70-42dc-8337-c1e89e7c6553.jpg","online_shops":[{"link_url":"http://woodandfaulk.com/","id":138,"name":"Wood & Faulk 官方网店"}]}
     * result : 1
     */

    private int result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static class DataBean {
        private String shop_image;
        private String online_shop_image;
        /**
         * city : 波特兰
         * address : 522 N Thompson St #1, Portland, OR 97211, United States
         * id : 146
         * name : Wood and Faulk
         */

        private List<ShopsBean> shops;
        /**
         * link_url : http://woodandfaulk.com/
         * id : 138
         * name : Wood & Faulk 官方网店
         */

        private List<OnlineShopsBean> online_shops;

        public String getShop_image() {
            return shop_image;
        }

        public void setShop_image(String shop_image) {
            this.shop_image = shop_image;
        }

        public String getOnline_shop_image() {
            return online_shop_image;
        }

        public void setOnline_shop_image(String online_shop_image) {
            this.online_shop_image = online_shop_image;
        }

        public List<ShopsBean> getShops() {
            return shops;
        }

        public void setShops(List<ShopsBean> shops) {
            this.shops = shops;
        }

        public List<OnlineShopsBean> getOnline_shops() {
            return online_shops;
        }

        public void setOnline_shops(List<OnlineShopsBean> online_shops) {
            this.online_shops = online_shops;
        }

        public static class ShopsBean {
            private String city;
            private String address;
            private int id;
            private String name;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class OnlineShopsBean {
            private String link_url;
            private int id;
            private String name;

            public String getLink_url() {
                return link_url;
            }

            public void setLink_url(String link_url) {
                this.link_url = link_url;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
