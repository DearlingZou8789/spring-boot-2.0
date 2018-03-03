package com.xm.spring.demo.controller;

import com.xm.spring.demo.domain.User;
import com.xm.spring.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    /**
     * 保护用户
     * @param olduser 用户名
     * @return 返回保存的用户
     * @consult
     * 1、如果依赖spring-boot-starter-web，RequestParam可以正常使用
     * 2、但是如果依赖spring-boot-starter-webflux，就会一直报Required String parameter 'name' is not present
     *
     * 总结：
     * 1、spring-boot-starter-web, 采用@RequestParam就可以通过paw中From URL-Encoded
     * 2、spring-boot-starter-webflux,采用@RequestBody,同时跟Struts2中一样通过模型传递参数
     */
    @PostMapping(value = "/person/save")
    public User save(@RequestBody User olduser){
        User user = new User();
        user.setName(olduser.getName());
        if (userRepository.save(user)){
            System.out.print("用户保存成功");
        }
        return user;
    }

    @GetMapping("/api/person/1.0/find/all")
    public Collection<User> findAll(){
        return userRepository.findAll();
    }
}
