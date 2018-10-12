package com.neuedu.Service.impl;

import com.neuedu.Service.IShippingService;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.IShippingDao;
import com.neuedu.pojo.Shipping;
import com.neuedu.vo.ShippingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    private IShippingDao iShippingDao;

    @Override
    public ServerResponse addShipping(Shipping shipping) {
        //参数校验
        int result = iShippingDao.addShipping(shipping);
        if (result > 0) {
            return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), shipping.getId());
        }
        return ServerResponse.createServerResponse(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMsg());

    }

    @Override
    public ServerResponse deleteShipping(Integer userId, Integer shippingId) {
        if(userId != null && shippingId !=null){
            int result = iShippingDao.deleteShipping(shippingId,userId);
            if(result > 0){
                return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
            }
        }
        return ServerResponse.createServerResponse(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
    }

    @Override
    public ServerResponse updateShipping(Integer userId, Integer shippingId, Shipping shipping) {
        if(userId != null && shippingId !=null){
            int result = iShippingDao.updateShipping(userId,shippingId,shipping);
            if(result > 0){
                return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
            }
        }
        return ServerResponse.createServerResponse(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
    }

    @Override
    public ServerResponse<Shipping> selectShipping(Integer userId, Integer shippingId) {
        if(userId != null && shippingId !=null){
            Shipping shipping = iShippingDao.selectShipping(userId,shippingId);
            if(shipping != null){
                return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),shipping);
            }
        }
        return ServerResponse.createServerResponse(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
    }

    @Override
    public ServerResponse<ShippingVO> shippingList(Integer userId, Integer pageNo, Integer pageSize) {
        List<Shipping> shippingList = iShippingDao.shippingList(userId,pageNo,pageSize);
        if(shippingList!=null && shippingList.size()>0){
            ShippingVO shippingVO = toShippingVO(shippingList,pageNo,pageSize);
            return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),shippingVO);
        }
        return ServerResponse.createServerResponse(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
    }

    private ShippingVO toShippingVO(List<Shipping> shippingList ,Integer pageNo ,Integer pageSize){
        ShippingVO shippingVO = new ShippingVO();

        shippingVO.setPageNo(pageNo);
        shippingVO.setPageSize(pageSize);
        shippingVO.setOrderBy("根据收货人姓名排序！");
        shippingVO.setTotal(1);
        shippingVO.setShippingList(shippingList);

        shippingVO.setFirstPage(1);
        Integer prePage = pageNo-1 ;
        Integer nextPage = pageNo+1 ;
        if(prePage<0){
            prePage = 0 ;
        }
        shippingVO.setPrePage(prePage);
        shippingVO.setNextPage(0);
        shippingVO.setLastPage(0);

        return shippingVO ;
    }


}
