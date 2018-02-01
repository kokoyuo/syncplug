package com.pages.sdrtest.controller;

import com.alibaba.fastjson.JSON;
import com.pages.test.model.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by kokoyuo on 2018/1/29.
 */
@Controller
@RequestMapping("/sdrtest")
public class SDRController
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ResponseBody
    @RequestMapping("/testopls")
    public String testOpls(String name)
    {
        TestBean testBean = new TestBean();
        testBean.setId(1);
        testBean.setCreateAt(new Date());
        testBean.setInforma("this is a data for spring data redis template test !");
        testBean.setName(name);
        ListOperations lo =stringRedisTemplate.opsForList();
        lo.leftPush("testopls", JSON.toJSONString(testBean));
        return "test for redis opls";
    }

    @ResponseBody
    @RequestMapping("/testoplsb")
    public String testOplsb(String name)
    {
        TestBean testBean = new TestBean();
        testBean.setId(1);
        testBean.setCreateAt(new Date());
        testBean.setInforma("this is a data for spring data redis template test !");
        testBean.setName(name);
        BoundListOperations blo =stringRedisTemplate.boundListOps("lxw");
        blo.leftPush(JSON.toJSONString(testBean));
        return "test for redis opls";
    }

    @ResponseBody
    @RequestMapping("/testset")
    public String testset(String name,String value)
    {
        TestBean testBean = new TestBean();
        testBean.setId(1);
        testBean.setCreateAt(new Date());
        testBean.setInforma("this is a data for spring data redis template test !");
        testBean.setName(name);
        BoundSetOperations blo =stringRedisTemplate.boundSetOps("set");
        blo.add(value);
        return "test for redis opls";
    }

    @ResponseBody
    @RequestMapping("/testhash")
    public String testhash(String name,String value)
    {
        TestBean testBean = new TestBean();
        testBean.setId(1);
        testBean.setCreateAt(new Date());
        testBean.setInforma("this is a data for spring data redis template test !");
        testBean.setName(name);
        BoundHashOperations blo =stringRedisTemplate.boundHashOps("hash");
        blo.put(name,value);
        return "test for redis opls";
    }
}
