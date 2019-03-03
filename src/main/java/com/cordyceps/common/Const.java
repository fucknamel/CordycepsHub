package com.cordyceps.common;

import com.google.common.collect.Sets;

import java.util.Set;

public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";

    public static final String USERNAME = "username";

    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");
    }

    public interface Role{
        int ROLE_ADMIN = 0;//管理员
        int ROLE_DIGGER = 1;//挖掘者
        int ROLE_CUSTOMER = 2;//顾客
        int ROLE_SELLER = 3;//商家
    }

    //二维码
    public interface QRcode{
        int HEIGHT = 320;
        int WEIGHT = 320;
        String FORMAT = "png";
    }

    public enum ProductStatusEnum{
        ON_SALE(1, "在线");

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        ProductStatusEnum(int code, String value){
            this.code = code;
            this.value = value;
        }
    }
}
