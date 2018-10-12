package com.neuedu.Service.impl;

import com.neuedu.Service.ICategoryService;
import com.neuedu.Service.IProductService;
import com.neuedu.businessconst.Const;
import com.neuedu.common.DateUtils;
import com.neuedu.common.PropertiesUtils;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.ICategoryDao;
import com.neuedu.dao.IProductDao;
import com.neuedu.pojo.Category;
import com.neuedu.pojo.Product;
import com.neuedu.vo.PageModel;
import com.neuedu.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;
    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private ICategoryService iCategoryService;

    @Override
    public ServerResponse<String> addorUpdateProduct(Product product) {
        if(product==null){
            return ServerResponse.createSercerResponse(ResponseCode.NEED_PRODUCT.getCode(),ResponseCode.NEED_PRODUCT.getMsg());
        }

        //sub_images  1.jpg,2.jpg...
        //获取子图
       String subimg= product.getSub_images();
        if(subimg!=null&&!subimg.equals("")){
           String[] images= subimg.split((","));
           if(images!=null&&images.length>0){
               product.setMain_images(images[0]);
           }
        }



        Integer productId=product.getId();
        int result=0;
        if(productId==null){
            //添加
          result= iProductDao.addProduct(product);

        }else{
           result= iProductDao.updateProduct(product);
        }

        if(result>0){
            return ServerResponse.createSercerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
        }else {
            return ServerResponse.createSercerResponse(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
        }

    }

    @Override
    public ServerResponse<String> onlineoroffline(Integer productId, Integer status) {
        if(productId==null){
            return ServerResponse.createSercerResponse(ResponseCode.NEED_PRODUCT.getCode(),ResponseCode.NEED_PRODUCT.getMsg());
        }
        if(status==null){
            return ServerResponse.createSercerResponse(ResponseCode.NEED_PRODUCT_STATUS.getCode(),ResponseCode.NEED_PRODUCT_STATUS.getMsg());
        }
        Product product=new Product();
        product.setId(productId);
        product.setStatus(status);
        int result=iProductDao.updateProduct(product);
        if(result>0){
            return ServerResponse.createSercerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
        }else {
            return ServerResponse.createSercerResponse(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());
        }
    }

    @Override
    public ServerResponse<ProductVO> findProductById(Integer productId) {
        if(productId==null){
            return ServerResponse.createServerResponse(ResponseCode.NEED_PRODUCT.getCode(),ResponseCode.NEED_PRODUCT.getMsg());
        }
        //pojo
        Product product=iProductDao.findProductById(productId);
        //vo
        ProductVO productVO=assembleProduct(product);

        return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),productVO);
    }

    @Override
    public ServerResponse<PageModel<ProductVO>> findProductByPageNo(Integer pageNo, Integer pageSize) {

       List<Product> productList= iProductDao.findProductByPageNo(pageNo,pageSize);
       List<ProductVO> productVOList=new ArrayList<>();
       for(Product product:productList){
           productVOList.add(assembleProduct(product));
       }

        long totalRecord=iProductDao.findTotalRecord();
        long totalpage=(totalRecord%pageSize==0?totalRecord/pageSize:(totalRecord/pageSize+1));
        PageModel<ProductVO> pageModel=new PageModel<>();
        pageModel.setData(productVOList);
        pageModel.setTotalPage(totalpage);

        if(pageNo==1){
            pageModel.setFirst(true);
        }else {
            pageModel.setFirst(false);
        }
        if(pageNo==totalpage){
            pageModel.setLast(true);
        }else {
            pageModel.setLast(false);
        }
        return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),pageModel);


    }

    @Override
    public ServerResponse<PageModel<ProductVO>> findPrdouctByPrdouctIdOrProductName(Integer productId, String productName, Integer pageNo, Integer pageSize) {

        if(productName!=null&&  ! productName.equals("")){
            productName="%"+productName+"%";
        }

       List<Product> productList= iProductDao.findPrdouctByPrdouctIdOrProductName(productId,productName,pageNo,pageSize);
        List<ProductVO> productVOList=new ArrayList<>();
        for(Product product:productList){
            productVOList.add(assembleProduct(product));
        }
        PageModel<ProductVO> productVOPageModel=new PageModel<>();
        long totalRecord=iProductDao.findTotalRecordByIdOrByName(productId,productName);
        long totalpage=(totalRecord%pageSize==0?totalRecord/pageSize:(totalRecord/pageSize+1));
        PageModel<ProductVO> pageModel=new PageModel<>();
        pageModel.setData(productVOList);
        pageModel.setTotalPage(totalpage);

        if(pageNo==1){
            pageModel.setFirst(true);
        }else {
            pageModel.setFirst(false);
        }
        if(pageNo==totalpage){
            pageModel.setLast(true);
        }else {
            pageModel.setLast(false);
        }
        return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),pageModel);
    }

    @Override
    public ServerResponse<String> upload(MultipartFile upload) {


        //重新生成文件名 UUID+扩展名(jsp)
        if (upload != null) {
            //step1:获取原文件扩展名
            String originFilename = upload.getOriginalFilename();

            if (originFilename != null && !originFilename.equals("")) {
                int index = originFilename.lastIndexOf('.');
                String extendname = originFilename.substring(index); //[)

                // step2:生成一个文件名
                String uuid = UUID.randomUUID().toString();
                String newFilename = uuid + extendname;

                //step3:
                String filePath = "D:\\ftpfilter";
                File file = new File(filePath, newFilename);
                try {
                    upload.transferTo(file);
                    return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),newFilename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return ServerResponse.createServerResponse(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMsg());

    }

    @Override
    public ServerResponse<ProductVO> findProductDetail(Integer productId) {

        //1、参数校验
         if(productId==null){
             return ServerResponse.createServerResponse(ResponseCode.NEED_PRODUCT.getCode(),ResponseCode.NEED_PRODUCT.getMsg());
         }

        //2、根据商品id查询商品
         Product product= iProductDao.findProductByIdAndOnline(productId);
         if(product==null){//商品不存在或已经下架
             return ServerResponse.createServerResponse(ResponseCode.PRODUCT_OFFLINE.getCode(),ResponseCode.PRODUCT_OFFLINE.getMsg());

         }

       ProductVO productVO= assembleProduct(product);
        return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),productVO);
    }

    @Override
    public ServerResponse<PageModel<ProductVO>> searchProduct(Integer categoryId, String productName, Integer pageNo, Integer pageSize,String orderBy) {

        //1、参数校验
        if(categoryId==null&&(productName==null||productName.equals(""))){
            return ServerResponse.createServerResponse(Const.ProductCode.ILLEGAL_PARAM.getCode(),Const.ProductCode.ILLEGAL_PARAM.getMsg());
        }
        Set<Integer> set=new HashSet<>();
        //2、如果categoyrId不为空，根据categoryId查询类别信息category
        if(categoryId!=null) {
            Category category = categoryDao.findCategoryById(categoryId);

            //2.1 如果category为空，且productName也为空——>没有该商品
            if (category==null&&(productName==null||productName.equals(""))){
                //没有查询到商品
                PageModel<ProductVO> pageModel=new PageModel<>();
                pageModel.setData(null);
                pageModel.setTotalPage(0L);
                pageModel.setFirst(false);
                pageModel.setLast(false);
                return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),pageModel);
            }
            //2.2 cayegory不为空，需要递归查询子类别list<categoryid> from product categoryid in(categoryid...)
           set= iCategoryService.findAllChildByCategory(set,category.getId());

        }

            //3、判断productName，如果productName不为空，%productName%（模糊查询)
        if(productName!=null&&!productName.equals("")){
            productName="%"+productName+"%";
        }
        //4、判断排序字段是否为空
       int sortnum=0;
        if(orderBy!=null&&!orderBy.equals("")){
           String[] orderbyarr= orderBy.split("_");
           if(orderbyarr!=null&&orderbyarr.length>1){
               String orderby=orderbyarr[1];
               if(orderby.equals("desc")){
                   sortnum=2;
               }else if(orderby.equals("asc")){
                   sortnum=1;
               }
           }
        }
        ///5、调用dao层查询
        List<Product> productList=iProductDao.findProductBycategoryIdsAndProductName(set,productName,pageNo,pageSize,sortnum);
        List<ProductVO> productVOList=new ArrayList<>();
        for(Product product:productList){
            productVOList.add(assembleProduct(product));
        }
        PageModel<ProductVO> productVOPageModel=new PageModel<>();
        long totalRecord=iProductDao.findTotalRecordByIdAndByName(set,productName);
        long totalpage=(totalRecord%pageSize==0?totalRecord/pageSize:(totalRecord/pageSize+1));
        PageModel<ProductVO> pageModel=new PageModel<>();
        pageModel.setData(productVOList);
        pageModel.setTotalPage(totalpage);

        if(pageNo==1){
            pageModel.setFirst(true);
        }else {
            pageModel.setFirst(false);
        }
        if(pageNo==totalpage){
            pageModel.setLast(true);
        }else {
            pageModel.setLast(false);
        }

        return ServerResponse.createServerResponse(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),pageModel);
    }


    public ProductVO assembleProduct(Product product){
        ProductVO productVO=new ProductVO();

        productVO.setName(product.getName());
        productVO.setId(product.getId());
        productVO.setCategory_id(product.getCategory_id());
        productVO.setDetayl(product.getDetayl());
        productVO.setSubtitle(product.getSubtitle());
        productVO.setMain_images(product.getMain_images());
        productVO.setSub_images(product.getSub_images());
        productVO.setStock(product.getStock());
        productVO.setStatus(product.getStatus());
        productVO.setPrice(product.getPrice());

        productVO.setImageHost(PropertiesUtils.getProperty("imageHost"));

        productVO.setCreate_time(DateUtils.dateToString(product.getCreate_time()));
        productVO.setUpdate_time((DateUtils.dateToString(product.getUpdate_time())));

        return productVO;
    }
}
