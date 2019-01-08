package com.edwin.juc;

/**
 * Created by wenpuzhao on 2019/1/6.
 */
public class myThread extends Thread{
    @Override
    public void run() {
        while (true)
            System.out.println(getName()+"线程执行来");
    }

    public static void main(String[] args) {
        myThread myThread=new myThread();
        myThread myThread1=new myThread();

        // Java的线程分为两种：User Thread(用户线程)、DaemonThread(守护线程)。
        myThread.setDaemon(true);// 设置为守护线程
        myThread1.setDaemon(true);
        myThread.start();
        myThread1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
