package com.neuedu;

import com.neuedu.dao.IProductDao;
import com.neuedu.dao.impl.ProductDaoImpl;
import com.neuedu.pojo.Product;
import org.junit.Test;

public class ProductDaoTest {

    @Test
    public void testFindProductById(){
        IProductDao productDao=new ProductDaoImpl();
        Product product=productDao.findProductById(10000);
        System.out.println(product);
    }

}
