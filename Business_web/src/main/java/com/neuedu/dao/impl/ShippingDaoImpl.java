package com.neuedu.dao.impl;

import com.neuedu.dao.IShippingDao;
import com.neuedu.pojo.Shipping;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShippingDaoImpl implements IShippingDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public Integer addShipping(Shipping shipping) {
        return sqlSession.insert("com.neuedu.dao.IShippingDao.addShipping", shipping);
    }

    @Override
    public Integer deleteShipping(Integer shippingId, Integer userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("shippingId", shippingId);
        return sqlSession.delete("com.neuedu.dao.IShippingDao.deleteShipping", map);
    }

    @Override
    public Integer updateShipping(Integer userId, Integer shippingId, Shipping shipping) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("shippingId", shippingId);
        map.put("shipping", shipping);
        return sqlSession.update("com.neuedu.dao.IShippingDao.updateShipping", map);
    }

    @Override
    public Shipping selectShipping(Integer userId, Integer shippingId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("shippingId", shippingId);
        return sqlSession.selectOne("com.neuedu.dao.IShippingDao.selectShipping", map);
    }

    @Override
    public List<Shipping> shippingList(Integer userId, Integer pageNo, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("offSize", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);
        return sqlSession.selectList("com.neuedu.dao.IShippingDao.shippingList", map);
    }
}
