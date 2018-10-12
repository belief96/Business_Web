package com.neuedu.pojo;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Category implements Serializable{

    private int id;
    private int parent_id;  //类别id
    private List<Product> productList;

    private String name; //类别名
    private int status; //类别状态 1-正常,2-废弃'
    private int sort_order; //创建时间
    private Date create_time; //创建时间
    private Date update_time; // 更新时间


    public Category(int id, int parent_id, List<Product> productList, String name, int status,
                    int sort_order, Date create_time, Date update_time) {
        this.id = id;
        this.parent_id = parent_id;
        this.productList = productList;
        this.name = name;
        this.status = status;
        this.sort_order = sort_order;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public Category(){

    }
    @PostConstruct
    public void init(){
        System.out.println("==catory init==");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSort_order() {
        return sort_order;
    }

    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
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
        return "Category{" +
                "id=" + id +
                ", parent_id=" + parent_id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", sort_order=" + sort_order +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
