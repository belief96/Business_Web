package com.neuedu.dao.impl;

import com.neuedu.dao.IOrderDao;
import com.neuedu.pojo.Order;
import com.neuedu.pojo.OrderItem;
import com.neuedu.pojo.PayInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements IOrderDao {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public Order findOrderByOrderNoAndUserid(Long orderNo, Integer userid) {
        Map<String, Object> map = new HashMap<>();
        map.put("userid", userid);
        map.put("orderno", orderNo);
        return sqlSession.selectOne("com.neuedu.dao.IOrderDao.findOrderByOrderNoAndUserid", map);
    }

    @Override
    public Order findOrderByOrderNo(Long orderNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderno", orderNo);
        return sqlSession.selectOne("com.neuedu.dao.IOrderDao.findOrderByOrderNo", map);

    }

    @Override
    public List<OrderItem> findOrderItemsByOrderNo(Long orderNo) {

        return sqlSession.selectList("com.neuedu.dao.IOrderDao.findOrderItemsByOrderNo", orderNo);

    }

    @Override
    public Integer updateOrderStatusByOrderNo(Integer status, Long orderno) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderno", orderno);
        map.put("status", status);
        return sqlSession.selectOne("com.neuedu.dao.IOrderDao.updateOrderStatusByOrderNo", map);

    }

    @Override
    public Integer addPayInfo(PayInfo payInfo) {

        return sqlSession.selectOne("com.neuedu.dao.IOrderDao.addPayInfo", payInfo);

    }

    @Override
    public Integer addOrder(Order order) {
        return sqlSession.selectOne("com.neuedu.dao.IOrderDao.addOrder", order);

    }

    @Override
    public Integer barchInsertOrderItem(List<OrderItem> orderItemList) {
        return sqlSession.selectOne("com.neuedu.dao.IOrderDao.barchInsertOrderItem", orderItemList);
    }

    @Override
    public Integer updateOrderStatusByOrderNoAndUserid(Integer status, Long orderno, Integer userid) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderno", orderno);
        map.put("status", status);
        map.put("userid",userid);
        return sqlSession.update("com.neuedu.dao.IOrderDao.updateOrderStatusByOrderNoAndUserid", map);

    }

    @Override
    public Integer sendOrder(Order order) {

        return sqlSession.update("com.neuedu.dao.IOrderDao.sendOrder", order);

    }

    @Override
    public List<Order> orderList(Integer userId, Integer pageNo, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("offSize", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);
        return sqlSession.selectList("com.neuedu.dao.IOrderDao.orderList", map);
    }

    @Override
    public Long totalPage(Integer userId) {

        return sqlSession.selectOne("com.neuedu.dao.IOrderDao.totalPage",userId);
    }

    @Override
    public List<OrderItem> findOrderItemByOrderNo(Long orderNo, Integer userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderNo", orderNo);
        map.put("userId",orderNo);
        return sqlSession.selectOne("com.neuedu.dao.IOrderDao.findOrderByOrderNo", map);
    }
}
