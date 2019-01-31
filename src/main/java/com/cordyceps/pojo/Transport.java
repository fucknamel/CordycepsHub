package com.cordyceps.pojo;

import java.util.Date;

public class Transport {
    private Integer id;

    private Integer productId;

    private String location;

    private String longitude;

    private String latitude;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Transport(Integer id, Integer productId, String location, String longitude, String latitude, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.productId = productId;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Transport() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
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