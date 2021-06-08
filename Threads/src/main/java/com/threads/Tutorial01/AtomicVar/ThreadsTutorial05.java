package com.threads.Tutorial01.AtomicVar;

/*

        Атомарность операции чаще всего принято обозначать через ее признак неделимости: операция
        может либо примениться полностью, либо не примениться вообще.

 */

import java.util.concurrent.atomic.*;

public class ThreadsTutorial05 {

    static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 10_000; j++) {
            new MyThread().start();
        }
        Thread.sleep(2_000);
        System.out.println(i.get());
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            i.incrementAndGet();
        }
    }
}
