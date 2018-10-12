package com.neuedu.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Product implements Serializable{
    private Integer id;  // 商品id
    private Integer category_id;
    private Category category;
    private String name; // 商品名称
    private String subtitle; // 商品副标题
    private String main_images; //主图地址，url相对地址
    private String sub_images;  //图片地址，json格式
    private String detayl;  //商品详情
    private BigDecimal price;  //商品价格

    private Integer stock;  //商品库存
    private Integer status;   //商品状态
    private Date create_time;  //创建时间
    private Date update_time;  //更新时间

    public Product(Integer id, Integer category_id, Category category, String name, String subtitle, String main_images, String sub_images, String detayl, BigDecimal price, Integer stock, Integer status, Date create_time, Date update_time) {
        this.id = id;
        this.category_id = category_id;
        this.category = category;
        this.name = name;
        this.subtitle = subtitle;
        this.main_images = main_images;
        this.sub_images = sub_images;
        this.detayl = detayl;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public Product() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMain_images() {
        return main_images;
    }

    public void setMain_images(String main_images) {
        this.main_images = main_images;
    }

    public String getSub_images() {
        return sub_images;
    }

    public void setSub_images(String sub_images) {
        this.sub_images = sub_images;
    }

    public String getDetayl() {
        return detayl;
    }

    public void setDetayl(String detayl) {
        this.detayl = detayl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", main_images='" + main_images + '\'' +
                ", sub_images='" + sub_images + '\'' +
                ", detayl='" + detayl + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", status=" + status +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
