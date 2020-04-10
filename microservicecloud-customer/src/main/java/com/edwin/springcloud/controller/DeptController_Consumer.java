package com.edwin.springcloud.controller;

import com.edwin.springcloud.entities.Dept;
import com.edwin.springcloud.service.DeptClientFeginService;
import com.edwin.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wenpuzhao on 2019/3/26.
 */
@RestController
public class DeptController_Consumer {
    @Autowired
    private DeptClientFeginService deptClientFeginService;

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id)
    {
        Dept dept=deptClientFeginService.get(id);
        return dept;
    }

    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list()
    {
        return this.deptClientFeginService.list();
    }

    @RequestMapping(value = "/consumer/dept/add")
    public Object add(Dept dept)
    {
        return this.deptClientFeginService.add(dept);
    }
}
