package com.neuedu.Service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Category;

import java.util.List;
import java.util.Set;

public interface ICategoryService {
    public ServerResponse<List<Category>> findSubCategoryById(int category);

    public ServerResponse<String> addCategory(int parent_id, String categoryName);

    public ServerResponse<String> updateCategory(Integer categoryId,String categoryName);

    public Set<Integer> findAllChildByCategory(Set<Integer> categoryIdList, Integer categoryId);
}
