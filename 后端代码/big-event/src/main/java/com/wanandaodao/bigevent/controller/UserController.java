package com.wanandaodao.bigevent.controller;

import com.wanandaodao.bigevent.pojo.Result;
import com.wanandaodao.bigevent.pojo.User;
import com.wanandaodao.bigevent.service.UserService;
import com.wanandaodao.bigevent.utils.JwtUtil;
import com.wanandaodao.bigevent.utils.Md5Util;
import com.wanandaodao.bigevent.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username, String password){
        //查询用户名是否存在
        User u = userService.selectByUsername(username);
        if(u==null){
            userService.register(username,password);
            return Result.success();
        }else{
            return Result.error("用户名已存在");
        }
    }

    @PostMapping("login")
    public Result login(String username, String password){
        User user = userService.selectByUsername(username);
        if(user!=null){
            password = Md5Util.getMD5String(password);
            if(password.equals(user.getPassword())){
                Map<String,Object> claims = new HashMap<>();
                claims.put("username",username);
                claims.put("id",user.getId());
                String token = JwtUtil.getToken(claims);
                return Result.success(token);
            }else{
                return Result.error("用户名或密码错误");
            }
        }else{
            return Result.error("用户名不存在");
        }
    }

    @GetMapping("/userInfo")
    public Result<User> getUserInfo(){
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User user = userService.getUserInfoByUsername(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam String avatarUrl){
        Map<String,Object> map= ThreadLocalUtil.get();
        int id = (int)map.get("id");
        userService.updateAvatar(avatarUrl,id);
        return Result.success();
    }

    @PutMapping("/password")
    public Result updatePassword(@RequestBody User user){
        String password = user.getPassword();
        password = Md5Util.getMD5String(password);
        user.setPassword(password);
        userService.updatePassword(user);
        return Result.success();
    }
}
