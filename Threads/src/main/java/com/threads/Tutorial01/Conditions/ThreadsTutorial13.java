package com.threads.Tutorial01.Conditions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadsTutorial13 {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static int accountCount = 0;

    public static void main(String[] args)  {
        new AccountMinus().start();
        new AccountPlus().start();
    }

    static class AccountPlus extends Thread {
        @Override
        public void run() {
            lock.lock();
            accountCount += 10;
            condition.signal();
            lock.unlock();
        }
    }

    static class AccountMinus extends Thread {
        @Override
        public void run() {
            lock.lock();
            if(accountCount < 10) {
                try {
                    System.out.println(accountCount);
                    condition.await();
                    System.out.println(accountCount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            accountCount -= 10;
            lock.unlock();
        }
    }
}
