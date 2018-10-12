package com.neuedu.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页模型
 * @param <T>
 */
public class PageModel<T> implements Serializable{

    //每页数据
    private List<T> data;
    private Long totalPage;//总共页数
    private boolean isFirst;//是否为首页
    private boolean isLast;//是否为最有一页

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }
}
