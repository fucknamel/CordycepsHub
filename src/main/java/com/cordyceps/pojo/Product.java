package com.cordyceps.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Integer id;

    private Integer categoryId;

    private Integer diggerId;

    private String subtitle;

    private String mainImage;

    private String subImages;

    private BigDecimal length;

    private BigDecimal weight;

    private String detail;

    private BigDecimal price;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Product(Integer id, Integer categoryId, Integer diggerId, String subtitle, String mainImage, String subImages, BigDecimal length, BigDecimal weight, String detail, BigDecimal price, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.categoryId = categoryId;
        this.diggerId = diggerId;
        this.subtitle = subtitle;
        this.mainImage = mainImage;
        this.subImages = subImages;
        this.length = length;
        this.weight = weight;
        this.detail = detail;
        this.price = price;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Product() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getDiggerId() {
        return diggerId;
    }

    public void setDiggerId(Integer diggerId) {
        this.diggerId = diggerId;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage == null ? null : mainImage.trim();
    }

    public String getSubImages() {
        return subImages;
    }

    public void setSubImages(String subImages) {
        this.subImages = subImages == null ? null : subImages.trim();
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}