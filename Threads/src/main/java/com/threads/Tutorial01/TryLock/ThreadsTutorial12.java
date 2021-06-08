package com.threads.Tutorial01.TryLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadsTutorial12 {
    static Lock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        new MyThreadOne().start();
        new MyThreadTwo().start();
    }

    static class MyThreadOne extends Thread {
        @Override
        public void run() {
            lock.lock();
            System.out.println(getName() + " start working ");

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(getName() + " stop working ");
            lock.unlock();
        }
    }

    static class MyThreadTwo extends Thread {
        @Override
        public void run() {
            System.out.println(getName() + " begin working");

            while (true) {
                if (lock.tryLock()) {
                    System.out.println(getName() + " working  ");
                    break;
                } else {
                    System.out.println(getName() + " waiting ");
                }
            }
        }
    }
}