package com.neuedu.dao;

import com.neuedu.pojo.Category;

import java.util.List;

public interface ICategoryDao {
    /**
     * 查询类别信息
     */
    public Category  findCategoryById(int categoryId);

    public List<Category> findSubCategoryByCategoryId(int categoryId);

    public int addCategory(int parent_id,String categoryName);

    public int updateCategoryByCategoryId(Category category);
}
