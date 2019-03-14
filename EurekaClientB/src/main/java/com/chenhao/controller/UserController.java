package com.chenhao.controller;

import com.alibaba.fastjson.JSONObject;
import com.chenhao.entity.UserDTO;
import com.chenhao.service.UserService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *  用户基本操作
 */
@RestController
public class UserController {
    private Logger logger= Logger.getLogger(UserController.class);
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private UserService userService;

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
    @RequestMapping("/user/findById.do")
     public String findById(HttpServletRequest request){
         String id=request.getParameter("id");
         UserDTO user=userService.findById(id);
         String result=null;
         if(user==null){
            result=toResult(new UserDTO(),"无记录",0);
         }else{
            result=toResult(user,"请求成功",0);
         }
        return result;
     }
    @RequestMapping("/user/insert.do")
     public String insert(HttpServletRequest request){
         String name=request.getParameter("name");
         String pwd=request.getParameter("pwd");
         String realName=request.getParameter("realName");
         String age=request.getParameter("age");
         String idNo=request.getParameter("idNo");
         String phone=request.getParameter("phone");
         String gender=request.getParameter("gender");
         UserDTO user=new UserDTO();
         user.setId(UUID.randomUUID().toString());
         user.setAge(Integer.valueOf(age));
         user.setName(name);
         user.setRealName(realName);
         user.setPwd(pwd);
         user.setGender(gender);
         user.setIdNo(idNo);
         user.setPhone(phone);
         userService.insert(user);
         return toResult(null,"新增成功",0);
     }

    @RequestMapping("/user/update.do")
    public String update(HttpServletRequest request){
         String id=request.getParameter("id");
        String name=request.getParameter("name");
        String pwd=request.getParameter("pwd");
        String realName=request.getParameter("realName");
        String age=request.getParameter("age");
        String idNo=request.getParameter("idNo");
        String phone=request.getParameter("phone");
        String gender=request.getParameter("gender");
        UserDTO user=new UserDTO();
        user.setId(id);
        user.setAge(Integer.valueOf(age));
        user.setName(name);
        user.setRealName(realName);
        user.setPwd(pwd);
        user.setGender(gender);
        user.setIdNo(idNo);
        user.setPhone(phone);
        userService.update(user);
        return toResult(null,"修改成功",0);
    }
    @RequestMapping("/user/delete.do")
    public String delete(HttpServletRequest request){
        String id=request.getParameter("id");
        userService.deleteOne(id);
        return toResult(id,"删除成功",0);
    }

     public String toResult(Object data,String msg,int code){
         JSONObject json=new JSONObject();
         json.put("code",code);
         json.put("msg",msg);
         json.put("data",data);
         json.put("server","B");
         logger.info("请求结果:::"+json.toJSONString());
         return json.toJSONString();
     }


}
