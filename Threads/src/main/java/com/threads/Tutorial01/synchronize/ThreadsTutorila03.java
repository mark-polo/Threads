package com.threads.Tutorial01.synchronize;

public class ThreadsTutorila03 {
    public static void main(String[] args) throws InterruptedException {
        Resources resources = new Resources();
        resources.setI(5);
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
    }
}

class Resources {

    // synchronized - для всех методов которые меняют значение

    private int i;

    public int getI() {
        return i;
    }

    public synchronized void setI(int i) {
        this.i = i;
    }

    public void changeI () {
        synchronized (this) {
            int i = this.i;

            if (Thread.currentThread().getName().equals("one")) {
                Thread.yield();
            }

            i++;
            this.i = i;
        }
    }
}