package com.neuedu.pojo;


public class Shipping {

    private Integer id;
    private Integer user_id;
    private String receiver_name; //收货姓名
    private String receiver_phone; //收获固定姓名
    private String receiver_mobile;//收获移动电话
    private String receiver_province;//省份
    private String receiver_city;//城市
    private String receiver_district;//区/县
    private String receiver_address;//详细地址
    private String receiver_zip;//邮编
    private String create_time;
    private String update_time;


    public Shipping() {

    }

    public Shipping(Integer id, Integer user_id, String receiver_name, String receiver_phone, String receiver_mobile, String receiver_province, String receiver_city, String receiver_district, String receiver_address, String receiver_zip, String create_time, String update_time) {
        this.id = id;
        this.user_id = user_id;
        this.receiver_name = receiver_name;
        this.receiver_phone = receiver_phone;
        this.receiver_mobile = receiver_mobile;
        this.receiver_province = receiver_province;
        this.receiver_city = receiver_city;
        this.receiver_district = receiver_district;
        this.receiver_address = receiver_address;
        this.receiver_zip = receiver_zip;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public String getReceiver_mobile() {
        return receiver_mobile;
    }

    public void setReceiver_mobile(String receiver_mobile) {
        this.receiver_mobile = receiver_mobile;
    }

    public String getReceiver_province() {
        return receiver_province;
    }

    public void setReceiver_province(String receiver_province) {
        this.receiver_province = receiver_province;
    }

    public String getReceiver_city() {
        return receiver_city;
    }

    public void setReceiver_city(String receiver_city) {
        this.receiver_city = receiver_city;
    }

    public String getReceiver_district() {
        return receiver_district;
    }

    public void setReceiver_district(String receiver_district) {
        this.receiver_district = receiver_district;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public String getReceiver_zip() {
        return receiver_zip;
    }

    public void setReceiver_zip(String receiver_zip) {
        this.receiver_zip = receiver_zip;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
