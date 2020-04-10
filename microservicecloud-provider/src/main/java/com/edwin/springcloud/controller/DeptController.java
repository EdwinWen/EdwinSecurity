package com.edwin.springcloud.controller;

import com.edwin.springcloud.entities.Dept;
import com.edwin.springcloud.service.DeptClientService;
import com.edwin.springcloud.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * Created by wenpuzhao on 2019/3/26.
 */
@RestController
public class DeptController implements DeptClientService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DeptService deptService;


    private void sleep(String methodName) {
        int sleepMinTime = new Random().nextInt(3000);
        logger.info("helloService "+methodName+" sleepMinTime: "+sleepMinTime);
        try {
            Thread.sleep(sleepMinTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Dept get(@PathVariable("id") long id) {
       // sleep("kkkkk");
        Dept dept=deptService.get(id);
        return dept;
    }

    @Override
    public List<Dept> list() {
        return null;
    }

    @Override
    public boolean add(Dept dept) {
        return false;
    }
}
