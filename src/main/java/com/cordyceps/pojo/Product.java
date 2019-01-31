package com.cordyceps.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Integer id;

    private Integer categoryId;

    private Integer diggerId;

    private BigDecimal length;

    private BigDecimal weight;

    private BigDecimal price;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String detail;

    public Product(Integer id, Integer categoryId, Integer diggerId, BigDecimal length, BigDecimal weight, BigDecimal price, Integer status, Date createTime, Date updateTime, String detail) {
        this.id = id;
        this.categoryId = categoryId;
        this.diggerId = diggerId;
        this.length = length;
        this.weight = weight;
        this.price = price;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.detail = detail;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}