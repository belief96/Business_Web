package com.neuedu;

import com.neuedu.dao.ICategoryDao;
import com.neuedu.dao.impl.CategoryDaoImpl;
import com.neuedu.pojo.Category;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

import java.util.List;


public class CategoryDaoTest {

    ICategoryDao categoryDao=new CategoryDaoImpl();

    @Test
    public void testFindCategoryById(){

        System.out.println(categoryDao.findCategoryById(10030));
    }

    @Test
    public void testfindSubCategoryByCategoryId(){
        List<Category> categoryList=categoryDao.findSubCategoryByCategoryId(10030);
        System.out.println(categoryList.size());
        System.out.println(categoryList);
    }

    @Test
    public void testaddCategory(){
        int result=categoryDao.addCategory(10030,"oppo R9");
        System.out.println(result);
    }


    @Test
    public void test(){


        System.out.println(digui(5)+"  "+5*4*3*2);
    }

    public static int digui(int n){
        if (n == 1){
            return 1;
        }else {
            return n*digui(n-1);
        }
    }

}
