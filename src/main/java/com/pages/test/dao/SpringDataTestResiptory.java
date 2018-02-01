package com.pages.test.dao;

import com.pages.test.model.TestBean;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kokoyuo on 2017/12/26.
 */
public interface SpringDataTestResiptory extends JpaRepository<TestBean,Integer>
{
    TestBean findByName(String name);

    TestBean findByNameAndId(String name,Integer id);
}
