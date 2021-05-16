package com.testRestful.entity;

import java.io.Serializable;

/**
 * (TUser)实体类
 *
 * @author dan
 * @since 2021-05-07 09:32:02
 */
public class TUser implements Serializable {
    private static final long serialVersionUID = -92089566797396833L;

    private Integer id;

    private String name;

    private Integer age;

    private Object salary;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Object getSalary() {
        return salary;
    }

    public void setSalary(Object salary) {
        this.salary = salary;
    }

}
