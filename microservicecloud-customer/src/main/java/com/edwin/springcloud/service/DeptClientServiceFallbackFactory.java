package com.edwin.springcloud.service;

import com.edwin.springcloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component // 不要忘记添加，不要忘记添加
public class DeptClientServiceFallbackFactory implements  DeptClientFeginService
{

	@Override
	public Dept get(@PathVariable("id") long id) {
		return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭")
				.setDb_source("no this database in MySQL");
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
