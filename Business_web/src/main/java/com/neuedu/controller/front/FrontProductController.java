package com.neuedu.controller.front;


import com.neuedu.Service.IProductService;
import com.neuedu.common.ServerResponse;
import com.neuedu.vo.PageModel;
import com.neuedu.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class FrontProductController {
    @Autowired
    IProductService productService;

    /**
     * 查新商品详情
     */
    @RequestMapping(value = "/find")
    public ServerResponse<ProductVO> detail(Integer productId){
      return productService.findProductDetail(productId);
    }

    /**
     * 商品搜索接口
     */
    @RequestMapping(value = "/searchProduct")
    public ServerResponse<PageModel<ProductVO>> searchProduct(@RequestParam(required = false) Integer categoryId,
                                                              @RequestParam(required = false)String productName,
                                                              @RequestParam(defaultValue = "1")Integer pageNo,
                                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                                              @RequestParam(required = false) String orderby){
        return productService.searchProduct(categoryId, productName, pageNo, pageSize,orderby);
    }
}
