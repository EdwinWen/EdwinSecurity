package com.edwin.juc;

/**
 * Created by wenpuzhao on 2019/1/6.
 */
public class myRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("实现runable的方式实现");
    }

    public static void main(String[] args) {
        myRunnable runnable=new myRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        // 匿名内部类的方式
        new Thread(){public void run(){
            System.out.println("实现内部类的方式实现");
        }}.start();

        // 匿名内部类，实现runnable接口的方式
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("实现内部类的runable的方式实现");
            }
        }).start();



    }
}
