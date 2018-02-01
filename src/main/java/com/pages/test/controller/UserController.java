package com.pages.test.controller;

import com.alibaba.fastjson.JSON;
import com.pages.test.model.TestBean;
import com.pages.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;


/**
 * Created by kokoyuo on 2017/12/27.
 */
@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/setuserinfo")
    @ResponseBody
    public String setUserInfo(String name)
    {
        TestBean testBean = new TestBean();
        testBean.setId(1);
        testBean.setCreateAt(new Date());
        testBean.setInforma("this is a data for spring data redis template test !");
        testBean.setName("lxw");
        ValueOperations vop = redisTemplate.opsForValue();
        vop.set(testBean.getId().toString(),testBean);
        return "just for test" + name;
    }

    @RequestMapping("/getuserinfo")
    @ResponseBody
    public String getUserInfo(String name)
    {
        TestBean testBean = new TestBean();
        testBean.setId(2);
        testBean.setCreateAt(new Date());
        testBean.setInforma("this is a data for spring data string redis template test !");
        testBean.setName("lxw");
        ValueOperations vop = stringRedisTemplate.opsForValue();
        vop.set(testBean.getId().toString(), JSON.toJSONString(testBean));
        return "just for test" + name;
    }

    @RequestMapping("/getindex")
    public String getIndex(HttpServletResponse response, HttpServletRequest request)
    {
        String name = request.getParameter("name");
        Integer id = Integer.valueOf(request.getParameter("id"));
        //TestBean user = userService.getUserInfoByName(name);
        TestBean user = userService.getUserInfoByNameAndId(name,id);
        request.setAttribute("user",user);
        Writer writer = null;
        try {
            writer = response.getWriter();
            writer.write(user.getInforma());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "index";
    }

    @RequestMapping("/getindex1")
    public String getIndex1(String name)
    {
        return "classpath:/templates/index.html";
    }

    public RedisTemplate<String, TestBean> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, TestBean> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
