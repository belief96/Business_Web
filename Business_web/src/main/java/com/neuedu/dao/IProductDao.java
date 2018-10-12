package com.neuedu.dao;

import com.neuedu.pojo.Product;
import com.neuedu.vo.PageModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface IProductDao {

    /**
     * 根据商品id查询商品信息
     */
    public Product findProductById(int product);

    /**
     * 添加商品
     */
    public int addProduct(Product product);

    /**
     * 更新商品
     * @param product
     * @return
     */
    public int updateProduct(Product product);

    /**
     * 分页查询商品数据
     * @param pageNo 查询第几页
     * @param pageSize  每页查询的数量
     * @return
     */
    public List<Product> findProductByPageNo(Integer pageNo,Integer pageSize);

    /**
     * 查询总的记录数
     */
    public Long findTotalRecord();

    /**
     * 后台商品搜索接口
     */
    public List<Product> findPrdouctByPrdouctIdOrProductName(Integer productId,String productName,Integer pageNo,Integer pageSize);

    /**
     * 根据商品id或商品名称获取商品总数量
     */
    public Long findTotalRecordByIdOrByName(Integer productId,String productName);

    /**
     * 根据productid查询在售商品详细
     */
    public Product findProductByIdAndOnline(Integer productid);

    /*
    根据类别集合和商品名称查询商品信息
     */
    public List<Product> findProductBycategoryIdsAndProductName(Set<Integer> categoryIds, String productName, Integer pageNo, Integer pageSize,int orderby);

    /**
     * 根据类别id或商品名称获取商品总数量
     */
    public Long findTotalRecordByIdAndByName(Set<Integer> categoryIds, String productName);

}
