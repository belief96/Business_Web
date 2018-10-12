package com.neuedu.dao.impl;

import com.neuedu.common.MyBatisUtil;
import com.neuedu.dao.ICategoryDao;
import com.neuedu.pojo.Category;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
//@Scope("singleton") //生命周期(默认singleton)
public class CategoryDaoImpl implements ICategoryDao{

    @Autowired
    private  SqlSession sqlSession;

    private int categoryId;

//    @Autowired //默认是byType
//    @Qualifier("category1") //当有两个相同名字时用（id）
    @Resource(name = "category1") //直接加载id为category1的bean
    private Category category;

//    public void setCategory(Category category) {
//        this.category = category;
//    }

    public Category getCategory() {
        return category;
    }

//    public CategoryDaoImpl(Category category){
//        System.out.println("==CategoryDaoImpl(Category category)==");
//        this.category=category;
//    }

    public CategoryDaoImpl(int categoryId){
        this.categoryId=categoryId;
    }

//    public void setCategoryId(int categoryId) {
//        this.categoryId = categoryId;
//    }

    public int getCategoryId() {
        return categoryId;
    }

    public CategoryDaoImpl(){
        System.out.println("==CategoryDaoImpl 构造方法==");
    }
    /**
     * bean的一个初始化
     */
    @PostConstruct //基于注解配置bean的生命周期
    public void init(){
        System.out.println("==CategoryDaoImpl init==");
    }

    /**
     * bean的一个销毁
     */
    @PreDestroy
    public void destory(){
        System.out.println("==CategoryDaoImpl destory==");
    }

    @Override
    public Category findCategoryById(int categoryId) {
//       SqlSession sqlSession= MyBatisUtil.getSqlSession();
       return sqlSession.selectOne("com.neuedu.dao.ICategoryDao.findCategoryById",categoryId);
    }

    @Override
    public List<Category> findSubCategoryByCategoryId(int categoryId) {
//        SqlSession sqlSession= MyBatisUtil.getSqlSession();
        return sqlSession.selectList("com.neuedu.dao.ICategoryDao.findSubCategoryByCategoryId",categoryId);
    }

    @Override
    public int addCategory(int parent_id, String categoryName) {
//        SqlSession sqlSession= MyBatisUtil.getSqlSession();
        Map<String,Object> map=new HashMap<>();
        map.put("parent_id",parent_id);
        map.put("categoryName",categoryName);
        int result=sqlSession.insert("com.neuedu.dao.ICategoryDao.addCategory",map);
        return result;

    }

    @Override
    public int updateCategoryByCategoryId(Category category) {
        return sqlSession.update("com.neuedu.dao.ICategoryDao.updateCategoryByCategoryId",category);
    }
}
