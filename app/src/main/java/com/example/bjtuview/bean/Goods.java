/**
 * Copyright 2023 bejson.com
 */
package com.example.bjtuview.bean;
import java.util.List;

/**
 * Auto-generated: 2023-05-18 16:22:0
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Goods {
    private int goodsId;
    private int spanSize;
    private List<String> banners;
    private String imageUrl;
    private String text;
    private String WebPage;
    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }
    public int getGoodsId() {
        return goodsId;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }
    public int getSpanSize() {
        return spanSize;
    }

    public void setBanners(List<String> banners) {
        this.banners = banners;
    }
    public List<String> getBanners() {
        return banners;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setWebPage(String WebPage) {
        this.WebPage = WebPage;
    }
    public String getWebPage() {
        return WebPage;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", spanSize=" + spanSize +
                ", banners=" + banners +
                ", imageUrl='" + imageUrl + '\'' +
                ", text='" + text + '\'' +
                ", WebPage='" + WebPage + '\'' +
                '}';
    }
}