package com.pages.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by kokoyuo on 2017/12/26.
 */

@Entity
@Table(name="just_for_test")
public class TestBean implements Serializable
{
    @Id
    private Integer id;

    @Column(name="informa")
    private String informa;

    @Column(name="create_at")
    private Date createAt;

    @Column(name="name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInforma() {
        return informa;
    }

    public void setInforma(String informa) {
        this.informa = informa;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
