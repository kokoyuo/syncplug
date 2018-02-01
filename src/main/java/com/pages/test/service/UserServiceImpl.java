package com.pages.test.service;

import com.pages.test.dao.SpringDataTestResiptory;
import com.pages.test.model.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kokoyuo on 2018/1/23.
 */
@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private SpringDataTestResiptory resiptory;

    @Override
    public TestBean getUserInfoByName(String name)
    {
        return resiptory.findByName(name);
    }

    @Override
    public TestBean getUserInfoByNameAndId(String name, Integer id)
    {
        return resiptory.findByNameAndId(name, id);
    }
}
