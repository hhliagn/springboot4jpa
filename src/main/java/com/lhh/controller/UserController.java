package com.lhh.controller;

import com.lhh.common.PageResult;
import com.lhh.common.Result;
import com.lhh.common.StatusCode;
import com.lhh.entity.User;
import com.lhh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lhh
 * @date 2019/5/26 11:43
 */
@RestController
@CrossOrigin  //跨域是什么？浏览器从一个域名的网页去请求另一个域名的资源时，域名、端口、协议任一不同，都是跨域 。
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/queryAll")
    public Result queryAll(){
        //throw new RuntimeException("ccc");  //测试异常是否能被正常捕获
        return new Result(StatusCode.OK, "查询成功", userService.findAll());
    }

    /**
     * 查询一个
     *  接收参数
     *      @RequestParam: 接收查询参数    /label?id=10
     *      @PathVariable: 接收路径参数    /label/10
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result queryOne(@PathVariable int id){
        return new Result(StatusCode.OK, "查询成功", userService.findOne(1));
    }

    /**
     * 添加
     *  @RequestBody： 把请求的json字符串转换为Java对象
     *  @ResponseBody: 把方法的Java返回对象转换json字符串
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody User user){
        userService.add(user);
        return new Result(StatusCode.OK, "新增成功");
    }

    //更新的时候传过来的对象就是带id的, 所以不用再传一次id
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody User user){
        userService.update(user);
        return new Result(StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable int id){
        userService.deleteById(id);
        return new Result(StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result search(@RequestBody Map searchMap){
        return new Result(StatusCode.OK, "条件查询成功",
                userService.findSearch(searchMap));
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result search(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size){
        Page<User> page1 = userService.findSearch(searchMap, page, size);
        return new Result(StatusCode.OK, "条件+分页查询成功",
                new PageResult<>(page1.getTotalElements(), page1.getContent()));
    }
}
