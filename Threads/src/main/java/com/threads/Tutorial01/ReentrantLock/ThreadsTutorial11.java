package com.threads.Tutorial01.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadsTutorial11 {
    public static void main(String[] args) throws InterruptedException {
        Resources resources = new Resources();
        resources.setI(5);
        resources.setJ(10);
        MyThread myThread = new MyThread();
        myThread.setName("one");
        MyThread myThread2 = new MyThread();
        myThread.setResources(resources);
        myThread2.setResources(resources);
        myThread.start();
        myThread2.start();
        myThread.join();
        myThread2.join();
        System.out.println(resources.getI());
        System.out.println(resources.getJ());
    }
}

class MyThread extends Thread {
    Resources resources;
    public void setResources(Resources resources) {
        this.resources = resources;
    }
    @Override
    public void run() {
        resources.changeI();
        resources.changeJ();
    }
}

class Resources {

    private int i;
    private int j;

    public int getJ() {
        return j;
    }

    public synchronized void setJ(int j) {
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public synchronized void setI(int i) {
        this.i = i;
    }

    Lock lock = new ReentrantLock();

    public void changeI () {
        lock.lock();
        int i = this.i;

        if (Thread.currentThread().getName().equals("one")) {
            Thread.yield();
        }

        i++;
        this.i = i;
    }

    public void changeJ () {
        int j = this.j;

        if (Thread.currentThread().getName().equals("one")) {
            Thread.yield();
        }

        j++;
        this.j = j;
        lock.unlock();
    }
}