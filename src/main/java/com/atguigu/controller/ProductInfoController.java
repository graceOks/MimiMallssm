package com.atguigu.controller;

import com.atguigu.pojo.ProductInfo;
import com.atguigu.pojo.vo.ProductInfoVO;
import com.atguigu.service.ProductInfoService;
import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("prod")
public class ProductInfoController {

    @Autowired
    ProductInfoService productInfoService;

    private String originalFilename;

    //显示全部商品不部分
    @RequestMapping("/getAll.action")
    public String getAll(HttpServletRequest request) {
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list", list);
        return "product";
    }

    //每页显示的记录数
    static final int PAGE_SIZE = 5;

    //显示第1页的5条记录
    @RequestMapping("/split.action")
    public String split(HttpServletRequest request) {
        //得到第1页的数据
        PageInfo info = productInfoService.splitPage(1, PAGE_SIZE);
        request.setAttribute("info", info);
        return "product";
    }

    //ajax分页翻页处理
    @ResponseBody
    @RequestMapping("/ajaxSplit.action")
    public void ajaxSplit(int page, HttpSession httpSession) {
        System.out.println("Ajax...");
        //取得当前page参数的页面的数据
        PageInfo info = productInfoService.splitPage(page, PAGE_SIZE);
        httpSession.setAttribute("info", info);
    }

    /*
    @ResponseBody
    @RequestMapping("/ajaxImg.action")
    public Object ajaxImg(@RequestParam("pimage")CommonsMultipartFile commonsMultipartFile,HttpServletRequest request,HttpSession session){
        System.out.println("AjaxImage...");

        FileItem fileItem = commonsMultipartFile.getFileItem();
        System.out.println("fileItem = " + fileItem);
        //获取文件上传目录
        String realPath = request.getSession().getServletContext().getRealPath("");
        System.out.println("fileItem.getName() = 获取上传图片的文件名" + fileItem.getName());
    }*/

    @ResponseBody
    @RequestMapping("/ajaxImg.action")
    public Object ajaxImg(MultipartFile pimage, HttpServletRequest request) {
        //提取生成文件名UUID+上传图片的后缀.jpg .png
        System.out.println("AjaxImage...");
        //String saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());
        //System.out.println("saveFileName ===>> " + saveFileName);

        //获取原始图片名称
        originalFilename = pimage.getOriginalFilename();
        System.out.println("originalFilename = " + originalFilename);
        //得到项目中图片存储的路径
        String path = request.getServletContext().getRealPath("/image_big");
        //转存 E:\idea\MimiMallssm\images\bf1455vd5554d5d55vg.jpg
        try {
//            pimage.transferTo(new File(path + File.separator + originalFilename));
            //修改后
//            pimage.transferTo(new File(path + File.separator + saveFileName));
            InputStream is = pimage.getInputStream();
            System.out.println(path + "/" + originalFilename);
            OutputStream os = new FileOutputStream(new File(path + "/" + originalFilename));
            int content = is.read();
            while (content != -1) {
                // 使用输出流将读取到的content写入文件
                os.write(content);
                content = is.read();
            }
            os.flush();
            os.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("path = " + path);

        //返回客户端JSON对象,封装图片的路径,为了在页面实现立即回显
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imgurl", originalFilename);
        System.out.println("jsonObject = " + jsonObject);
        System.out.println("saveFileName = " + originalFilename);
        return jsonObject.toString();


    }

    //@ResponseBody
    @RequestMapping("/save.action")
    public String save(ProductInfo productInfo,HttpServletRequest request) {
        System.out.println("save...");
        productInfo.setpImage(originalFilename);
        productInfo.setpDate(new Date());
        //productInfo对象中用表单提交上来的5个数据，用异步ajax提交上来的名称数据，有上架时间数据
        int num=-1;
        try {
            num=productInfoService.save(productInfo);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        if (num>0){
            request.setAttribute("msg", "增加成功!");
        }else {
            request.setAttribute("msg", "增加失败!");
        }
        //清空saveFileName变量中的内容,为了下次增加或修改的异步ajax的上传处理
        originalFilename ="";

        //增加成功后应该重新访问数据库，所以跳转到分页显示的action上
        //转发到分页请求页面
        return "forward:/prod/split.action";
    }

    @RequestMapping("/one.action")
    public String one(int pid, Model model){
        ProductInfo productInfo=productInfoService.getByID(pid);
        model.addAttribute("prod",productInfo);
        return "update";
    }



    @RequestMapping("/update.action")
    public String update(ProductInfo productInfo,HttpServletRequest request){
        //因为ajax的异步图片上传,如果有上传过，
        //则saveFileName里有上传上来的图片的名称，
        //如果没有使用异步ajax上传过图片,则originalFilename="",实体类productInfo使用隐藏表单域提供上来的pImage原始图片的名称
        if (!originalFilename.equals("")) {
            productInfo.setpImage(originalFilename);
        }
        //完成更新处理
        int num=-1;
        try {
            num=productInfoService.update(productInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (num>0){
            //此时说明更新成功
            request.setAttribute("msg", "更新成功!");
        }else {
            //此时说明更新失败
            request.setAttribute("msg", "更新失败!");
        }
        //处理完成更新后,originalFilename里面有可能有数据,
        // 而下一次更新时要使用这个变量做为判断的依据,就会出错,所以必须清空originalFilename
        originalFilename="";
        return "forward:/prod/split.action";
    }

    //单个删除功能
    @RequestMapping("/delete.action")
    public String delete(int pid,HttpServletRequest request){
        System.out.println("delete...");
        try {
            int deleteByID=productInfoService.deleteByID(pid);
            if (deleteByID>0){
                request.setAttribute("msg","删除成功");
            }else {
                request.setAttribute("msg","删除失败");
            }
        } catch (Exception e) {
            request.setAttribute("msg","商品删除出错!");
        }
        //删除结束后跳转到分页显示
        return "forward:/prod/deleteAjaxSplit.action";
    }


    //单个删除 Ajax请求
    @ResponseBody
    @RequestMapping(value = "/deleteAjaxSplit.action",produces = "text/html;charset=utf-8")
    public Object deleteAjaxSplit(HttpServletRequest request){
        //取得第1页的数据
        PageInfo pageInfo = productInfoService.splitPage(1, PAGE_SIZE);
        request.getSession().setAttribute("info", pageInfo);
        return request.getAttribute("msg");
    }


    //批量删除功能
    @RequestMapping("/deleteBatch.action")
    //pids="1,4,5" ps[1,4,5]
    public String deleteBatch(String pids,HttpServletRequest request){
        System.out.println("BatchDelete...");
        System.out.println("pids= " + pids);
        //将上传上来的字符串截开,形成商品id的字符数组
        String[] pid = pids.split(",");
        System.out.println("pid= " + pid);

        try {
            int deleteResult = productInfoService.deleteBatch(pid);
            if (deleteResult>0) {
                request.setAttribute("msg", "删除成功");
            }else {
                request.setAttribute("msg", "删除失败");
            }
        } catch (Exception e) {
            request.setAttribute("smg", "商品不可删除!");
        }
        return "forward:/prod/deleteAjaxSplit.action";

    }

    //多条件查询功能实现
    @ResponseBody
    @RequestMapping("/condition.action")
    public void condition(ProductInfoVO vo,HttpSession session){
        System.out.println("condition...");
        System.out.println("vo = " + vo);
        List<ProductInfo> infoList=productInfoService.selectCondition(vo);
        session.setAttribute("list", infoList);
    }


}
