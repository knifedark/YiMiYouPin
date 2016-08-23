package com.wangbo.www.yimiyoupin.androidbean;

import java.util.List;

/**
 * 画报页使用，并且设计师详情页页使用
 */
public class DesginerBean {
    /**
     * introduce_images : ["http://dstatic.zuimeia.com/common/image/2016/8/4/9d7d934d-5828-42e3-9bf7-c98fe59320ba_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/4/9e4d8172-8353-4211-af04-25151eb87da7_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/4/1ce6e286-53a0-4439-b904-a27caacb748c_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/4/bed7f75f-5656-44f1-950c-4e12e303d326_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/4/c326f4e7-160d-4213-a27c-a17d156ca8c6_1000x1000.jpeg"]
     * city : 纽约
     * concept : 我的作品是我个人生活的演变和进化
     * article_num : 1
     * name : Chris Habana
     * product_num : 10
     * label : Chrishabana 创始人
     * avatar_url : http://dstatic.zuimeia.com/designer/avatar/2016/8/2/3b70f136-3ae3-4c9d-b644-2978cbb8ede6.jpg
     * is_followed : 0
     * id : 45
     * description : Chrihabana 分别在美国和菲律宾生活过，从小酷爱科幻，朋克和哥特以及90年代的反主流文化。他设计的饰品融合了哥特元素，部落元素，风格独特同时精致细腻。
     */

    private DataBean data;
    /**
     * data : {"introduce_images":["http://dstatic.zuimeia.com/common/image/2016/8/4/9d7d934d-5828-42e3-9bf7-c98fe59320ba_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/4/9e4d8172-8353-4211-af04-25151eb87da7_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/4/1ce6e286-53a0-4439-b904-a27caacb748c_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/4/bed7f75f-5656-44f1-950c-4e12e303d326_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/4/c326f4e7-160d-4213-a27c-a17d156ca8c6_1000x1000.jpeg"],"city":"纽约","concept":"我的作品是我个人生活的演变和进化","article_num":1,"name":"Chris Habana","product_num":10,"label":"Chrishabana 创始人 ","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/2/3b70f136-3ae3-4c9d-b644-2978cbb8ede6.jpg","is_followed":0,"id":45,"description":"Chrihabana 分别在美国和菲律宾生活过，从小酷爱科幻，朋克和哥特以及90年代的反主流文化。他设计的饰品融合了哥特元素，部落元素，风格独特同时精致细腻。"}
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
        private String city;
        private String concept;
        private int article_num;
        private String name;
        private int product_num;
        private String label;
        private String avatar_url;
        private int is_followed;
        private int id;
        private String description;
        private List<String> introduce_images;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getConcept() {
            return concept;
        }

        public void setConcept(String concept) {
            this.concept = concept;
        }

        public int getArticle_num() {
            return article_num;
        }

        public void setArticle_num(int article_num) {
            this.article_num = article_num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getProduct_num() {
            return product_num;
        }

        public void setProduct_num(int product_num) {
            this.product_num = product_num;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getIs_followed() {
            return is_followed;
        }

        public void setIs_followed(int is_followed) {
            this.is_followed = is_followed;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<String> getIntroduce_images() {
            return introduce_images;
        }

        public void setIntroduce_images(List<String> introduce_images) {
            this.introduce_images = introduce_images;
        }
    }
}
