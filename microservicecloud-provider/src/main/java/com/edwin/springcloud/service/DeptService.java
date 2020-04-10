package com.edwin.springcloud.service;

import com.edwin.springcloud.entities.Dept;

import java.util.List;

/**
 * Created by wenpuzhao on 2019/3/26.
 */
public interface DeptService {
    public boolean add(Dept dept);

    public Dept get(Long id);

    public List<Dept> list();
}
