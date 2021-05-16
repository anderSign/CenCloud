package com.testRestful.controller;

import com.testRestful.entity.TUser;
import com.testRestful.service.TUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * (TUser)表控制层
 *
 * 本控制层是最为原始的控制层因此我将会把这个作为我后续学习springcloud的基石。
 *
 * @author dan
 * @since 2021-05-07 09:32:03
 */
@RestController
@RequestMapping("tUser")
public class TUserController {
    private static Logger logger= LoggerFactory.getLogger(TUserController.class);

    /**
     * 服务对象
     */
    @Resource
    private TUserService tUserService;

    /*
    下面是极简式开发风格
     */
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public TUser selectOne(@PathVariable("id") Integer id) {
        return this.tUserService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @return 单条数据
     */
    @GetMapping
    public List<TUser> selectAll() {
        return this.tUserService.queryAllByLimit(0,Integer.MAX_VALUE);
    }

    /**
     * 通过主键删除一条数据
     *
     * 注意：这是特殊访问，不能够通过地址栏或者from表单来请求，因此只能通过ajax或者特殊工具/组件才可以
     * @return 单条数据
     */
    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable("id") int id){
        return (tUserService.deleteById(id))?"ok":"no";
    }

    /**
     * 添加一条数据
     *
     * @return 单条数据
     */
    @PostMapping
    public TUser saveOne(TUser tUser){
        return tUserService.insert(tUser);
    }

    /**
     * 更新一条数据
     *
     * 注意：这是特殊访问，不能够通过地址栏或者from表单来请求，因此只能通过ajax或者特殊工具/组件才可以
     * @return 单条数据
     * 前台传输的是json数据的字符串就使用@RequestBody
     * 前台传输的是json数据就使用@RequestParam
     */
    @PatchMapping
    public TUser updateOne(@RequestBody TUser tUser){
        return tUserService.update(tUser);
    }

    /**
     * 保存文件类型的数据
     *
     * 注意：文件是特殊访问信息，一般使用post来传输
     * @return 单条数据
     */
    @PostMapping("/file")
    public String uploadOne(Object dataSource, HttpServletRequest request, String name,MultipartFile resource) throws ServletException, IOException {
        //注意这个part会检查你上传表单数据的enctype类型是不是:"multipart/form-data"或者"multipart/mixed stream"如果是则继续，如果不是则报错。
        Collection<Part> parts = request.getParts();
        Part part=null;
        for (Part partInfo : parts) {
            System.out.println(part);
            if (partInfo.getName().equals("resource")) part= partInfo;
        }
        //一种打印日志的方式，下面两个一个是原生tomcat中自带的，另一个是spring框架自带的
        logger.debug("***文件信息part打印:\n文件名称:[{}]\n文件Key:[{}]\n文件大小:[{}byte]",part.getSubmittedFileName(),part.getName(),part.getSize());
        logger.debug("***MultipartFile:\n文件名称:[{}]\n文件Key:[{}]\n文件大小:[{}]",resource.getOriginalFilename(),resource.getName(),resource.getSize());
        return "ok";
    }

}
