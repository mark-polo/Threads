package com.threads.Tutorial01;

public class ThreadsLesson1 {
    public static void main(String[] args) {
        new MyThread().start();
        new MyThread().start();
        new MyThread().start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 400; i++) {
            System.out.println("thread number : " + Thread.currentThread().getName() + " i : " + i);
        }
    }
}

//class MyRunnable implements Runnable {
//    @Override
//    public void run() {
//        System.out.println("My runnable thred 2 " + Thread.currentThread().getName());
//    }
//}