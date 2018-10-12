package com.neuedu.Service.impl;

import com.neuedu.Service.ICartService;
import com.neuedu.businessconst.Const;
import com.neuedu.common.BigDecimalUtils;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.ICartDao;
import com.neuedu.dao.IProductDao;
import com.neuedu.pojo.Cart;
import com.neuedu.pojo.Product;
import com.neuedu.vo.CartProductVO;
import com.neuedu.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService{
    @Autowired
    private ICartDao iCartDao;
    @Autowired
    private IProductDao iProductDao;

    @Override
    public ServerResponse<CartVO> addProductToCart(Integer userid, Integer productid, int count) {
        //1、校验，根据userid和productid查询购物车cart
            Cart cart=iCartDao.findCartByUseridAndProductid(userid,productid);
        //2、cart=null 添加 否则更新
        if(cart==null) {//添加
            Cart cart1=new Cart(userid,productid,count,1);
            iCartDao.addProductToCart(cart1);

        }else {//更新
            cart.setQuantity(cart.getQuantity()+count);
            iCartDao.updateCartByUseridAndProductid(cart);
        }
        CartVO cartVO=getCartVOLimit(userid);
        return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),cartVO);
    }

    @Override
    public ServerResponse<CartVO> findCartByUserid(Integer userid) {
        //1.根据userid查询用户购物车信息
         CartVO cartVO=getCartVOLimit(userid);

        return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),cartVO);

    }

    @Override
    public ServerResponse<CartVO> updateCartByUserAndProductid(Integer userid, Integer productid, Integer quantity) {
        //1.参数非空验证
        if(productid==null||quantity==null){
            return ServerResponse.createServerResponse(ResponseCode.NEED_PRODUCT.getCode(),ResponseCode.NEED_PRODUCT.getMsg());
        }
        if(quantity<1){
            return ServerResponse.createServerResponse(ResponseCode.NO_CUT.getCode(),ResponseCode.NO_CUT.getMsg());
        }
        //2.构造cart
        Cart cart=new Cart();
        cart.setUser_id(userid);
        cart.setProduct_id(productid);
        cart.setQuantity(quantity);
        iCartDao.updateCartByUseridAndProductid(cart);
       CartVO cartVO= getCartVOLimit(userid);
        return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),cartVO);
    }

    @Override
    public ServerResponse<CartVO> deleteProductFromCart(Integer userid, String productIds) {
        //1.参数非空验证
        if(productIds==null||productIds.equals("")){
            return ServerResponse.createServerResponse(ResponseCode.NEED_PRODUCT.getCode(),ResponseCode.NEED_PRODUCT.getMsg());
        }
        //2.productIds
       String[] productidsarry= productIds.split(",");
        List<Integer> productIdsList=new ArrayList<>();
        for(String productidStr:productidsarry){
           Integer productid= Integer.parseInt(productidStr);
           productIdsList.add(productid);
        }
        iCartDao.deleteProduct(productIdsList,userid);
        CartVO cartVO=getCartVOLimit(userid);
        return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),cartVO);
    }

    //选中某个商品
    @Override
    public ServerResponse<CartVO> checkedProductByProductId(Integer userid, Integer productid) {
        //1.参数非空验证
        if(productid==null){
            return ServerResponse.createServerResponse(ResponseCode.NEED_PRODUCT.getCode(),ResponseCode.NEED_PRODUCT.getMsg());
        }
        iCartDao.checkProductByProductId(userid, productid);
        CartVO cartVO=getCartVOLimit(userid);
        return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),cartVO);

    }

    //取消选中某个商品
    @Override
    public ServerResponse<CartVO> uncheckedProductByProductId(Integer userid, Integer productid) {
        //1.参数非空验证
        if(productid==null){
            return ServerResponse.createServerResponse(ResponseCode.NEED_PRODUCT.getCode(),ResponseCode.NEED_PRODUCT.getMsg());
        }
        iCartDao.uncheckProductByProductId(userid, productid);
        CartVO cartVO=getCartVOLimit(userid);
        return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),cartVO);

    }

    @Override
    public ServerResponse<Integer> findProductNum(Integer userid) {
        Integer count = 0;
        List<Cart> list = iCartDao.findCartByUserid(userid);
        for (Cart c:list) {
            if(c.getChecked() == 1){
                count += c.getQuantity();
            }
        }
        return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),count);

    }

    @Override
    public ServerResponse<CartVO> checkAll(Integer userid) {
        List<Cart> cartVOList=iCartDao.findCartByUserid(userid);
        for(Cart c:cartVOList){
            if(c.getChecked()== Const.CHECKENUM.UNCHECK.getCode()){
                checkedProductByProductId(userid,c.getProduct_id());
            }
        }
        return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
    }

    @Override
    public ServerResponse<CartVO> uncheckAll(Integer userid) {
        List<Cart> cartVOList=iCartDao.findCartByUserid(userid);
        for(Cart c:cartVOList){
            if(c.getChecked()== Const.CHECKENUM.CHECK.getCode()){
                uncheckedProductByProductId(userid,c.getProduct_id());
            }
        }
        return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
    }

    public CartVO getCartVOLimit(Integer userid){
        CartVO cartVO= new CartVO();

        //1、根据userid查询购物车商品集合
        List<Cart> cartList=iCartDao.findCartByUserid(userid);

        //2、将cartList转成List<CartProductVO>
        List<CartProductVO> cartProductVOList=new ArrayList<>();
        BigDecimal carttotalPrice=new BigDecimal(0);
        if(cartList!=null&&cartList.size()>0){
            for(Cart cart:cartList){
                CartProductVO cartProductVO=new CartProductVO();
                Product product= iProductDao.findProductById(cart.getProduct_id());

                cartProductVO.setUser_id(userid);
                cartProductVO.setProductid(cart.getProduct_id());
                cartProductVO.setName(product.getName());
                cartProductVO.setSubtitle(product.getSubtitle());
                cartProductVO.setMain_images(product.getMain_images());
                cartProductVO.setProductprice(product.getPrice());
                //设置商品总价格
                cartProductVO.setTotalPrice(BigDecimalUtils.multi(product.getPrice(),new BigDecimal(cart.getQuantity())));

                if(cart.getChecked()==Const.CHECKENUM.CHECK.getCode()){
                    carttotalPrice=BigDecimalUtils.add(carttotalPrice,cartProductVO.getTotalPrice());
                }

                //校验库存
                if(product.getStock()>cart.getQuantity()){
                    //库存充足
                    cartProductVO.setLimitQuantity(Const.STOCK.LIMIT_NUM_SUCCESS.getStockdesc());
                }else{
                    //库存不足
                    cartProductVO.setLimitQuantity(Const.STOCK.LIMIT_NUM_FAIL.getStockdesc());
                    //更新购物车中商品购买数量
                    Cart cart1=new Cart();
                    cart1.setUser_id(userid);
                    cart1.setQuantity(product.getStock());
                    cart1.setProduct_id(product.getId());
                    cart.setQuantity(product.getStock());
                    iCartDao.updateCartByUseridAndProductid(cart1);
                }
                cartProductVO.setQuantity(cart.getQuantity());
                cartProductVO.setStatus(product.getStatus());
                cartProductVO.setCartid(cart.getId());
                // 是否被勾选
                cartProductVO.setChecked(cart.getChecked());
                cartProductVOList.add(cartProductVO);


            }
        }

        cartVO.setCartProductVOList(cartProductVOList);
        cartVO.setIsallchecked(iCartDao.isCheckAll(userid)==0);
        cartVO.setTotalPrice(carttotalPrice);
        return cartVO;
    }



}
