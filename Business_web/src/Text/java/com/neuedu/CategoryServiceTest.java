package com.neuedu;

import com.neuedu.Service.ICategoryService;
import com.neuedu.Service.impl.CategoryServiceImpl;
import org.junit.Test;

public class CategoryServiceTest {
    @Test
    public void test(){
        ICategoryService categoryService=new CategoryServiceImpl();
        System.out.println(categoryService.findSubCategoryById(10030));
    }



}
