package com.chenhao.controller;

import com.alibaba.fastjson.JSONObject;
import com.chenhao.entity.UserDTO;
import com.chenhao.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 *  用户基本操作
 */
@Api(tags="用户定义API",description = "通过用户信息来查看swagger在线文档")
@RestController
public class UserController {
    private Logger logger= Logger.getLogger(UserController.class);
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private UserService userService;
    @ApiOperation(value="查询用户列表")
     @RequestMapping("/user/findAll.do")
    public String findAll(){
         List<UserDTO> list=userService.findAll();
         String result=null;
         if(list==null){
             result=toResult(new ArrayList<UserDTO>(),"无记录",0);
         }else{
             result=toResult(list,"请求成功",0);
         }
         return result;
     }
    @ApiOperation(value="查询用户详情")
    @ApiImplicitParam(name = "id", value = "根据id查询用户详情", required = true, dataType = "String")
    @RequestMapping("/user/findById.do")
     public String findById(@PathVariable String id){
         UserDTO user=userService.findById(id);
         String result=null;
         if(user==null){
            result=toResult(new UserDTO(),"无记录",0);
         }else{
            result=toResult(user,"请求成功",0);
         }
        return result;
     }
    @ApiOperation(value="新增用户")
    @ApiImplicitParam(name = "userBean", value = "新增用户", required = true, dataType = "UserDTO")
    @RequestMapping("/user/insert.do")
     public String insert(@RequestBody UserDTO userBean){
         UserDTO user=new UserDTO();
         user.setId(UUID.randomUUID().toString());
         user.setAge(Integer.valueOf(userBean.getAge()));
         user.setName(userBean.getName());
         user.setRealName(userBean.getRealName());
         user.setPwd(userBean.getPwd());
         user.setGender(userBean.getGender());
         user.setIdNo(userBean.getIdNo());
         user.setPhone(userBean.getPhone());
         userService.insert(user);
         return toResult(null,"新增成功",0);
     }
    @ApiOperation(value="更新用户信息")
    @ApiImplicitParam(name = "userBean", value = "修改用户信息", required = true, dataType = "UserDTO")
    @RequestMapping("/user/update.do")
    public String update(@RequestBody UserDTO userBean){
        UserDTO user=new UserDTO();
        user.setId(userBean.getId());
        user.setAge(Integer.valueOf(userBean.getAge()));
        user.setName(userBean.getName());
        user.setRealName(userBean.getRealName());
        user.setPwd(userBean.getPwd());
        user.setGender(userBean.getGender());
        user.setIdNo(userBean.getIdNo());
        user.setPhone(userBean.getPhone());
        userService.update(user);
        return toResult(null,"修改成功",0);
    }
    @ApiOperation(value="删除用户信息")
    @ApiImplicitParam(name = "id", value = "删除用户信息", required = true, dataType = "String")
    @RequestMapping("/user/delete.do")
    public String delete(@PathVariable String id){
        userService.deleteOne(id);
        return toResult(id,"删除成功",0);
    }

    @ApiOperation(value="获取其他信息", notes="获取其他信息")
    @ApiImplicitParam(name="id", value="用户ID", required=true, dataType="String")
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public Map<String,Object> getInfo(@PathVariable String id) {
        UserDTO user=userService.findById(id);
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("user", user);
        map.put("code", "0");
        map.put("msg", "请求成功");
        return map;
    }


    public String toResult(Object data,String msg,int code){
         JSONObject json=new JSONObject();
         json.put("code",code);
         json.put("msg",msg);
         json.put("data",data);
         json.put("server","A");
         logger.info("请求结果:::"+json.toJSONString());
         String desc=discoveryClient.description();
         json.put("description",desc);
         return json.toJSONString();
     }



}
