package com.example.bookdinner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipping {

    private Integer id; //地址的id

    private Integer userId; //用户的id

    private String address; //详细地址信息

    private String sign; //标签

    private String name; //收件人姓名

    private String sex; //性别

    private String phone; //手机号

    private Integer isDefault; //是否为默认

    private Integer status; //状态码

    private Timestamp created; //创建时间

    private String isDefaultString; //给前端显示的默认字符串

    public Shipping(Integer userId, String address, String sign, String name, String sex, String phone, Integer isDefault, Integer status) {
        this.userId = userId;
        this.address = address;
        this.sign = sign;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.isDefault = isDefault;
        this.status = status;
    }
}
