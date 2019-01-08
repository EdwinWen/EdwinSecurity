package com.edwin.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by wenpuzhao on 2019/1/6.
 *
 *
 *
 * 带返回参数的多线程
 */
public class myCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("正在紧张的执行中。。。。");
        Thread.sleep(3000);

        return 1;
    }

    public static void main(String[] args) {
        myCallable callable=new myCallable();
        FutureTask<Integer> task = new FutureTask<Integer>(callable);
        Thread t = new Thread(task);
        t.start();
        System.out.println("我先干点别的。。。。");

        try {
            Integer rusult=task.get();
            System.out.println("线程执行的结果为："+rusult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
