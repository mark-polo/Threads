package com.threads.Tutorial01.WaitAndNotify;

public class ThreadsTutorial08 {
    public static void main(String[] args) throws InterruptedException {
        ThreadB threadB = new ThreadB();
        threadB.start();

        synchronized (threadB) {
            threadB.wait();
        }

        System.out.println(threadB.total);
    }

    static class ThreadB extends Thread {
        int total;
        @Override
        public void run() {
            synchronized (this) {
                for (int i = 0; i < 5; i++) {
                    total += i;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                notify();
            }
        }
    }
}
