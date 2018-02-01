package com.pages.test.service;

import com.pages.test.model.TestBean;

/**
 * Created by kokoyuo on 2018/1/23.
 */
public interface UserService
{
    TestBean getUserInfoByName(String name);

    TestBean getUserInfoByNameAndId(String name,Integer id);
}
